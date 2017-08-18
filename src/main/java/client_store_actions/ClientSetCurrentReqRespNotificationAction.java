package client_store_actions;

import common.RRNotification;
import common.StoreAction;

/**
 * Created by giorgiopea on 29/03/17.
 */
public class ClientSetCurrentReqRespNotificationAction extends StoreAction {
    private final RRNotification rrNotification;

    public ClientSetCurrentReqRespNotificationAction(RRNotification rrNotification) {
        super("@CLIENT_SET_RR","@CLIENT_GROUP");
        this.rrNotification = rrNotification;
    }

    public RRNotification getRrNotification() {
        return rrNotification;
    }
}
