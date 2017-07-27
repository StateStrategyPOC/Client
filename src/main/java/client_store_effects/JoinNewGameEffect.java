package client_store_effects;

import client.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.ClientStore;
import client_store.Effect;
import client_store.State;
import client_store.StoreAction;
import client_store_actions.ClientJoinNewGameAction;
import client_store_actions.ClientSetPlayerAction;
import client_store_actions.ClientSetRequestAction;
import common.RRClientNotification;

import java.util.ArrayList;

public class JoinNewGameEffect implements Effect{
    @Override
    public void apply(StoreAction action, State state) {
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ClientJoinNewGameAction castedAction = (ClientJoinNewGameAction) action;
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(castedAction.getPlayerName());
        parameters.add(castedAction.getMapName());
        ActionOnTheWire request = new ActionOnTheWire(ServerMethodsNameProvider.getInstance().joinNewGame(),parameters);
        CLIENT_STORE.propagateAction(new ClientSetRequestAction(request));
        RRClientNotification currentNotification = CLIENT_STORE.getState().getCurrentReqRespNotification();
        boolean isActionServerValidated = currentNotification.getActionResult();
        if (isActionServerValidated){
            CLIENT_STORE.dispatchAction(new ClientSetPlayerAction(castedAction.getPlayerName(), currentNotification.getPlayerToken()));
        }
    }
}
