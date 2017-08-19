package client_store_actions;

import common.PlayerToken;
import common.StoreAction;

/**
 * An action for signalling the availability of an in game identifier for the Player
 */
public class ClientSetPlayerAction extends StoreAction {


    private final String playerName;
    private final PlayerToken playerToken;

    public ClientSetPlayerAction(String playerName, PlayerToken playerToken) {
        super("@CLIENT_SET_PLAYER","@CLIENT_GROUP");
        this.playerName = playerName;
        this.playerToken = playerToken;
    }

    public String getPlayerName() {
        return playerName;
    }

    public PlayerToken getPlayerToken() {
        return playerToken;
    }
}
