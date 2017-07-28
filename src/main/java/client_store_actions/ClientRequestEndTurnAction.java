package client_store_actions;

import client_store.StoreAction;

public class ClientRequestEndTurnAction extends StoreAction {
    private static String type = "@CLIENT_REQUEST_END_TURN";
    public static String getType(){
        return type;
    }
    public ClientRequestEndTurnAction() {
        super("@CLIENT_REQUEST_END_TURN","@CLIENT_GROUP");
    }
}
