package client_store_actions;


import common.StoreAction;

/**
 * An action for signalling if the Player has used an Adrenaline Object Card
 */
public class ClientAdrenlineAction extends StoreAction {
    public ClientAdrenlineAction() {
        super("@CLIENT_ADRENALINE","@CLIENT_GROUP");
    }
}
