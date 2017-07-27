package client_store_effects;

import client.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.ClientStore;
import client_store.Effect;
import client_store.State;
import client_store.StoreAction;
import client_store_actions.ClientJoinGameAction;
import client_store_actions.ClientJoinNewGameAction;
import client_store_actions.ClientSetPlayerAction;
import client_store_actions.ClientSetRequestAction;
import common.RRClientNotification;

import java.util.ArrayList;

public class JoinGameEffect implements Effect {
    @Override
    public void apply(StoreAction action, State state) {
        ClientJoinGameAction castedAction = (ClientJoinGameAction) action;
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ServerMethodsNameProvider SERVER_ACTION_WIRE_PROVIDER = ServerMethodsNameProvider.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(castedAction.getGameId());
        parameters.add(castedAction.getPlayerName());
        ActionOnTheWire request = new ActionOnTheWire(SERVER_ACTION_WIRE_PROVIDER.joinGame(),parameters);
        CLIENT_STORE.propagateAction(new ClientSetRequestAction(request));
        RRClientNotification currentNotification = CLIENT_STORE.getState().getCurrentReqRespNotification();
        boolean isActionServerValidated = currentNotification.getActionResult();
        if (isActionServerValidated){
            CLIENT_STORE.dispatchAction(new ClientSetPlayerAction(castedAction.getPlayerName(), currentNotification.getPlayerToken()));
        }
    }
}