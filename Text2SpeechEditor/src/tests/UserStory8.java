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
import java.util.ArrayList;

import javax.swing.*;

class UserStory8 {
	private GUI gui;
	private String textEdit = "Just robot things";
	private String text = "";
	private int lineNumber;
	private boolean reversed = false;
	private boolean notReversed = true;

	@BeforeEach
	void setUp() throws Exception {
		this.gui = GUI.getInstance();
		JFrame frame = gui.getJFrame();
		JMenuBar menuBar = frame.getJMenuBar();
		JMenu file = menuBar.getMenu(0);
		JMenuItem newFile = file.getItem(0);
		JMenu playback = menuBar.getMenu(1);
		JMenuItem voiceLine = playback.getItem(1);
		JMenuItem reverse = playback.getItem(5);
		JMenuItem doNotReverse = playback.getItem(6);
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
		reverse.doClick();
		
		Runnable runnable = new Runnable() {
			public void run() {
				synchronized(this) {
					voiceLine.doClick();
					notifyAll();
				}
			}
		};
		SwingUtilities.invokeLater(runnable);
		
		stringSelection = new StringSelection("1");
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
		synchronized(runnable) {
			try {
				runnable.wait(1);
			}catch(InterruptedException e){
                e.printStackTrace();
			}
			DocumentInternal doc = DocumentInternal.getInstance();
			this.reversed = doc.getIsReversed();
			this.lineNumber = gui.getLineNumber();
			Line line = doc.getReversedLine(lineNumber);
			ArrayList<String> words = line.getWords();
			for (int i = 0; i < words.size(); i++) {
				this.text = text + " " + words.get(i);
			}
			this.text = text.trim();
			doNotReverse.doClick();
			this.notReversed = doc.getIsReversed();
		}
	}

	@Test
	void testFileVoice() throws AWTException {
		assertTrue(reversed);
		assertFalse(notReversed);
		assertEquals("things robot Just", text);
	}
}
