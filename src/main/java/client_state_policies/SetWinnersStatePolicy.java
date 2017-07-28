package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import client_store.StoreAction;
import client_store_actions.ClientSetWinnersAction;

public class SetWinnersStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientSetWinnersAction castedAction = (ClientSetWinnersAction) action;
        state.setAliensHaveWon(castedAction.isAliensHaveWon());
        state.setHumansHaveWon(castedAction.isHumansHaveWon());
        return state;
    }
}
