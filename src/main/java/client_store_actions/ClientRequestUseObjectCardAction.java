package client_store_actions;

import common.StoreAction;
import common.ObjectCard;

/**
 * An action for signalling the request of the in game action of using an Object Card
 */
public class ClientRequestUseObjectCardAction extends StoreAction {

    private final ObjectCard objectCard;
    public ClientRequestUseObjectCardAction(ObjectCard objectCard) {
        super("@CLIENT_REQUEST_USE_OBJECT_CARD","@CLIENT_GROUP");
        this.objectCard = objectCard;
    }

    public ObjectCard getObjectCard() {
        return objectCard;
    }
}
