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
import javax.swing.*;
import javax.swing.SwingUtilities;

class UserStory1 {
	private GUI gui;
	private String titleUI;
	private String authorUI;
	private String creationTimeUI;
	private String saveTimeUI;
	private String titleInternal;
	private String authorInternal;
	private String creationTimeInternal;
	private String saveTimeInternal;

	@BeforeEach
	void setUp() throws Exception {
		this.gui = GUI.getInstance();
		JFrame frame = gui.getJFrame();
		JMenuBar menuBar = frame.getJMenuBar();
		JMenu file = menuBar.getMenu(0);
		JMenuItem newFile = file.getItem(0);
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
		
		this.titleUI = gui.getDocTitle();
		this.authorUI = gui.getDocAuthor();
		this.creationTimeUI = gui.getDocCreationTime();
		this.saveTimeUI = gui.getDocSaveTime();
		
		DocumentInternal doc = DocumentInternal.getInstance();
		this.titleInternal = doc.getInternalTitle();
		this.authorInternal = doc.getInternalAuthor();
		this.creationTimeInternal = doc.getInternalCreationTime();
		this.saveTimeInternal = doc.getInternalSaveTime();
	}

	@Test
	void testFileProperties() throws AWTException {
		assertEquals("Just a test", titleUI);
		assertEquals("Just a test", titleInternal);
		assertEquals("Java Robot", authorUI);
		assertEquals("Java Robot", authorInternal);
		assertEquals(creationTimeUI, creationTimeInternal);
		assertEquals(saveTimeUI, saveTimeInternal);
	}
	/*
	@Test
	void testTitle() throws AWTException {
		assertEquals("Just a test", titleUI);
		assertEquals("Just a test", titleInternal);
	}
	
	@Test
	void testAuthor() throws AWTException {
		assertEquals("Java Robot", authorUI);
		assertEquals("Java Robot", authorInternal);
	}
	
	@Test
	void testCreationTime() throws AWTException {
		assertEquals(creationTimeUI, creationTimeInternal);
	}
	
	@Test
	void testSaveTime() throws AWTException {
		assertEquals(saveTimeUI, saveTimeInternal);
	}
	*/
}
