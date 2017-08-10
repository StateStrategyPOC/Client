package client_store_actions;

import common.StoreAction;
import common.ObjectCard;

public class ClientRequestUseObjectCard extends StoreAction {

    private static String type = "@CLIENT_REQUEST_USE_OBJECT_CARD";
    public static String getType(){
        return type;
    }

    private final ObjectCard objectCard;
    public ClientRequestUseObjectCard(ObjectCard objectCard) {
        super("@CLIENT_REQUEST_USE_OBJECT_CARD","@CLIENT_GROUP");
        this.objectCard = objectCard;
    }

    public ObjectCard getObjectCard() {
        return objectCard;
    }
}
