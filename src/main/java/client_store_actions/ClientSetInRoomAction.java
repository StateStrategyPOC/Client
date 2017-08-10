package client_store_actions;

import common.StoreAction;

public class ClientSetInRoomAction extends StoreAction {

    private final boolean inRoom;

    public ClientSetInRoomAction(boolean inRoom) {
        super("@CLIENT_SET_IN_ROOM","@CLIENT_GROUP");
        this.inRoom = inRoom;
    }

    public boolean isInRoom() {
        return inRoom;
    }
}
