package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import common.StoreAction;
import client_store_actions.ClientUseObjectCardAction;

public class UseObjectCardStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientUseObjectCardAction castedAction = (ClientUseObjectCardAction) action;
        state.getPlayer().getPrivateDeck().removeCard(castedAction.getObjectCard());
        return state;
    }
}
