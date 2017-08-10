package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import common.StoreAction;
import client_store_actions.ClientSetPlayerAction;
import common.Player;

public class SetPlayerStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientSetPlayerAction castedAction = (ClientSetPlayerAction) action;
        Player player = new Player(castedAction.getPlayerName());
        player.setPlayerToken(castedAction.getPlayerToken());
        state.setPlayer(player);
        return state;
    }
}
