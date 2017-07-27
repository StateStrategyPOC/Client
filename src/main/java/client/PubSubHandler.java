package client;

import client_store.ClientStore;
import client_store_actions.ClientRemovePubSubHandlerAction;
import client_store_actions.ClientSetConnectionActiveAction;
import common.RemoteMethodCall;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
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
    @Override
    public void run() {
        while (this.CLIENT_STORE.getState().isGameStarted()) {
            try {
                ActionOnTheWire notification = (ActionOnTheWire) this.inputStream.readObject();
                this.CLIENT_STORE.propagateAction(notification);
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
