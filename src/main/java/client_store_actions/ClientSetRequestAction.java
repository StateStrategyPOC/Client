package client_store_actions;

import client.ActionOnTheWire;
import client_store.StoreAction;

public class ClientSetRequestAction extends StoreAction {

    private ActionOnTheWire request;
    public ClientSetRequestAction(ActionOnTheWire request) {
        super("@CLIENT_SET_REQUEST");
    }

    public ActionOnTheWire getRequest() {
        return request;
    }
}
