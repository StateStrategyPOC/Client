package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import client_store.StoreAction;
import client_store_actions.ClientSetPlayerAction;
import client_store_actions.ClientSetPlayerToken;
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
