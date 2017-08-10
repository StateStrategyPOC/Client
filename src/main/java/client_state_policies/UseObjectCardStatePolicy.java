package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import common.StoreAction;
import client_store_actions.ClientUseObjectCard;

public class UseObjectCardStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientUseObjectCard castedAction = (ClientUseObjectCard) action;
        state.getPlayer().getPrivateDeck().removeCard(castedAction.getObjectCard());
        return state;
    }
}
