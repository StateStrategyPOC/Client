package client_store_actions;

import common.StoreAction;
import common.Coordinate;

/**
 * An action for signalling the request of the in game action of attacking a Sector
 */
public class ClientRequestAttackAction extends StoreAction {

    private final Coordinate coordinate;
    public ClientRequestAttackAction(Coordinate coordinate) {
        super("@CLIENT_REQUEST_ATTACK","@CLIENT_GROUP");
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
