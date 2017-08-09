package client_store;


import java.util.Observable;

/**
 * Created by giorgiopea on 24/03/17.
 */
public class ObservableClientState extends Observable {
    private ClientState clientState;

    public ObservableClientState(ClientState serverState) {
        this.clientState = serverState;
    }

    public ClientState getClientState() {
        return clientState;
    }

    public void setState(ClientState clientState, StoreAction lastAction){
        this.clientState = clientState;
        this.setChanged();
        this.notifyObservers(lastAction);
    }

    @Override
    public String toString() {
        return clientState.toString();
    }
}
