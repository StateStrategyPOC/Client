package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import client_store.StoreAction;
import client_store_actions.ClientRequestDiscardObjectCardAction;

public class DiscardObjectCardStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientRequestDiscardObjectCardAction castedAction = (ClientRequestDiscardObjectCardAction) action;
        state.getPlayer().getPrivateDeck().removeCard(castedAction.getDiscardedObjectCard());
        return state;
    }
}
