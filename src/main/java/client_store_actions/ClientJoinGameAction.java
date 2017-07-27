package client_store_actions;

import client_store.StoreAction;

public class ClientJoinGameAction extends StoreAction{

    private String gameId;
    private String playerName;

    public ClientJoinGameAction() {
        super("@CLIENT_JOIN_GAME");
    }

    public String getGameId() {
        return gameId;
    }

    public String getPlayerName() {
        return playerName;
    }
}
