package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import common.StoreAction;
import client_store_actions.ClientSetConnectionActiveAction;

public class SetConnectionActiveStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientSetConnectionActiveAction castedAction = (ClientSetConnectionActiveAction) action;
        state.setConnectionActive(castedAction.isConnectionActive());
        return state;
    }
}
