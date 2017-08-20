package client_store_actions;

import common.ObjectCard;
import common.StoreAction;

/**
 * An action for signalling the use of an Object Card
 */
public class ClientUseObjectCardAction extends StoreAction {
    private final ObjectCard objectCard;

    public ClientUseObjectCardAction(ObjectCard objectCard) {
        super("@CLIENT_USE_OBJECT_CARD","@CLIENT_GROUP");
        this.objectCard = objectCard;
    }

    public ObjectCard getObjectCard() {
        return objectCard;
    }

    @Override
    public String toString() {
        return "ClientUseObjectCardAction{" +
                "objectCard=" + objectCard +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}
