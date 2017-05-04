package client_store_actions;

import client_store.StoreAction;

/**
 * Created by giorgiopea on 27/03/17.
 */
public class ClientAskAttackAction extends StoreAction {

    private final boolean toBeAsked;

    public ClientAskAttackAction(boolean toBeAsked) {
        super("@CLIENT_ASK_FOR_SECTOR_TO_ATTACK");
        this.toBeAsked = toBeAsked;
    }

    public boolean isToBeAsked() {
        return toBeAsked;
    }
}