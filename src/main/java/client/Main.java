package client;

import javax.swing.*;

/**
 * The application's entry point
 */
public class Main {

    public static void main(String[] args){
        Runnable r = new Runnable() {
            public void run() {

                GuiManager.getInstance().initGuiComponents();
                ReqRespHandler.getInstance();
            }
        };
        // Swing GUIs should be created and updated on the EDT
        SwingUtilities.invokeLater(r);
    }
}
