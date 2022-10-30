package model;

public class TextToSpeechAPIFactory {

	public TextToSpeechAPIFactory() {}
	
	public TextToSpeechAPI createTTSAPI(String nameOfTTSAPI) {
		switch(nameOfTTSAPI) {
		case "FreeTTSAdapter": return FreeTTSAdapter.getInstance();
		default: return null;
		}
	}
}
