package client_store_actions;

import common.RescueCard;
import common.StoreAction;

public class ClientRescuePlayerAction extends StoreAction {

    private final RescueCard rescueCard;
    public ClientRescuePlayerAction(RescueCard rescueCard) {
        super("@CLIENT_RESCUE_PLAYER", "@CLIENT_GROUP");
        this.rescueCard = rescueCard;
    }

    public RescueCard getRescueCard() {
        return rescueCard;
    }
}
