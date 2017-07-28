package client_store_actions;

import client_store.StoreAction;
import common.Action;
import common.Coordinate;

public class ClientRequestGlobalNoise extends StoreAction {

    private static String type = "@CLIENT_REQUEST_GLOBAL_NOISE";
    public static String getType(){
        return type;
    }
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
