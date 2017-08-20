package client_store_actions;

import common.StoreAction;
import common.GamePublicData;

import java.util.ArrayList;

/**
 * An action for signalling the availibility a series of game rooms to join
 */
public class ClientSetAvailableGamesAction extends StoreAction {

    private final ArrayList<GamePublicData> availableGames;

    public ClientSetAvailableGamesAction(ArrayList<GamePublicData> availableGames) {
        super("@CLIENT_SET_AVAILABLE_GAMES","@CLIENT_GROUP");
        this.availableGames = availableGames;
    }

    public ArrayList<GamePublicData> getAvailableGames() {
        return availableGames;
    }

    @Override
    public String toString() {
        return "ClientSetAvailableGamesAction{" +
                "availableGames=" + availableGames +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}
