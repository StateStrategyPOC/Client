package client_store_actions;

import common.RescueCard;
import common.StoreAction;

/**
 * An action for signalling the use of a Rescue Card
 */
public class ClientRescuePlayerAction extends StoreAction {

    private final RescueCard rescueCard;
    public ClientRescuePlayerAction(RescueCard rescueCard) {
        super("@CLIENT_RESCUE_PLAYER", "@CLIENT_GROUP");
        this.rescueCard = rescueCard;
    }

    public RescueCard getRescueCard() {
        return rescueCard;
    }

    @Override
    public String toString() {
        return "ClientRescuePlayerAction{" +
                "rescueCard=" + rescueCard +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}
