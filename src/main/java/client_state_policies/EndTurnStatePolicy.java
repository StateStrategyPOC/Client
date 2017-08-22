package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import common.StoreAction;

public class EndTurnStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        state.getPlayer().setHasMoved(false);
        state.getPlayer().setAdrenalined(false);
        state.getPlayer().setSedated(false);
        return state;
    }
}
