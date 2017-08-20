package client_store_actions;

import common.ObjectCard;
import common.StoreAction;

/**
 * An action for signalling the discard of an Object Card
 */
public class ClientDiscardObjectCardAction extends StoreAction {

    private final ObjectCard discardedObjectCard;

    public ClientDiscardObjectCardAction(ObjectCard objectCard) {
        super("@CLIENT_DISCARD_OBJECT_CARD","@CLIENT_GROUP");
        this.discardedObjectCard = objectCard;
    }

    public ObjectCard getDiscardedObjectCard() {
        return discardedObjectCard;
    }

    @Override
    public String toString() {
        return "ClientDiscardObjectCardAction{" +
                "discardedObjectCard=" + discardedObjectCard +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}
