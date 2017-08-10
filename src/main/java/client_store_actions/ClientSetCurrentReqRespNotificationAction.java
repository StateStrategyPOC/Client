package client_store_actions;

import common.RRClientNotification;
import common.StoreAction;

/**
 * Created by giorgiopea on 29/03/17.
 */
public class ClientSetCurrentReqRespNotificationAction extends StoreAction {
    private final RRClientNotification rrClientNotification;

    public ClientSetCurrentReqRespNotificationAction(RRClientNotification rrClientNotification) {
        super("@CLIENT_SET_RR","@CLIENT_GROUP");
        this.rrClientNotification = rrClientNotification;
    }

    public RRClientNotification getRrClientNotification() {
        return rrClientNotification;
    }
}
