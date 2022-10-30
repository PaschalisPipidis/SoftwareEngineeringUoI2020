package commands;

import java.awt.event.*;
//import javax.swing.event.*;

public class CommandsFactory{

	public CommandsFactory() {}
	
	public ActionListener createCommand(String commandName) {
		switch(commandName) {
			case "FileNew": return new FileNew();
			case "FileOpen": return new FileOpen();
			case "FileSave": return new FileSave();
			case "TextSave": return new FileEdit();
			
			case "TextPlayAll": return new TextPlayAll();
			case "TextPlayLine": return new TextPlayLine();
			case "EncodedAll": return new EncodedPlayAll();
			case "EncodedLine": return new EncodedPlayLine();
			case "EncodeStrat": return new EncodeStrat();
			case "Reverse": return new ReversalToggle();
			
			case "AudioVolume": return new AudioVolume();
			case "AudioPitch": return new AudioPitch();
			case "AudioRate": return new AudioRate();
			
			case "MacroManager": return new MacroManager();
			
			default: return null;
		}
	}
	
	/*public DocumentListener createEditor(String commandName) {
		switch(commandName) {
			case "TextEdit": return new TextEdit();
			default: return null;
		}
	}*/
}
