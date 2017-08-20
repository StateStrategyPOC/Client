package client_store_actions;

import common.StoreAction;

/**
 * An action for signalling the starting of the game turn
 */
public class ClientStartTurnAction extends StoreAction {
    public ClientStartTurnAction() {
        super("@CLIENT_START_TURN","@CLIENT_GROUP");
    }

    @Override
    public String toString() {
        return "ClientStartTurnAction{" +
                "actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}
