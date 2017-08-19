package client_store_actions;

import common.StoreAction;

/**
 * An action for signalling the request of the in game action of ending the game turn
 */
public class ClientRequestEndTurnAction extends StoreAction {
    public ClientRequestEndTurnAction() {
        super("@CLIENT_REQUEST_END_TURN","@CLIENT_GROUP");
    }
}
