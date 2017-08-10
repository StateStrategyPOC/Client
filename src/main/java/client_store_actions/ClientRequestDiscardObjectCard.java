package client_store_actions;

import common.StoreAction;
import common.ObjectCard;

public class ClientRequestDiscardObjectCard extends StoreAction {
    private final ObjectCard objectCard;

    public ClientRequestDiscardObjectCard(ObjectCard objectCard) {
        super("@CLIENT_REQUEST_DISCARD_OBJ_CARD","@CLIENT_GROUP");
        this.objectCard = objectCard;
    }

    public ObjectCard getObjectCard() {
        return objectCard;
    }
}
