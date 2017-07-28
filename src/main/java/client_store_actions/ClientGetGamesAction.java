package client_store_actions;

import client_store.StoreAction;

public class ClientGetGamesAction extends StoreAction {
    private static String type = "@CLIENT_GET_GAMES";
    public static String getType(){
        return type;
    }

    public ClientGetGamesAction() {
        super("@CLIENT_GET_GAMES");
    }
}
