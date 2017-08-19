package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import common.StoreAction;
import client_store_actions.ClientDiscardObjectCardAction;

public class DiscardObjectCardStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientDiscardObjectCardAction castedAction = (ClientDiscardObjectCardAction) action;
        state.getPlayer().getPrivateDeck().removeCard(castedAction.getDiscardedObjectCard());
        return state;
    }
}
