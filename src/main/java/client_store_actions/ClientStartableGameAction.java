package client_store_actions;

import client_store.StoreAction;

/**
 * Created by giorgiopea on 02/04/17.
 */
public class ClientStartableGameAction extends StoreAction {
    private static String type = "@CLIENT_STARTABLE_GAME";
    public static String getType(){
        return type;
    }
    public ClientStartableGameAction() {
        super("@CLIENT_STARTABLE_GAME");
    }
}
