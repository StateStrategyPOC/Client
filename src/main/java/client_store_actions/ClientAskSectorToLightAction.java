package client_store_actions;

import client_store.StoreAction;

/**
 * Created by giorgiopea on 27/03/17.
 */
public class ClientAskSectorToLightAction extends StoreAction {

    private final boolean toBeAsked;

    public ClientAskSectorToLightAction(boolean toBeAsked) {
        super("@CLIENT_ASK_SECTOR_TO_LIGHT");
        this.toBeAsked = toBeAsked;
    }

    public boolean isToBeAsked() {
        return toBeAsked;
    }
}
