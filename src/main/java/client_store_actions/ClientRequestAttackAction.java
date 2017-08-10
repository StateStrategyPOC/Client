package client_store_actions;

import common.StoreAction;
import common.Coordinate;

public class ClientRequestAttackAction extends StoreAction {
    private static String type = "@CLIENT_ATTACK_ACTION";
    public static String getType(){
        return type;
    }
    private final Coordinate coordinate;
    public ClientRequestAttackAction(Coordinate coordinate) {
        super("@CLIENT_ATTACK_ACTION","@CLIENT_GROUP");
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
