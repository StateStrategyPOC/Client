package client_store_actions;

import common.StoreAction;

/**
 * Created by giorgiopea on 07/04/17.
 */
public class ClientSetWinnersAction extends StoreAction {

    private final boolean aliensHaveWon;
    private final boolean humansHaveWon;

    public ClientSetWinnersAction(boolean aliensHaveWon, boolean humansHaveWon) {
        super("@CLIENT_SET_WINNERS","@CLIENT_GROUP");
        this.aliensHaveWon = aliensHaveWon;
        this.humansHaveWon = humansHaveWon;
    }

    public boolean isAliensHaveWon() {
        return aliensHaveWon;
    }

    public boolean isHumansHaveWon() {
        return humansHaveWon;
    }
}
