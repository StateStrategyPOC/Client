package client_store_actions;


import common.StoreAction;

/**
 * Created by giorgiopea on 25/03/17.
 */
public class ClientStartGameAction extends StoreAction {

    private final String gameMapName;

    public ClientStartGameAction(String gameMapName) {
        super("@CLIENT_START_GAME","@CLIENT_GROUP");
        this.gameMapName = gameMapName;
    }


    public String getGameMapName() {
        return gameMapName;
    }
}
