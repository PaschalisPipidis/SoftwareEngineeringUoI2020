package view;

import commands.*;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;


@SuppressWarnings("serial")
public class GUI extends JFrame {
	private static GUI singleInstance = null;
	
	private JFrame frame = new JFrame("Text to Speech Editor");
	private JSplitPane splitPane = new JSplitPane();
	private JTextPane topPane = new JTextPane();
	private JTextPane botPane = new JTextPane();
	private JMenuBar menubar = new JMenuBar();
	private SimpleAttributeSet attributeSet = new SimpleAttributeSet();
	private String saveTime = ""; //Time of last save
	private String creationTime = ""; //Time of creation
	private String author = ""; //Document author
	private String title = ""; //Document title
	private String filePath;
	private boolean noFile = true;
	private boolean recordMacro = false;
	private ArrayList<ActionEvent> macroActions = new ArrayList<ActionEvent>();
	private int lineToPlay = -1;
	
	private GUI(){
		initUI();
	}
	
	public static GUI getInstance() {
		if(singleInstance == null)
			singleInstance = new GUI();
		return singleInstance;
	}
	
	private void initUI() {
		
		initPanes();
  		
        menubar = initMenu();
 
		frame.add(menubar);frame.add(splitPane);	//bale mpara kai panel sto para8uro
		frame.setJMenuBar(menubar); ///bale thn mpara
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); ///
		frame.addWindowListener(new WindowAdapter() {		//////auto einai to para8uro an 8eloume ;h oxi na kleisei to frame apo to x panw deksia
			@Override
			public void windowClosing(WindowEvent we)
			{ 
				int PromptResult = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Text to Speach Editor", JOptionPane.YES_NO_OPTION);
				if(PromptResult==JOptionPane.YES_OPTION)
				{
					System.exit(0);
				}
			}
		});
		
		frame.pack(); // einai gia kapoia ergaleia (isws den xreiastei)
        frame.setSize(800, 600); //mege8os arxikou para8yrou
		frame.setLocationRelativeTo(null);		//sto kentro o8onhs
		frame.setVisible(true);
	}
	
	private void initPanes() {
		JScrollPane scrollPane = new JScrollPane(botPane);
	    TextLineNumber tln = new TextLineNumber(botPane);
	    scrollPane.setRowHeaderView( tln );
		
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);  // we want it to split the window vertically
        splitPane.setDividerLocation(100);                    // the initial position of the divider is 100
        splitPane.setTopComponent(topPane);                  // at the top we want our "topPanel"
        splitPane.setBottomComponent(scrollPane);            // and at the bottom we want our "botPanel"
        
        botPane.setLayout(new BoxLayout(botPane, BoxLayout.Y_AXIS));		//oti einai apo katw einai to botpanel
        botPane.setText("Your text will appear here to be edited after creating a new file or opening an existing one.");
        botPane.setEditable(false);
        
        StyleConstants.setBold(attributeSet, true);  //attribute BOLD  
		StyleConstants.setForeground(attributeSet, Color.blue); //attribute BLUE
		topPane.setCharacterAttributes(attributeSet, true);  //Set the attributes before adding text  
        topPane.setText("Welcome to our Text to Speech Editor!\n"
        		+ "Start by creating a new text file (File>New File) or opening an existing one (File>Open File)."); //top panel stoixeia
        topPane.setEditable(false);
	}
	
	private JMenuBar initMenu() {
		
		JMenuItem newFile=new JMenuItem("New File");    							//
		JMenuItem open=new JMenuItem("Open File");    							//
		JMenuItem save=new JMenuItem("Save File");    							//
		JMenuItem edit=new JMenuItem("Save Edits");
		
		JMenuItem playAll=new JMenuItem("Voice the text");    					//OLA ta onomata kai to pws 8a emfanizontai katw apo ta ergaleia
		JMenuItem playLine=new JMenuItem("Voice a line");    						//
		JMenuItem playAllEncoded = new JMenuItem("Voice the text encoded");
		JMenuItem playLineEncoded = new JMenuItem("Voice a line encoded");
		JMenuItem encodingStrat = new JMenu("Encoding Methods (Default: Atbash)");
		JMenuItem Atbash=new JMenuItem("Atbash");    					//
		JMenuItem Rot_13=new JMenuItem("ROT13");  			//
		JMenuItem reverseEnable=new JMenuItem("Enable reverse reading");    		//
		JMenuItem reverseDisable=new JMenuItem("Disable reverse reading");			//
								
		JMenuItem volume=new JMenuItem("Volume");    								//
		JMenuItem pitch=new JMenuItem("Pitch");    						//
		JMenuItem speed=new JMenuItem("Rate");								//
		
		JMenuItem start=new JMenuItem("Start recording Actions for new Macro"); 				//
		JMenuItem stop=new JMenuItem("Stop recording Actions for Macro"); 					//
		JMenuItem re_do=new JMenuItem("Use recorded Macro"); 				//
		
		JMenuBar menubar=new JMenuBar();   // kane thn mpara
		JMenu file=new JMenu("File");    //
		JMenu play=new JMenu("Playback");      //
		JMenu soundbar=new JMenu("Audio");//      
		JMenu replay=new JMenu("Macro Tools");     // 

		CommandsFactory factory = new CommandsFactory(); 				//initialise commands
		newFile.addActionListener(factory.createCommand("FileNew"));	//and pair them to
		open.addActionListener(factory.createCommand("FileOpen"));		//their respective
		save.addActionListener(factory.createCommand("FileSave"));		//menu items
		edit.addActionListener(factory.createCommand("TextSave"));
		
		playAll.addActionListener(factory.createCommand("TextPlayAll"));
		playLine.addActionListener(factory.createCommand("TextPlayLine"));
		playAllEncoded.addActionListener(factory.createCommand("EncodedAll"));
		playLineEncoded.addActionListener(factory.createCommand("EncodedLine"));
		Atbash.addActionListener(factory.createCommand("EncodeStrat"));
		Rot_13.addActionListener(factory.createCommand("EncodeStrat"));
		reverseEnable.addActionListener(factory.createCommand("Reverse"));
		reverseDisable.addActionListener(factory.createCommand("Reverse"));
		
		volume.addActionListener(factory.createCommand("AudioVolume"));
		pitch.addActionListener(factory.createCommand("AudioPitch"));
		speed.addActionListener(factory.createCommand("AudioRate"));
		
		start.addActionListener(factory.createCommand("MacroManager"));
		stop.addActionListener(factory.createCommand("MacroManager"));
		re_do.addActionListener(factory.createCommand("MacroManager"));
		
		
		file.add(newFile); file.add(open);file.add(save);file.add(edit);   																					//
		play.add(playAll);play.add(playLine);play.add(playAllEncoded);play.add(playLineEncoded);play.add(encodingStrat);play.add(reverseEnable);play.add(reverseDisable);//
		encodingStrat.add(Atbash);encodingStrat.add(Rot_13);											//bale sta ergaleia ta antistoixa pou exoun
		soundbar.add(volume);soundbar.add(pitch);soundbar.add(speed);    								//
		replay.add(start);replay.add(stop);replay.add(re_do);    						//
		
		menubar.add(file);menubar.add(play);menubar.add(soundbar);menubar.add(replay); // bale ta ergaleia sthn mpara
		return menubar;
	}
	
	public void initNewFileDialog() {
		title = JOptionPane.showInputDialog(frame,"Enter Title"); // get title me arxiko para8uro
		author = JOptionPane.showInputDialog(frame,"Enter Author"); // get author me arxiko para8uro
		if (title != null && author != null) {
			creationTime = new Date().toString();  //Time of file's creation
			topPane.setText("Title: "+title+"\n"+
					"Author: "+author+"\n" +
					"Time of file creation: " +creationTime+"\n"+
					"Time of last file save: "); //toppanel stoixeia
			botPane.setEditable(true);
	        botPane.setText("");
	        botPane.getDocument().putProperty("source", botPane);
	        noFile = false;
	        frame.setVisible(true);
	        botPane.grabFocus();
		}
	}
	
	public void initOpenFileDialog() {
		JFileChooser fileChooser=new JFileChooser(); //standard entolh gia na briskeis file   
		
		int i=fileChooser.showOpenDialog(this);    
		if(i==JFileChooser.APPROVE_OPTION){ // ama nai 
			File openFile;
			if(!fileChooser.getSelectedFile().getName().endsWith(".txt")) {
				openFile = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
			}else {
				openFile=fileChooser.getSelectedFile();    //pare to arxeio 
			}
			String filePath=openFile.getPath();    // to path
			this.filePath = filePath;
			try{  //mikro elegxoi kai balto sto botpanel
			BufferedReader bufferedReader=new BufferedReader(new FileReader(filePath));    
			String tempText="", finalText="";
			tempText = bufferedReader.readLine();
			if(tempText.equals(".-DocumentProperties-.")) {
				title = bufferedReader.readLine();
				author = bufferedReader.readLine();
				creationTime = bufferedReader.readLine();
				saveTime = bufferedReader.readLine();
				tempText = bufferedReader.readLine();
			}else {
				title = "Unknown";
				author = "Unknown";
				creationTime = "Unknown";
				saveTime = "Unknown";
			}
			if(tempText.equals(".-DocumentText-.")) {
				while((tempText=bufferedReader.readLine())!=null) {    
					finalText += tempText + "\n";
				}  
			}else {
				finalText += tempText + "\n";
				while((tempText=bufferedReader.readLine())!=null) {    
					finalText += tempText + "\n";
				}  
			}
			topPane.setText("Title: "+title+"\n"+
					"Author: "+author+"\n" +
					"Time of file creation: " +creationTime+"\n"+
					"Time of last file save: "+saveTime);
			botPane.setText(finalText.trim()); 
			botPane.setEditable(true);
			botPane.grabFocus();
			bufferedReader.close(); 
			noFile = false;
			}catch (Exception ex) {ex.printStackTrace();  }                 
		} 
	}
	
	public void initSaveFileDialog() {
		saveTime = new Date().toString();
		topPane.setText("Title: "+title+"\n"+
				"Author: "+author+"\n" +
				"Time of file creation: " +creationTime+"\n"+
				"Time of last file save: "+saveTime);
		final JFileChooser saveAsFileChooser = new JFileChooser(); // pare ta axeia
	    int actionDialog = saveAsFileChooser.showSaveDialog(frame); // 
	    if (actionDialog == JFileChooser.APPROVE_OPTION) {		// an nai
	    	File file = saveAsFileChooser.getSelectedFile();
		    if (!file.getName().endsWith(".txt")) {
		       file = new File(file.getAbsolutePath() + ".txt"); //bale txt an den eixe eidh txt
		    }
		    this.filePath = file.getAbsolutePath();
		    BufferedWriter outFile = null;
		    try {
		    	outFile = new BufferedWriter(new FileWriter(file));
		    	outFile.write(".-DocumentProperties-.\n");
				outFile.write(title+"\n");
				outFile.write(author+"\n");
				outFile.write(creationTime+"\n");
				outFile.write(saveTime+"\n");
				outFile.write(".-DocumentText-.\n");
		        botPane.write(outFile);
		        
		    }catch (IOException ex) {
		    	ex.printStackTrace();
		    }finally {
		    	if (outFile != null) {
		    		try {
		    			outFile.close();
		        	}catch (IOException exe) {}
		        }
		    }
	    }
	    botPane.grabFocus();
	}

	public void initPlayLineDialog() {
		try {
			int lineNumber = Integer.parseInt(JOptionPane.showInputDialog(frame,"Enter number of line to voice"))-1;
			if (lineNumber < botPane.getText().split("\\r?\\n").length && lineNumber >= 0) {
				lineToPlay = lineNumber;
			}else {
				JOptionPane.showMessageDialog(frame, "The line you asked for is out of bounds, please try again", "ERROR", JOptionPane.ERROR_MESSAGE);
				initPlayLineDialog();
			}
		}catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(frame, "Input was not an integer, please try again", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		botPane.grabFocus();
	}
	
	public int initVolumeDialog() {
		try {
			int volume = Integer.parseInt(JOptionPane.showInputDialog(frame,"Enter desired speaking volume (0 to 100)"));
			if (volume <= 100  && volume >= 0) {
				botPane.grabFocus();
				return volume;
			}else {
				JOptionPane.showMessageDialog(frame, "The volume you asked for is out of bounds, please try again", "ERROR", JOptionPane.ERROR_MESSAGE);
				initVolumeDialog();
			}
		}catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(frame, "Input was not an integer, please try again", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		botPane.grabFocus();
		return 50;
	}
	
	public int initPitchDialog() {
		try {
			int pitch = Integer.parseInt(JOptionPane.showInputDialog(frame,"Enter desired speaking pitch in Hertz (25 to 300)"));
			if (pitch <= 300  && pitch >= 25) {
				botPane.grabFocus();
				return pitch;
			}else {
				JOptionPane.showMessageDialog(frame, "The pitch you asked for is out of bounds, please try again", "ERROR", JOptionPane.ERROR_MESSAGE);
				initPitchDialog();
			}
		}catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(frame, "Input was not an integer, please try again", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		botPane.grabFocus();
		return 100;
	}
	
	public int initRateDialog() {
		try {
			int rate = Integer.parseInt(JOptionPane.showInputDialog(frame,"Enter desired speaking rate in words per minute (50 to 250)"));
			if (rate <= 250  && rate >= 50) {
				botPane.grabFocus();
				return rate;
			}else {
				JOptionPane.showMessageDialog(frame, "The rate you asked for is out of bounds, please try again", "ERROR", JOptionPane.ERROR_MESSAGE);
				initRateDialog();
			}
		}catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(frame, "Input was not an integer, please try again", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		botPane.grabFocus();
		return 150;
	}
	
	public void initStratSelectedDialog(String strat) {
		JOptionPane.showMessageDialog(frame, strat+" selected", "Encoding Strategy", JOptionPane.WARNING_MESSAGE);
		botPane.grabFocus();
	}
	
	public boolean checkForFile() {
		return noFile;
	}
	
	public void setRecordMacro(boolean recordMacro) {
		this.recordMacro = recordMacro;
	}
	
	public boolean getRecordMacro() {
		return recordMacro;
	}
	
	public void clearActionList() {
		macroActions.clear();
	}
	
	public void addToActionList(ActionEvent e) {
		macroActions.add(e);
	}
	
	public ArrayList<ActionEvent> getActionList(){
		return macroActions;
	}
	
	public void displayMacroWarning() {
		JOptionPane.showMessageDialog(frame, "Cannot play macro while still recording actions.", "ERROR", JOptionPane.ERROR_MESSAGE);
		botPane.grabFocus();
	}
	
	public void displayNoFileError() {
		JOptionPane.showMessageDialog(frame, "No active file found.", "ERROR", JOptionPane.ERROR_MESSAGE);
		botPane.grabFocus();
	}
	
	public String getDocTitle(){
		return title;
	}
	
	public String getDocAuthor(){
		return author;
	}
	
	public String getDocCreationTime(){
		return creationTime;
	}
	
	public String getDocSaveTime(){
		return saveTime;
	}
	
	public String getDocText(){
		String docText = new String();
		docText = botPane.getText();
		return docText;
	}
	
	public int getLineNumber() {
		return lineToPlay;
	}
	
	public void setTextTitle(String title) {
		this.title = title;
	}
	
	public void setTextAuthor(String author) {
		this.author = author;
	}

	public JFrame getJFrame() {
		return frame;
	}
	public String getFilePath() {
		return filePath;
	}
}
