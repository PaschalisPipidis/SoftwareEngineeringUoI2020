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

class UserStory12 {
	private GUI gui;
	private int rVol;
	private int rPitch;
	private int rRate;

	@BeforeEach
	void setUp() throws Exception {
		this.gui = GUI.getInstance();
		JFrame frame = gui.getJFrame();
		JMenuBar menuBar = frame.getJMenuBar();
		JMenu voipParams = menuBar.getMenu(2);
		JMenuItem volume = voipParams.getItem(0);
		JMenuItem pitch = voipParams.getItem(1);
		JMenuItem rate = voipParams.getItem(2);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				volume.doClick();
			}
		});

		String vol = "25";
		StringSelection stringSelection = new StringSelection(vol);
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
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				pitch.doClick();
			}
		});

		String pit = "75";
		stringSelection = new StringSelection(pit);
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
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				rate.doClick();
			}
		});

		String rt = "100";
		stringSelection = new StringSelection(rt);
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
		
		TextToSpeechAPIFactory apiFactory = new TextToSpeechAPIFactory();
		FreeTTSAdapter api = (FreeTTSAdapter)apiFactory.createTTSAPI("FreeTTSAdapter");
		rVol = api.getVolume();
		rPitch = api.getPitch();
		rRate = api.getRate();
	}

	@Test
	void testFileVoice() throws AWTException {
		assertEquals(25, rVol);
		assertEquals(75, rPitch);
		assertEquals(100, rRate);
	}
}
