package client_store_actions;

import common.StoreAction;

/**
 * Created by giorgiopea on 25/03/17.
 */
public class ClientTeleportToStartingSectorAction extends StoreAction {
    private static String type = "@CLIENT_TELEPORT_TO_STARTING_SECTOR";
    public static String getType(){
        return type;
    }
    public ClientTeleportToStartingSectorAction() {
        super("@CLIENT_TELEPORT_TO_STARTING_SECTOR","@CLIENT_GROUP");
    }
}
