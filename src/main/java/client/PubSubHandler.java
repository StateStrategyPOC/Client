package client;

import client_store.ClientStore;
import client_store_actions.ClientSetCurrentPSNotificationAction;
import common.PSNotification;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Represents a thread that handles async messages from the server, in the logic
 * of the pub/sub pattern(the client is the subscriber).
 *
 */
public class PubSubHandler extends Thread {

    private final Socket socket;
    private final ObjectInputStream inputStream;
    private final ClientStore CLIENT_STORE;

    public PubSubHandler(Socket socket, ObjectInputStream inputStream) {
        this.socket = socket;
        this.inputStream = inputStream;
        this.CLIENT_STORE = ClientStore.getInstance();
    }

    /**
     * Runs the thread defined in this class. The thread listens for incoming notifications
     * from the connection and makes the them available to the rest of the application
     * via Action propagation
     */
    @Override
    public void run() {
        while (this.CLIENT_STORE.getState().isInRoom()) {
            try {
                PSNotification notification = (PSNotification) this.inputStream.readObject();
                this.CLIENT_STORE.propagateAction(new ClientSetCurrentPSNotificationAction(notification));
            }
            catch (IOException | ClassNotFoundException e){
                try {
                    this.inputStream.close();
                    this.socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        }
        try {
            this.inputStream.close();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
