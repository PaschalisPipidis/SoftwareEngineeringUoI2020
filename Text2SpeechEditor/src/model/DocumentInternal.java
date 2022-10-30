package model;

import java.util.*;



public class DocumentInternal {
	private static DocumentInternal singleInstance = null;
	private String text;
	private String reversedText;
	private String reversedEncodedText;
	private String encodedText;
	private ArrayList<String> properties = new ArrayList<String>();
	private String title;
	private String author;
	private String creationTime;
	private String saveTime;
	private ArrayList<Line> lines = new ArrayList<Line>();
	private ArrayList<Line> reversedLines = new ArrayList<Line>();
	private TextToSpeechAPIFactory TTSAPIFactory = new TextToSpeechAPIFactory();
	private EncodingStrategiesFactory stratFactory = new EncodingStrategiesFactory();
	private boolean isReversed = false;
	private String stratChoice = "Atbash";
	
	private DocumentInternal() {}
	
	public static DocumentInternal getInstance() {
		if(singleInstance == null)
			singleInstance = new DocumentInternal();
		return singleInstance;
	}
	
	public void setDocProperties(String title, String author, String creationTime, String saveTime) {
		this.title = title;
		this.author = author;
		this.creationTime = creationTime;
		this.saveTime = saveTime;
		properties.add(this.title);
		properties.add(this.author);
		properties.add(this.creationTime);
		properties.add(this.saveTime);
	}
	
	public ArrayList<String> getDocProperties(){
		return properties;
	}
	
	public String getInternalTitle() {
		return title;
	}
	
	public String getInternalAuthor() {
		return author;
	}
	
	public String getInternalCreationTime() {
		return creationTime;
	}
	
	public String getInternalSaveTime() {
		return saveTime;
	}
	
	public void setDocText(String text) {
		this.text = text;
		convertToLines(this.text);
		if(isReversed) {
			reverseText(text);
			convertToLines(reversedText);
		}
	}
	
	public String getDocText() {
		return text.trim();
	}
	
	public String getReversedDocText() {
		return reversedText.trim();
	}
	
	public String getDocEncodedText() {
		return encodedText.trim();
	}
	
	public String getDocReversedEncodedText() {
		return reversedEncodedText.trim();
	}
	
	public Line getLine(int lineNumber) {
		return lines.get(lineNumber);
	}
	
	public Line getReversedLine(int lineNumber) {
		return reversedLines.get(lineNumber);
	}
	
	private void convertToLines(String text) {
		ArrayList<Line> contents = new ArrayList<Line>();
		ArrayList<Line> reversedContents = new ArrayList<Line>();
		String lines[] = text.split("\\r?\\n");
		for(int i = 0; i < lines.length; i++) {
			ArrayList<String> words = new ArrayList<String>();
			String tempLine = lines[i].toString();
			String[] tempWords = tempLine.split("\\W+");
			for (int j = 0; j < tempWords.length; j++) {
				words.add(tempWords[j]);
			}
			Line line = new Line(words);
			contents.add(line);
			reversedContents.add(0, line);
		}
		if(isReversed) {
			this.reversedLines = reversedContents;
		}else {
			this.lines = contents;
		}
	}
	
	public void playLine(int lineNumber) {
		if(isReversed) {
			Line lineToPlay = reversedLines.get(lineNumber);
			lineToPlay.playLine();
		}else {
			Line lineToPlay = lines.get(lineNumber);
			lineToPlay.playLine();
		}
	}
	
	public void playLineEncoded(int lineNumber) {
		if(isReversed) {
			Line lineToPlay = reversedLines.get(lineNumber);
			lineToPlay.playLineEncoded(stratChoice);
		}else {
			Line lineToPlay = lines.get(lineNumber);
			lineToPlay.playLineEncoded(stratChoice);
		}
	}
	
	public void playContents() {
		TextToSpeechAPI freeTTS = TTSAPIFactory.createTTSAPI("FreeTTSAdapter");
		if(isReversed) {
			freeTTS.play(reversedText);
		}else {
			freeTTS.play(text);
		}
	}
	
	public void playEncodedContents() {
		TextToSpeechAPI freeTTS = TTSAPIFactory.createTTSAPI("FreeTTSAdapter");
		EncodingStrategy strat = stratFactory.create(stratChoice);
		if(isReversed) {
			this.reversedEncodedText = strat.encode(reversedText);
			freeTTS.play(reversedEncodedText);
		}else {
			this.encodedText = strat.encode(text);
			freeTTS.play(encodedText);
		}
	}
	
	public void setIsReversed(boolean flag) {
		this.isReversed = flag;
	}
	
	public boolean getIsReversed() {
		return isReversed;
	}
	
	public void setEncodingStrat(String stratChoice) {
		this.stratChoice = stratChoice;
	}
	
	public String getEncodingStrat() {
		return stratChoice;
	}
	
	private void reverseText(String text) {
		String lines[] = text.split("\\r?\\n");
		String reversedLines = new String();
		for(int i = lines.length-1; i >= 0; i--) {
			String tempLine = lines[i].toString();
			String[] tempWords = tempLine.split("\\W+");
			String reversedLine = "";
			for (int j = tempWords.length-1; j >= 0 ; j--) {
				reversedLine = reversedLine + tempWords[j] + " ";
			}
			reversedLine = reversedLine.trim();
			reversedLines = reversedLines + reversedLine + "\n";
		}
		this.reversedText = reversedLines;
	}
	
}
