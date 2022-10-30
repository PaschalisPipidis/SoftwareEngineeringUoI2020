package commands;

import view.*;
import model.*;

import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class FileEdit extends JMenuItem implements ActionListener {
	public void actionPerformed(ActionEvent e)
	{
		GUI gui = GUI.getInstance();
		if(gui.checkForFile()) {
			gui.displayNoFileError();
			return;
		}
		if (gui.getRecordMacro()) {
			gui.addToActionList(e);
		}
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
