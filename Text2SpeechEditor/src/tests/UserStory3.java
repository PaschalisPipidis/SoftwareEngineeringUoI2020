package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.*;
import model.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.SwingUtilities;

class UserStory3 {
	private GUI gui;
	private String text;
	private String textEdit = "Just robot things";
	private File file;

	@BeforeEach
	void setUp() throws Exception {
		this.gui = GUI.getInstance();
		JFrame frame = gui.getJFrame();
		JMenuBar menuBar = frame.getJMenuBar();
		JMenu file = menuBar.getMenu(0);
		JMenuItem newFile = file.getItem(0);
		JMenuItem saveFile = file.getItem(2);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				newFile.doClick();
			}
		});
		String title = "Just a test";
		StringSelection stringSelection = new StringSelection(title);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, stringSelection);

		Robot robot = new Robot();
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(1000);
		
		String author = "Java Robot";
		stringSelection = new StringSelection(author);
		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, stringSelection);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(1000);
		
		stringSelection = new StringSelection(textEdit);
		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, stringSelection);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(1000);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				saveFile.doClick();
			}
		});
		
		String fileName = "RobotTestFile.txt";
		stringSelection = new StringSelection(fileName);
		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, stringSelection);
		
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(1000);
		
		this.file = new File(gui.getFilePath());
		DocumentInternal doc = DocumentInternal.getInstance();
		this.text = doc.getDocText();
	}

	@Test
	void testFileSave() throws AWTException {
		assertTrue(file.exists());
		assertEquals(textEdit, text);
	}
}
