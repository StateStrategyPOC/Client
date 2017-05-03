package client;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Starts and display the GUI
 *
 */
public class Main {
	public static void main(String[] args) {
		Runnable r = new Runnable() {
			
			public void run() {


				GuiManager guiManager = GuiManager.getInstance();
				guiManager.initGuiComponents();

			}
		};
		// Swing GUIs should be created and updated on the EDT
		SwingUtilities.invokeLater(r);

	}
}
