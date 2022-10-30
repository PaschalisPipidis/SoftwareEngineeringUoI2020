package commands;

import view.*;
import model.*;

import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class FileOpen extends JMenuItem implements ActionListener {
	
	public void actionPerformed(ActionEvent e)
	{
		GUI gui = GUI.getInstance();
		if (gui.getRecordMacro()) {
			gui.addToActionList(e);
		}
		gui.initOpenFileDialog();
		String docTitle = gui.getDocTitle();
		String docAuthor = gui.getDocAuthor();
		String docCreationTime = gui.getDocCreationTime();
		String docSaveTime = gui.getDocSaveTime();
		String docText = gui.getDocText();
		DocumentInternal doc = DocumentInternal.getInstance();
		doc.setDocProperties(docTitle, docAuthor, docCreationTime, docSaveTime);
		doc.setDocText(docText);
	}
}
