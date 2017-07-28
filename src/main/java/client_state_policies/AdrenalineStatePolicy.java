package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import client_store.StoreAction;

public class AdrenalineStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        state.getPlayer().setAdrenalined(true);
        return state;
    }
}
