package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import common.StoreAction;
import client_store_actions.ClientSetAvailableGamesAction;

public class SetAvailableGamesStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientSetAvailableGamesAction castedAction = (ClientSetAvailableGamesAction) action;
        state.setAvailableGames(castedAction.getAvailableGames());
        return state;
    }
}
