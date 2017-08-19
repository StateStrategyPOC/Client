package client_store_actions;

import common.ObjectCard;
import common.StoreAction;

/**
 * Created by giorgiopea on 07/04/17.
 */
public class ClientRequestDiscardObjectCardAction extends StoreAction {

    private final ObjectCard discardedObjectCard;

    public ClientRequestDiscardObjectCardAction(ObjectCard objectCard) {
        super("@CLIENT_DISCARD_OBJECT_CARD","@CLIENT_GROUP");
        this.discardedObjectCard = objectCard;
    }

    public ObjectCard getDiscardedObjectCard() {
        return discardedObjectCard;
    }
}
