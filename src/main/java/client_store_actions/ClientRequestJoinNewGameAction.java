package client_store_actions;

import client_store.StoreAction;

public class ClientRequestJoinNewGameAction extends StoreAction {

    private static String type = "@CLIENT_REQUEST_JOIN_NEW_GAME";
    public static String getType(){
        return type;
    }

    private String playerName;
    private String mapName;
    public ClientRequestJoinNewGameAction(String mapName, String playerName) {
        super("@CLIENT_JOIN_NEW_GAME","@CLIENT_GROUP");
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
