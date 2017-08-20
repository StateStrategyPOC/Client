package client_store_actions;

import common.StoreAction;

/**
 * An action for signalling a new status for the connection with the server
 */
public class ClientSetConnectionActiveAction extends StoreAction {

    private final boolean isConnectionActive;

    public ClientSetConnectionActiveAction(boolean isConnectionActive) {
        super("@CLIENT_SET_CONNECTION_ACTIVE","@CLIENT_GROUP");
        this.isConnectionActive = isConnectionActive;
    }

    public boolean isConnectionActive() {
        return isConnectionActive;
    }

    @Override
    public String toString() {
        return "ClientSetConnectionActiveAction{" +
                "isConnectionActive=" + isConnectionActive +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}
