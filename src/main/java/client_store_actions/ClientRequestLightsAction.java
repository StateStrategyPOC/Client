package client_store_actions;

import common.StoreAction;
import common.Coordinate;

public class ClientRequestLightsAction extends StoreAction {


    private final Coordinate coordinate;


    public ClientRequestLightsAction(Coordinate coordinate) {
        super("@CLIENT_REQUEST_LIGHTS","@CLIENT_GROUP");
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
