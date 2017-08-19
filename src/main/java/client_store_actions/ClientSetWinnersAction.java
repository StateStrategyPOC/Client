package client_store_actions;

import common.StoreAction;

/**
 * An action for signalling that the game is ended
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
