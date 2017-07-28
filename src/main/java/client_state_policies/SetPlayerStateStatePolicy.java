package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import client_store.StoreAction;
import client_store_actions.ClientSetPlayerState;
import common.PlayerState;

public class SetPlayerStateStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientSetPlayerState castedAction = (ClientSetPlayerState) action;
        state.getPlayer().setPlayerState(castedAction.getPlayerState());
        if (state.getPlayer().getPlayerState().equals(PlayerState.DEAD) ||  state.getPlayer().getPlayerState().equals(PlayerState.ESCAPED)){
            state.setGameStarted(false);
        }
        return state;
    }
}