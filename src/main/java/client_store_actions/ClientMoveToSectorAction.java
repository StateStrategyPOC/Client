package client_store_actions;

import client_store.StoreAction;
import common.Sector;

/**
 * Created by giorgiopea on 25/03/17.
 */
public class ClientMoveToSectorAction extends StoreAction {

    private static String type = "@CLIENT_MOVE_TO_SECTOR";
    public static String getType(){
        return type;
    }
    private final Sector targetSector;

    public ClientMoveToSectorAction(Sector targetSector) {
        super("@CLIENT_MOVE_TO_SECTOR","@CLIENT_GROUP");
        this.targetSector = targetSector;
    }

    public Sector getTargetSector() {
        return targetSector;
    }
}
