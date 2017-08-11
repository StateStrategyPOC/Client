package client_store_actions;

import common.StoreAction;
import common.ObjectCard;

public class ClientRequestUseObjectCard extends StoreAction {

    private final ObjectCard objectCard;
    public ClientRequestUseObjectCard(ObjectCard objectCard) {
        super("@CLIENT_REQUEST_USE_OBJECT_CARD","@CLIENT_GROUP");
        this.objectCard = objectCard;
    }

    public ObjectCard getObjectCard() {
        return objectCard;
    }
}
