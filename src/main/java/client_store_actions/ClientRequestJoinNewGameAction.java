package client_store_actions;

import common.StoreAction;

/**
 * An action for signalling the request of the action of creating and joining a new game room
 */
public class ClientRequestJoinNewGameAction extends StoreAction {

    private final String playerName;
    private final String mapName;
    public ClientRequestJoinNewGameAction(String mapName, String playerName) {
        super("@CLIENT_REQUEST_JOIN_NEW_GAME","@CLIENT_GROUP");
        this.mapName = mapName;
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getMapName() {
        return mapName;
    }

    @Override
    public String toString() {
        return "ClientRequestJoinNewGameAction{" +
                "playerName='" + playerName + '\'' +
                ", mapName='" + mapName + '\'' +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}
