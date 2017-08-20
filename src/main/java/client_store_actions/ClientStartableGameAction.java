package client_store_actions;

import common.StoreAction;

/**
 * An action for signalling that the Player can start the game on demand
 */
public class ClientStartableGameAction extends StoreAction {
    public ClientStartableGameAction() {
        super("@CLIENT_STARTABLE_GAME","@CLIENT_GROUP");
    }

    @Override
    public String toString() {
        return "ClientStartableGameAction{" +
                "actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}
