package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import common.StoreAction;

public class BlankPolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        return state;
    }
}
