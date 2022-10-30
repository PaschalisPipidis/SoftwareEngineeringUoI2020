package model;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTSAdapter implements TextToSpeechAPI{
	private static FreeTTSAdapter singleInstance = null;
	private static VoiceManager freeTTSVM = VoiceManager.getInstance();
	private static Voice freeTTSVoice;
	private float volume = 5.0f;
	private float pitch = 100.0f;
	private float rate = 150.0f;
	
	
	public FreeTTSAdapter(){}
	
	public static FreeTTSAdapter getInstance() {
		if(singleInstance == null)
			singleInstance = new FreeTTSAdapter();
		return singleInstance;
	}
	
	public void play(String text) {
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		freeTTSVoice = freeTTSVM.getVoice("kevin16");
		if (freeTTSVoice != null) {
			freeTTSVoice.allocate();// Allocating Voice
	        try {
	        	freeTTSVoice.setRate(rate);// Setting the rate of the voice
	        	freeTTSVoice.setPitch(pitch);// Setting the Pitch of the voice
	        	freeTTSVoice.setVolume(volume);// Setting the volume of the voice
	        	freeTTSVoice.speak(text);// Calling speak() method
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }
	        freeTTSVoice.deallocate();
	    } else {
	        throw new IllegalStateException("Cannot find voice: kevin16");
	    }
	}
	
	public void setVolume(int volume) {
		this.volume = (float) volume/10;
	}
	
	public void setPitch(int pitch) {
		this.pitch = (float) pitch;
	}
	
	public void setRate(int rate) {
		this.rate = (float) rate;
	}
	
	public int getVolume() {
		int rVolume = (int)(volume*10);
		return rVolume;
	}
	
	public int getPitch() {
		int rPitch = (int) pitch;
		return rPitch;
	}
	
	public int getRate() {
		int rRate = (int) rate;
		return rRate;
	}
}
