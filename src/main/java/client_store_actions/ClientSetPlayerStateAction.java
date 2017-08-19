package client_store_actions;

import common.PlayerState;
import common.StoreAction;

/**
 * An action for signalling a new state for the Player
 */
public class ClientSetPlayerStateAction extends StoreAction {

    private final PlayerState playerState;

    public ClientSetPlayerStateAction(PlayerState playerState)
    {
        super("@CLIENT_SET_PLAYER_STATE","@CLIENT_GROUP");
        this.playerState = playerState;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }
}
