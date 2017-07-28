package client_store_actions;

import client_store.StoreAction;
import common.ObjectCard;

public class ClientRequestUseObjectCard extends StoreAction {

    private static String type = "@CLIENT_REQUEST_USE_OBJECT_CARD";
    public static String getType(){
        return type;
    }

    private final ObjectCard objectCard;
    public ClientRequestUseObjectCard(ObjectCard objectCard) {
        super("@CLIENT_REQUEST_USE_OBJECT_CARD");
        this.objectCard = objectCard;
    }

    public ObjectCard getObjectCard() {
        return objectCard;
    }
}
