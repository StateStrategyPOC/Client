package client_store_actions;

import common.PSNotification;
import common.StoreAction;

/**
 * An action for signalling the availability of an asynchronous notification from the server
 */
public class ClientSetCurrentPSNotificationAction extends StoreAction {
    private final PSNotification psNotification;

    public ClientSetCurrentPSNotificationAction(PSNotification psNotification) {
        super("@CLIENT_SET_PS","@CLIENT_GROUP");
        this.psNotification = psNotification;
    }

    public PSNotification getPsNotification() {
        return psNotification;
    }
}
