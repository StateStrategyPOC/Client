package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import common.StoreAction;

public class TeleportStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        state.getPlayer().setCurrentSector(state.getGameMap().getHumanSector());
        return state;
    }
}
