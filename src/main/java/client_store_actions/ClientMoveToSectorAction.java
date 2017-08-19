package client_store_actions;

import common.StoreAction;
import common.Sector;

/**
 * An action for signalling the request of the in game action of moving to a Sector
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
