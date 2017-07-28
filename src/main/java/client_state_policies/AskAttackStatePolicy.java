package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import client_store.StoreAction;
import client_store_actions.ClientAskAttackAction;

public class AskAttackStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientAskAttackAction castedAction = (ClientAskAttackAction) action;
        state.setAskAttack(castedAction.isToBeAsked());
        return state;
    }
}
