package commands;

import view.*;
import model.*;

import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class AudioPitch extends JMenuItem implements ActionListener {
	
	public void actionPerformed(ActionEvent e)
	{
		GUI gui = GUI.getInstance();
		if (gui.getRecordMacro()) {
			gui.addToActionList(e);
		}
		TextToSpeechAPIFactory TTSAPIFactory = new TextToSpeechAPIFactory();
		int pitch = gui.initPitchDialog();
		TextToSpeechAPI freeTTS = TTSAPIFactory.createTTSAPI("FreeTTSAdapter");
		freeTTS.setPitch(pitch);
	}
}
