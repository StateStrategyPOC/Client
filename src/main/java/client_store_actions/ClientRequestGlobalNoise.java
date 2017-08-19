package client_store_actions;

import common.StoreAction;
import common.Coordinate;

public class ClientRequestGlobalNoise extends StoreAction {


    private final Coordinate coordinate;
    private final boolean hasObject;

    public ClientRequestGlobalNoise(Coordinate coordinate, boolean hasObject) {
        super("@CLIENT_REQUEST_GLOBAL_NOISE","@CLIENT_GROUP");
        this.coordinate = coordinate;
        this.hasObject = hasObject;
    }

    public boolean isHasObject() {
        return hasObject;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
