package client_store_actions;


import client_store.StoreAction;

/**
 * Created by giorgiopea on 25/03/17.
 */
public class ClientStartGameAction extends StoreAction {
    private static String type = "@CLIENT_START_GAME";
    public static String getType(){
        return type;
    }
    private final String gameMapName;

    public ClientStartGameAction(String gameMapName) {
        super("@CLIENT_START_GAME","@CLIENT_GROUP");
        this.gameMapName = gameMapName;
    }


    public String getGameMapName() {
        return gameMapName;
    }
}
