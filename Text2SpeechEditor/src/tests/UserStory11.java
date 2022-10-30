package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.*;
import model.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.SwingUtilities;

class UserStory11 {
	private GUI gui;
	private String selectedMethod = "";

	@BeforeEach
	void setUp() throws Exception {
		this.gui = GUI.getInstance();
		JFrame frame = gui.getJFrame();
		JMenuBar menuBar = frame.getJMenuBar();
		JMenu playback = menuBar.getMenu(1);
		JMenu encodingMethods = (JMenu)playback.getItem(4);
		JMenuItem method = encodingMethods.getItem(1);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				method.doClick();
			}
		});

		Robot robot = new Robot();
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(1000);
		
		DocumentInternal doc = DocumentInternal.getInstance();
		selectedMethod = doc.getEncodingStrat();
	}

	@Test
	void testFileVoice() throws AWTException {
		assertEquals("ROT13", selectedMethod);
	}
}
