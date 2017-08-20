package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import common.StoreAction;
import client_store_actions.ClientSetPlayerStateAction;
import common.PlayerState;

public class SetPlayerStateStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientSetPlayerStateAction castedAction = (ClientSetPlayerStateAction) action;
        state.getPlayer().setPlayerState(castedAction.getPlayerState());
        return state;
    }
}
