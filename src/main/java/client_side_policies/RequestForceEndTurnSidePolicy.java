package client_side_policies;

import client_store.ClientState;
import client_store.ClientStore;
import client_store.SidePolicy;
import client_store.StoreAction;
import client_store_actions.ClientEndTurnAction;
import client_store_actions.ClientSetCurrentReqRespNotificationAction;
import common.RRClientNotification;

public class RequestForceEndTurnSidePolicy implements SidePolicy {

    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        RRClientNotification clientNotification = new RRClientNotification();
        clientNotification.setMessage("You have taken too much to act, you will skip your turn");
        CLIENT_STORE.propagateAction(new ClientSetCurrentReqRespNotificationAction(clientNotification));
        CLIENT_STORE.propagateAction(new ClientEndTurnAction());
    }
}
