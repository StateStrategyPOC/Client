package client_store_actions;

import common.StoreAction;
import common.Coordinate;

/**
 * An action for signalling the request of the in game action of using a Global Noise Sector Card
 */
public class ClientRequestGlobalNoiseAction extends StoreAction {


    private final Coordinate coordinate;
    private final boolean hasObject;

    public ClientRequestGlobalNoiseAction(Coordinate coordinate, boolean hasObject) {
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
