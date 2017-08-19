package client_store_actions;

import common.StoreAction;

/**
 * An action for signalling if the Player has used an Attack Object Card
 */
public class ClientAskAttackAction extends StoreAction {

    private final boolean toBeAsked;

    public ClientAskAttackAction(boolean toBeAsked) {
        super("@CLIENT_ASK_FOR_SECTOR_TO_ATTACK","@CLIENT_GROUP");
        this.toBeAsked = toBeAsked;
    }

    public boolean isToBeAsked() {
        return toBeAsked;
    }
}
