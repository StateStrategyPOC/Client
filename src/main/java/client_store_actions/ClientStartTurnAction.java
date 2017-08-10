package client_store_actions;

import common.StoreAction;

/**
 * Created by giorgiopea on 28/03/17.
 */
public class ClientStartTurnAction extends StoreAction {
    private static String type = "@CLIENT_START_TURN";
    public static String getType(){
        return type;
    }
    public ClientStartTurnAction() {
        super("@CLIENT_START_TURN","@CLIENT_GROUP");
    }
}
