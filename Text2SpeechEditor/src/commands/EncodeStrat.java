package commands;

import view.*;
import model.*;

import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class EncodeStrat extends JMenuItem implements ActionListener {
	public void actionPerformed(ActionEvent e)
	{
		GUI gui = GUI.getInstance();
		if (gui.getRecordMacro()) {
			gui.addToActionList(e);
		}
		JMenuItem item = (JMenuItem)e.getSource();
		String strat = item.getText();
		DocumentInternal doc = DocumentInternal.getInstance();
		doc.setEncodingStrat(strat);
		gui.initStratSelectedDialog(strat);
	}
}
