package client_store_actions;

import client_store.StoreAction;
import common.GamePublicData;

import java.util.ArrayList;

/**
 * Created by giorgiopea on 25/03/17.
 */
public class ClientSetAvailableGamesAction extends StoreAction {
    private static String type = "@CLIENT_SET_AVAILABLE_GAMES";
    public static String getType(){
        return type;
    }
    private final ArrayList<GamePublicData> availableGames;

    public ClientSetAvailableGamesAction(ArrayList<GamePublicData> availableGames) {
        super("@CLIENT_SET_AVAILABLE_GAMES");
        this.availableGames = availableGames;
    }

    public ArrayList<GamePublicData> getAvailableGames() {
        return availableGames;
    }
}
