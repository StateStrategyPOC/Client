package client_store_actions;

import common.PSNotification;
import common.StoreAction;

/**
 * Created by giorgiopea on 29/03/17.
 */
public class ClientSetCurrentPubSubNotificationAction extends StoreAction {
    private final PSNotification psNotification;

    public ClientSetCurrentPubSubNotificationAction(PSNotification psNotification) {
        super("@CLIENT_SET_PS","@CLIENT_GROUP");
        this.psNotification = psNotification;
    }

    public PSNotification getPsNotification() {
        return psNotification;
    }
}
