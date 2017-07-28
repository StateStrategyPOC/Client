package client_store_actions;

import client_store.StoreAction;
import common.ObjectCard;

import java.io.ObjectOutputStream;

public class ClientRequestDiscardObjectCard extends StoreAction {
    private static String type = "@CLIENT_REQUEST_DISCARD_OBJ_CARD";
    public static String getType(){
        return type;
    }
    private final ObjectCard objectCard;

    public ClientRequestDiscardObjectCard(ObjectCard objectCard) {
        super("@CLIENT_REQUEST_DISCARD_OBJ_CARD","@CLIENT_GROUP");
        this.objectCard = objectCard;
    }

    public ObjectCard getObjectCard() {
        return objectCard;
    }
}
