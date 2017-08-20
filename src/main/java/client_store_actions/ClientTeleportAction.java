package client_store_actions;

import common.StoreAction;

/**
 * An action for signalling the use of a Teleport Object Card
 */
public class ClientTeleportAction extends StoreAction {

    public ClientTeleportAction() {
        super("@CLIENT_TELEPORT_TO_STARTING_SECTOR","@CLIENT_GROUP");
    }

    @Override
    public String toString() {
        return "ClientTeleportAction{" +
                "actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}
