package client_store_actions;

import client_store.StoreAction;
import common.Coordinate;
import common.Sector;

public class ClientAttackAction extends StoreAction {

    private final Coordinate coordinate;
    public ClientAttackAction(Coordinate coordinate) {
        super("@CLIENT_ATTACK_ACTION");
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
