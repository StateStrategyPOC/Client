package client_store_actions;

import common.StoreAction;

/**
 * An action for signalling the ending of the Player's game turn
 */
public class ClientEndTurnAction extends StoreAction {
    public ClientEndTurnAction() {
        super("@CLIENT_END_TURN","@CLIENT_GROUP");
    }

    @Override
    public String toString() {
        return "ClientEndTurnAction{" +
                "actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}
