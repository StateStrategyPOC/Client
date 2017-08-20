package client_store_actions;

import common.StoreAction;

/**
 * An action for signalling if the Player is in a game room
 */
public class ClientSetInRoomAction extends StoreAction {

    private final boolean inRoom;

    public ClientSetInRoomAction(boolean inRoom) {
        super("@CLIENT_SET_IN_ROOM","@CLIENT_GROUP");
        this.inRoom = inRoom;
    }

    public boolean isInRoom() {
        return inRoom;
    }

    @Override
    public String toString() {
        return "ClientSetInRoomAction{" +
                "inRoom=" + inRoom +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}
