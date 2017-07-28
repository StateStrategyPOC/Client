package client_store_actions;

import common.RRClientNotification;
import client_store.StoreAction;

/**
 * Created by giorgiopea on 29/03/17.
 */
public class ClientSetCurrentReqRespNotificationAction extends StoreAction {
    private static String type = "@CLIENT_SET_CURRENT_REQRESP_NOTIFICATION";
    public static String getType(){
        return type;
    }
    private final RRClientNotification rrClientNotification;

    public ClientSetCurrentReqRespNotificationAction(RRClientNotification rrClientNotification) {
        super("@CLIENT_SET_CURRENT_REQRESP_NOTIFICATION","@CLIENT_GROUP");
        this.rrClientNotification = rrClientNotification;
    }

    public RRClientNotification getRrClientNotification() {
        return rrClientNotification;
    }
}
