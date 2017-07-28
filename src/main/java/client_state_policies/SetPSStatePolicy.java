package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import client_store.StoreAction;
import client_store_actions.ClientSetCurrentPubSubNotificationAction;

public class SetPSStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientSetCurrentPubSubNotificationAction castedAction = (ClientSetCurrentPubSubNotificationAction) action;
        state.setCurrentPubSubNotification(castedAction.getPsNotification());
        return state;
    }
}
