package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import common.StoreAction;
import client_store_actions.ClientSetRequestAction;

public class SetRequestStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientSetRequestAction castedAction = (ClientSetRequestAction) action;
        state.setRequest(castedAction.getRequest());
        return state;
    }
}
