package client_store_actions;


import common.StoreAction;

/**
 * An action for signalling the start of the game
 */
public class ClientStartGameAction extends StoreAction {

    private final String gameMapName;

    public ClientStartGameAction(String gameMapName) {
        super("@CLIENT_START_GAME","@CLIENT_GROUP");
        this.gameMapName = gameMapName;
    }


    public String getGameMapName() {
        return gameMapName;
    }
}
