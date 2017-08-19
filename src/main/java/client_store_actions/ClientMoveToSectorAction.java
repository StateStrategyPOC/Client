package client_store_actions;

import common.StoreAction;
import common.Sector;

/**
 * Created by giorgiopea on 25/03/17.
 */
public class ClientMoveToSectorAction extends StoreAction {

    private final Sector targetSector;

    public ClientMoveToSectorAction(Sector targetSector) {
        super("@CLIENT_MOVE_TO_SECTOR","@CLIENT_GROUP");
        this.targetSector = targetSector;
    }

    public Sector getTargetSector() {
        return targetSector;
    }
}
