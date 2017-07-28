package client;

import client_store.ClientStore;
import client_store.StoreAction;
import client_store_actions.ClientSetConnectionActiveAction;
import client_store_actions.ClientSetCurrentReqRespNotificationAction;
import client_store_actions.ClientSetRequestAction;
import common.RRClientNotification;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class ReqRespHandler implements Observer {

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
        this.CLIENT_STORE.observeState(this);
    }

    private void initRequestResponse(ActionOnTheWire actionOnTheWire){
        try {
            Socket socket = new Socket(CLIENT_STORE.getState().getHost(), CLIENT_STORE.getState().getTcpPort());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.flush();
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            this.sendRequest(actionOnTheWire,outputStream);
            this.getResponse(inputStream);
            if(actionOnTheWire.getIdentifierMapper().equals(ServerMethodsNameProvider.getInstance().subscribe())){
                PubSubHandler pubSubHandler = new PubSubHandler(socket,inputStream);
                pubSubHandler.start();
            }
            else {
                this.closeConnection(socket,outputStream,inputStream);
            }
        } catch (IOException | ClassNotFoundException e) {
            this.CLIENT_STORE.propagateAction(new ClientSetConnectionActiveAction(false));
        }
    }

    private void getResponse(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        RRClientNotification response = (RRClientNotification) inputStream.readObject();
        this.CLIENT_STORE.propagateAction(new ClientSetCurrentReqRespNotificationAction(response));
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

    @Override
    public void update(Observable o, Object arg) {
        StoreAction dispatchedAction = (StoreAction) arg;
        switch(dispatchedAction.getActionIdentifier()){
            case "@CLIENT_SET_REQUEST":
                this.setRequest(dispatchedAction);
                break;
        }
    }

    private void setRequest(StoreAction action){
        ClientSetRequestAction castedAction = (ClientSetRequestAction) action;
        this.initRequestResponse(castedAction.getRequest());
    }
}
