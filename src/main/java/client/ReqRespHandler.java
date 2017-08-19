package client;

import client_store.ClientStore;
import client_store_actions.ClientSetConnectionActiveAction;
import client_store_actions.ClientSetCurrentRRNotificationAction;
import common.ActionOnTheWire;
import common.EncodedBehaviourIdentifiers;
import common.RRNotification;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Represents a handler of a request communication from the client to the server
 */
public class ReqRespHandler {

    private final ClientStore CLIENT_STORE;
    private static ReqRespHandler INSTANCE;

    public static ReqRespHandler getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ReqRespHandler();
        }
        return INSTANCE;
    }

    private ReqRespHandler() {
        this.CLIENT_STORE = ClientStore.getInstance();
    }

    public void initRequestResponse(ActionOnTheWire actionOnTheWire){
        try {
            Socket socket = new Socket(CLIENT_STORE.getState().getHost(), CLIENT_STORE.getState().getTcpPort());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.flush();
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            this.sendRequest(actionOnTheWire,outputStream);
            if(actionOnTheWire.getActionIdentifier().equals(EncodedBehaviourIdentifiers.subscribe())){
                PubSubHandler pubSubHandler = new PubSubHandler(socket,inputStream);
                pubSubHandler.start();
            }
            else {
                this.getResponse(inputStream);
                this.closeConnection(socket,outputStream,inputStream);
            }
            CLIENT_STORE.propagateAction(new ClientSetConnectionActiveAction(true));
        } catch (IOException | ClassNotFoundException e) {
            this.CLIENT_STORE.propagateAction(new ClientSetConnectionActiveAction(false));
        }
    }

    private void getResponse(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        RRNotification response = (RRNotification) inputStream.readObject();
        this.CLIENT_STORE.propagateAction(new ClientSetCurrentRRNotificationAction(response));
    }

    private void sendRequest(ActionOnTheWire actionOnTheWire, ObjectOutputStream outputStream) throws IOException {
        outputStream.writeObject(actionOnTheWire);
        outputStream.flush();
    }
    private void closeConnection(Socket socket, ObjectOutputStream outputStream, ObjectInputStream inputStream) throws IOException {
        outputStream.close();
        inputStream.close();
        socket.close();
    }
}
