package client_store;


import common.StoreAction;

import java.util.Observable;

/**
 * Represents a wrapper to the {@link ClientState} so that its changes can be observed by means of propagated Actions
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
