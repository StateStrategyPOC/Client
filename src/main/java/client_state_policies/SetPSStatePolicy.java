package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import common.StoreAction;
import client_store_actions.ClientSetCurrentPSNotificationAction;

public class SetPSStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientSetCurrentPSNotificationAction castedAction = (ClientSetCurrentPSNotificationAction) action;
        state.setCurrentPubSubNotification(castedAction.getPsNotification());
        return state;
    }
}
