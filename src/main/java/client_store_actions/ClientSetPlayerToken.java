package client_store_actions;

import client_store.StoreAction;
import common.Player;
import common.PlayerToken;

public class ClientSetPlayerToken extends StoreAction {
    private PlayerToken playerToken;

    public ClientSetPlayerToken(PlayerToken playerToken) {
        super("@CLIENT_SET_PLAYER_TOKEN");
        this.playerToken = playerToken;
    }

    public PlayerToken getPlayerToken() {
        return playerToken;
    }
}
