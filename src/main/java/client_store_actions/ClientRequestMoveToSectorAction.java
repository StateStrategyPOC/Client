package client_store_actions;

import common.StoreAction;
import common.Coordinate;

public class ClientRequestMoveToSectorAction extends StoreAction {

    private static String type = "@CLIENT_REQUEST_MOVE_TO_SECTOR";
    public static String getType(){
        return type;
    }
    private final Coordinate coordinate;

    public ClientRequestMoveToSectorAction(Coordinate coordinate) {
        super("@CLIENT_REQUEST_MOVE_TO_SECTOR","@CLIENT_GROUP");
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
