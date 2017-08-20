package client_store_actions;

import common.StoreAction;

/**
 * An action for signalling the request of the action of getting available game rooms
 */
public class ClientGetGamesAction extends StoreAction {


    public ClientGetGamesAction() {
        super("@CLIENT_GET_GAMES", "@CLIENT_GROUP");
    }

    @Override
    public String toString() {
        return "ClientGetGamesAction{" +
                "actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }

}
