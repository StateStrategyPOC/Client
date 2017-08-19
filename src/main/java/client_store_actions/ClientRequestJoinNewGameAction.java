package client_store_actions;

import common.StoreAction;

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
}
