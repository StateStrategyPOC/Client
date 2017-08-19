package client_store_actions;

import common.PlayerState;
import common.StoreAction;

/**
 * Created by giorgiopea on 07/04/17.
 */
public class ClientSetPlayerState extends StoreAction {

    private final PlayerState playerState;

    public ClientSetPlayerState(PlayerState playerState)
    {
        super("@CLIENT_SET_PLAYER_STATE","@CLIENT_GROUP");
        this.playerState = playerState;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }
}
