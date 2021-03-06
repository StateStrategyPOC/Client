package client_store_actions;

import common.StoreAction;

/**
 * An action for signalling the request of the action of joining a game room
 */
public class ClientRequestJoinGameAction extends StoreAction{
    private final int gameId;
    private final String playerName;

    public ClientRequestJoinGameAction(int gameId, String playerName) {
        super("@CLIENT_REQUEST_JOIN_GAME","@CLIENT_GROUP");
        this.gameId = gameId;
        this.playerName = playerName;
    }

    public int getGameId() {
        return gameId;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public String toString() {
        return "ClientRequestJoinGameAction{" +
                "gameId=" + gameId +
                ", playerName='" + playerName + '\'' +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}
