package client_store_actions;

import common.StoreAction;

/**
 * An action for signalling if the Player has to be asked to indicate a Sector to reveal other Players
 * within it
 */
public class ClientAskSectorToLightAction extends StoreAction {

    private final boolean toBeAsked;

    public ClientAskSectorToLightAction(boolean toBeAsked) {
        super("@CLIENT_ASK_SECTOR_TO_LIGHT","@CLIENT_GROUP");
        this.toBeAsked = toBeAsked;
    }

    public boolean isToBeAsked() {
        return toBeAsked;
    }

    @Override
    public String toString() {
        return "ClientAskSectorToLightAction{" +
                "toBeAsked=" + toBeAsked +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}
