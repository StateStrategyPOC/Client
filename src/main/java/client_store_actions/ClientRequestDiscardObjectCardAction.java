package client_store_actions;

import common.StoreAction;
import common.ObjectCard;

/**
 * An action for signalling the request of the in game action of discarding an Object Card
 */
public class ClientRequestDiscardObjectCardAction extends StoreAction {
    private final ObjectCard objectCard;

    public ClientRequestDiscardObjectCardAction(ObjectCard objectCard) {
        super("@CLIENT_REQUEST_DISCARD_OBJ_CARD","@CLIENT_GROUP");
        this.objectCard = objectCard;
    }

    public ObjectCard getObjectCard() {
        return objectCard;
    }
}
