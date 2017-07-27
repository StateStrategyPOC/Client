package client_store_actions;

import client_store.StoreAction;
import common.Coordinate;
import common.Sector;

public class ClientRequestAttackAction extends StoreAction {

    private final Coordinate coordinate;
    public ClientRequestAttackAction(Coordinate coordinate) {
        super("@CLIENT_ATTACK_ACTION");
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
