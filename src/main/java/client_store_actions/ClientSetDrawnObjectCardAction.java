package client_store_actions;

import common.ObjectCard;
import client_store.StoreAction;

/**
 * Created by giorgiopea on 06/04/17.
 */
public class ClientSetDrawnObjectCardAction extends StoreAction {

    private final ObjectCard drawnObjectCard;

    public ClientSetDrawnObjectCardAction(ObjectCard objectCard) {
        super("@CLIENT_SET_DRAWN_OBJECT_CARD");
        this.drawnObjectCard = objectCard;
    }

    public ObjectCard getDrawnObjectCard() {
        return drawnObjectCard;
    }
}
