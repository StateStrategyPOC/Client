package client_side_policies;

import client_store.ClientState;
import client_store.ClientStore;
import client_store.SidePolicy;
import common.StoreAction;
import client_store_actions.ClientEndTurnAction;
import client_store_actions.ClientSetCurrentReqRespNotificationAction;
import common.RRNotification;

public class RequestForceEndTurnSidePolicy implements SidePolicy {

    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        RRNotification clientNotification = new RRNotification(true,"You have taken too much to act, you will skip your turn",null,null,null,null,null,null,null);
        CLIENT_STORE.propagateAction(new ClientSetCurrentReqRespNotificationAction(clientNotification));
        CLIENT_STORE.propagateAction(new ClientEndTurnAction());
    }
}
