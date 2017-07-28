package client_store_actions;

import client.ActionOnTheWire;
import client_store.StoreAction;

public class ClientSetRequestAction extends StoreAction {
    private static String type = "@CLIENT_SET_REQUEST";
    public static String getType(){
        return type;
    }
    private final ActionOnTheWire request;
    public ClientSetRequestAction(ActionOnTheWire request) {
        super("@CLIENT_SET_REQUEST","@CLIENT_GROUP");
        this.request = request;

    }

    public ActionOnTheWire getRequest() {
        return request;
    }
}
