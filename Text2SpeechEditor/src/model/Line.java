package model;

import java.util.*;

public class Line {
	private ArrayList<String> words;
	private TextToSpeechAPIFactory TTSAPIFactory = new TextToSpeechAPIFactory();
	private EncodingStrategiesFactory stratFactory = new EncodingStrategiesFactory();
	private String text = "";
	
	public Line(ArrayList<String> words) {
		this.words = words;
	}
	
	public void playLine() {
		TextToSpeechAPI freeTTS = TTSAPIFactory.createTTSAPI("FreeTTSAdapter");
		for(int i = 0; i < words.size(); i++) {
			text = text+words.get(i)+" ";
		}
		text = text.trim();
		freeTTS.play(text);
	}
	
	public void playLineEncoded(String stratChoice) {
		TextToSpeechAPI freeTTS = TTSAPIFactory.createTTSAPI("FreeTTSAdapter");
		EncodingStrategy strat = stratFactory.create(stratChoice);
		for(int i = 0; i < words.size(); i++) {
			text = text+words.get(i)+" ";
		}
		text = text.trim();
		text = strat.encode(text);
		freeTTS.play(text);
	}
	
	public ArrayList<String> getWords() {
		return words;
	}
	
	public String getText() {
		return text;
	}
}
