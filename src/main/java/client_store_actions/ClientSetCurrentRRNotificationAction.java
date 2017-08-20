package client_store_actions;

import common.RRNotification;
import common.StoreAction;

/**
 * An action for signalling the availability of a response to the last request to the server
 */
public class ClientSetCurrentRRNotificationAction extends StoreAction {
    private final RRNotification rrNotification;

    public ClientSetCurrentRRNotificationAction(RRNotification rrNotification) {
        super("@CLIENT_SET_RR","@CLIENT_GROUP");
        this.rrNotification = rrNotification;
    }

    public RRNotification getRrNotification() {
        return rrNotification;
    }

}
