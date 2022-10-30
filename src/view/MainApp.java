package view;
import javax.swing.*;
import javax.swing.text.*;

public class MainApp {
	public static void main (String[] args) throws BadLocationException {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUI.getInstance();
			}
		});
	}
}
