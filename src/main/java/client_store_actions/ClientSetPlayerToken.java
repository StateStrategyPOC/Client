package client_store_actions;

import common.StoreAction;
import common.PlayerToken;

public class ClientSetPlayerToken extends StoreAction {

    private static String type = "@CLIENT_SET_PLAYER_TOKEN";

    public static String getType(){
        return type;
    }

    private PlayerToken playerToken;

    public ClientSetPlayerToken(PlayerToken playerToken) {
        super("@CLIENT_SET_PLAYER_TOKEN","@CLIENT_GROUP");
        this.playerToken = playerToken;
    }

    public PlayerToken getPlayerToken() {
        return playerToken;
    }
}
