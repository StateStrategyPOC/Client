package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import common.StoreAction;
import client_store_actions.ClientSetCurrentRRNotificationAction;

public class SetRRStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientSetCurrentRRNotificationAction castedAction = (ClientSetCurrentRRNotificationAction) action;
        state.setCurrentReqRespNotification(castedAction.getRrNotification());
        return state;
    }
}
