package commands;

import view.*;
import model.*;

import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class AudioRate extends JMenuItem implements ActionListener {
	
	public void actionPerformed(ActionEvent e)
	{
		GUI gui = GUI.getInstance();
		if (gui.getRecordMacro()) {
			gui.addToActionList(e);
		}
		TextToSpeechAPIFactory TTSAPIFactory = new TextToSpeechAPIFactory();
		int rate = gui.initRateDialog();
		TextToSpeechAPI freeTTS = TTSAPIFactory.createTTSAPI("FreeTTSAdapter");
		freeTTS.setRate(rate);
	}
}
