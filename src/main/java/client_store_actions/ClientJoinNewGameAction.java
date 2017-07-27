package client_store_actions;

import client_store.StoreAction;

public class ClientJoinNewGameAction extends StoreAction {
    private String playerName;
    private String mapName;
    public ClientJoinNewGameAction(String mapName, String playerName) {
        super("@CLIENT_JOIN_NEW_GAME");
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getMapName() {
        return mapName;
    }
}
