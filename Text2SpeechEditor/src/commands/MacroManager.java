package commands;

import view.*; 

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MacroManager extends JMenuItem implements ActionListener {
	
	public void actionPerformed(ActionEvent e)
	{
		GUI gui = GUI.getInstance();
		JMenuItem item = (JMenuItem)e.getSource();
		String recordDo = item.getText();
		if (recordDo.equals("Stop recording Actions for Macro")) {
			gui.setRecordMacro(false);
		}else if (recordDo.equals("Start recording Actions for new Macro")) {
			gui.clearActionList();
			gui.setRecordMacro(true);	
		}else if (recordDo.equals("Use recorded Macro")) {
			if (gui.getRecordMacro()) {
				gui.displayMacroWarning();
				return;
			}
			ArrayList<ActionEvent> eventList = gui.getActionList();
			for (int i = 0; i < eventList.size(); i++) {
				ActionEvent event = eventList.get(i);
				JMenuItem source = (JMenuItem)event.getSource();
				source.doClick();
			}
		}
	}
}