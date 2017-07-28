package client_store_actions;

import client_store.StoreAction;

public class ClientRequestJoinGameAction extends StoreAction{
    private static String type = "@CLIENT_REQUEST_JOIN_GAME";
    public static String getType(){
        return type;
    }
    private final int gameId;
    private final String playerName;

    public ClientRequestJoinGameAction(int gameId, String playerName) {
        super("@CLIENT_JOIN_GAME");
        this.gameId = gameId;
        this.playerName = playerName;
    }

    public int getGameId() {
        return gameId;
    }

    public String getPlayerName() {
        return playerName;
    }
}
