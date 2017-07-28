package client_store_actions;


import client_store.StoreAction;

/**
 * Created by giorgiopea on 07/04/17.
 */
public class ClientAdrenlineAction extends StoreAction {
    private static String type = "@CLIENT_ADRENALINE";
    public static String getType(){
        return type;
    }
    public ClientAdrenlineAction() {
        super("@CLIENT_ADRENALINE");
    }
}
