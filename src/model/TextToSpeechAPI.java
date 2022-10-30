package model;

public interface TextToSpeechAPI {

	public void play(String text);
	
	public void setVolume(int volume);
	
	public void setPitch(int pitch);
	
	public void setRate(int rate);
}
