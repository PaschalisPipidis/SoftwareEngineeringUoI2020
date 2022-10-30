package commands;

import model.*;
import view.*;

import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ReversalToggle extends JMenuItem implements ActionListener {
	
	public void actionPerformed(ActionEvent e)
	{
		GUI gui = GUI.getInstance();
		if (gui.getRecordMacro()) {
			gui.addToActionList(e);
		}
		JMenuItem item = (JMenuItem)e.getSource();
		String text = item.getText();
		DocumentInternal doc = DocumentInternal.getInstance();
		if(text.equals("Enable reverse reading")){
			doc.setIsReversed(true);
		}else if(text.equals("Disable reverse reading")) {
			doc.setIsReversed(false);
		}
		
	}
	
}
