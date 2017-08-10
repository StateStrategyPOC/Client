package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import common.StoreAction;
import client_store_actions.ClientSetDrawnSectorObjectCard;

public class SetDrawnSectorObjectCardStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientSetDrawnSectorObjectCard castedAction = (ClientSetDrawnSectorObjectCard) action;
        if (castedAction.getDrawnObjectCard() != null){
            state.getPlayer().getPrivateDeck().addCard(castedAction.getDrawnObjectCard());
        }
        return state;
    }
}
