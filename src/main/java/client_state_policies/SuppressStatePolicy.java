package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import common.StoreAction;
import client_store_actions.ClientSuppressAction;

public class SuppressStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientSuppressAction castedAction = (ClientSuppressAction) action;
        state.getPlayer().setSedated(castedAction.isSedated());
        return state;
    }
}
