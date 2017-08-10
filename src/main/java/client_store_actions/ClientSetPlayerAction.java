package client_store_actions;

import common.PlayerToken;
import common.StoreAction;

/**
 * Created by giorgiopea on 25/03/17.
 */
public class ClientSetPlayerAction extends StoreAction {

    private static String type = "@CLIENT_SET_PLAYER";
    public static String getType(){
        return type;
    }
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
