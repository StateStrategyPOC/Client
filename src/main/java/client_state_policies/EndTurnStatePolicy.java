package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import client_store.StoreAction;

public class EndTurnStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        state.setMyTurn(false);
        state.getPlayer().setHasMoved(false);
        state.getPlayer().setAdrenalined(false);
        state.getPlayer().setSedated(false);
        state.setAskAttack(false);
        state.setAskLights(false);
        return state;
    }
}
