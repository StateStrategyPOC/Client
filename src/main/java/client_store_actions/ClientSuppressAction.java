package client_store_actions;

import common.StoreAction;

/**
 * An action for signalling the use of a Sedatives Object Card
 */
public class ClientSuppressAction extends StoreAction {

    private final boolean isSedated;

    public ClientSuppressAction(boolean isSedated) {
        super("@CLIENT_SUPPRESS","@CLIENT_GROUP");
        this.isSedated = isSedated;
    }

    public boolean isSedated() {
        return isSedated;
    }
}
