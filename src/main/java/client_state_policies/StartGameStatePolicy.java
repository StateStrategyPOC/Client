package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import common.StoreAction;
import client_store_actions.ClientStartGameAction;
import common.GameMap;
import common.Player;
import common.PlayerType;
import factories.GameMapFactory;

public class StartGameStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientStartGameAction castedAction = (ClientStartGameAction) action;
        Player player = state.getPlayer();
        GameMap gameMap;
        gameMap = GameMapFactory.provideCorrectFactory(castedAction.getGameMapName().toUpperCase()).makeMap();
        state.setGameMap( gameMap);
        if (player.getPlayerToken().getPlayerType().equals(PlayerType.ALIEN)) {
            player.setCurrentSector(state.getGameMap().getAlienSector());
        } else {
            player.setCurrentSector(state.getGameMap().getHumanSector());
        }
        state.setGameStarted(true);
        return state;
    }
}
