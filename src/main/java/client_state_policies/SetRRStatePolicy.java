package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import client_store.StoreAction;
import client_store_actions.ClientSetCurrentReqRespNotificationAction;

public class SetRRStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientSetCurrentReqRespNotificationAction castedAction = (ClientSetCurrentReqRespNotificationAction) action;
        state.setCurrentReqRespNotification(castedAction.getRrClientNotification());
        return state;
    }
}
