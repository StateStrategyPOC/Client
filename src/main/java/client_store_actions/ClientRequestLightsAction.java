package client_store_actions;

import client_store.StoreAction;
import common.Coordinate;

public class ClientRequestLightsAction extends StoreAction {

    private static String type = "@CLIENT_REQUEST_LIGHTS_ACTION";
    public static String getType(){
        return type;
    }
    private final Coordinate coordinate;


    public ClientRequestLightsAction(Coordinate coordinate) {
        super("@CLIENT_REQUEST_LIGHTS_ACTION","@CLIENT_GROUP");
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
