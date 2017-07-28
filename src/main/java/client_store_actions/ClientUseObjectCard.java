package client_store_actions;

import common.ObjectCard;
import client_store.StoreAction;

/**
 * Created by giorgiopea on 25/03/17.
 */
public class ClientUseObjectCard extends StoreAction {
    private static String type = "@CLIENT_USE_OBJECT_CARD";
    public static String getType(){
        return type;
    }
    private final ObjectCard objectCard;

    public ClientUseObjectCard(ObjectCard objectCard) {
        super("@CLIENT_USE_OBJECT_CARD","@CLIENT_GROUP");
        this.objectCard = objectCard;
    }

    public ObjectCard getObjectCard() {
        return objectCard;
    }
}
