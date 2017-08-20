package client_store_actions;

import common.StoreAction;

/**
 * An action for signalling the request of the action of starting a game on demand
 */
public class ClientOnDemandGameStartAction extends StoreAction {
    public ClientOnDemandGameStartAction() {
        super("@CLIENT_ON_DEMAND_GAME_START","@CLIENT_GROUP");
    }

    @Override
    public String toString() {
        return "ClientOnDemandGameStartAction{" +
                "actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}
