package client_store_actions;

import common.StoreAction;

/**
 * Created by giorgiopea on 25/03/17.
 *
 */
public class ClientEndTurnAction extends StoreAction {
    private static String type = "@CLIENT_END_TURN";
    public static String getType(){
        return type;
    }
    public ClientEndTurnAction() {
        super("@CLIENT_END_TURN","@CLIENT_GROUP");
    }
}
