package client_store_actions;

import common.PSClientNotification;
import client_store.StoreAction;

/**
 * Created by giorgiopea on 29/03/17.
 */
public class ClientSetCurrentPubSubNotificationAction extends StoreAction {
    private static String type = "@CLIENT_SET_CURRENT_PUBSUB_NOTIFICATION";
    public static String getType(){
        return type;
    }
    private final PSClientNotification psNotification;

    public ClientSetCurrentPubSubNotificationAction(PSClientNotification psNotification) {
        super("@CLIENT_SET_CURRENT_PUBSUB_NOTIFICATION");
        this.psNotification = psNotification;
    }

    public PSClientNotification getPsNotification() {
        return psNotification;
    }
}
