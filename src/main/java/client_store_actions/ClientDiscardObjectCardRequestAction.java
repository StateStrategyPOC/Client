package client_store_actions;

import client_store.StoreAction;
import common.ObjectCard;

import java.io.ObjectOutputStream;

public class ClientDiscardObjectCardRequestAction extends StoreAction {
    private final ObjectCard objectCard;
    public ClientDiscardObjectCardRequestAction(ObjectCard objectCard) {
        super("@CLIENT_REQUEST_DISCARD_OBJ_CARD");
        this.objectCard = objectCard;
    }

    public ObjectCard getObjectCard() {
        return objectCard;
    }
}
