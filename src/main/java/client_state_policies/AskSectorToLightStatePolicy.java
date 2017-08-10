package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import common.StoreAction;
import client_store_actions.ClientAskSectorToLightAction;

public class AskSectorToLightStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientAskSectorToLightAction castedAction = (ClientAskSectorToLightAction) action;
        state.setAskAttack(castedAction.isToBeAsked());
        return state;
    }
}
