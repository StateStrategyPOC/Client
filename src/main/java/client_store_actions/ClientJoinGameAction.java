package client_store_actions;

import client_store.StoreAction;

public class ClientJoinGameAction extends StoreAction{

    private final int gameId;
    private final String playerName;

    public ClientJoinGameAction(int gameId, String playerName) {
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
