package client_store_actions;

import common.ObjectCard;
import client_store.StoreAction;

/**
 * Created by giorgiopea on 06/04/17.
 */
public class ClientSetDrawnObjectCardAction extends StoreAction {
    private static String type = "@CLIENT_SET_DRAWN_OBJECT_CARD";
    public static String getType(){
        return type;
    }
    private final ObjectCard drawnObjectCard;

    public ClientSetDrawnObjectCardAction(ObjectCard objectCard) {
        super("@CLIENT_SET_DRAWN_OBJECT_CARD","@CLIENT_GROUP");
        this.drawnObjectCard = objectCard;
    }

    public ObjectCard getDrawnObjectCard() {
        return drawnObjectCard;
    }
}
