package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import common.StoreAction;
import client_store_actions.ClientSetInRoomAction;

public class InRoomStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientSetInRoomAction castedAction = (ClientSetInRoomAction) action;
        state.setInRoom(castedAction.isInRoom());
        return state;
    }
}
