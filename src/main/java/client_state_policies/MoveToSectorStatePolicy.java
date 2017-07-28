package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import client_store.StoreAction;
import client_store_actions.ClientMoveToSectorAction;

public class MoveToSectorStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientMoveToSectorAction castedAction = (ClientMoveToSectorAction) action;
        state.getPlayer().setHasMoved(!castedAction.getTargetSector().equals(state.getPlayer().getCurrentSector()));
        state.getPlayer().setCurrentSector(castedAction.getTargetSector());
        return state;
    }
}
