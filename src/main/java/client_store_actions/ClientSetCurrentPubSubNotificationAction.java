package client_store_actions;

import common.PSClientNotification;
import client_store.StoreAction;

/**
 * Created by giorgiopea on 29/03/17.
 */
public class ClientSetCurrentPubSubNotificationAction extends StoreAction {
    private final PSClientNotification psNotification;

    public ClientSetCurrentPubSubNotificationAction(PSClientNotification psNotification) {
        super("@CLIENT_SET_PS","@CLIENT_GROUP");
        this.psNotification = psNotification;
    }

    public PSClientNotification getPsNotification() {
        return psNotification;
    }
}
