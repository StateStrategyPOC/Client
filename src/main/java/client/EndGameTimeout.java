package client;

import java.util.TimerTask;

/**
 * Represents the thread that waits for a certain time then changes the view of the application to the
 * 'game list view'
 */
public class EndGameTimeout extends TimerTask {
    @Override
    public void run() {
        GuiManager.getInstance().returnToGameList();
    }
}
