package client_store_effects;


import client.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.ClientStore;
import client_store.Effect;
import client_store.State;
import client_store.StoreAction;
import client_store_actions.*;
import common.RRClientNotification;

import java.util.ArrayList;

public  class RequestGetGamesEffect implements Effect {

    @Override
    public void apply(StoreAction action, State state) {
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ServerMethodsNameProvider SERVER_ACTION_WIRE_PROVIDER = ServerMethodsNameProvider.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        ActionOnTheWire request = new ActionOnTheWire(SERVER_ACTION_WIRE_PROVIDER.getGames(),parameters);
        CLIENT_STORE.propagateAction(new ClientSetRequestAction(request));
        RRClientNotification currentNotification = CLIENT_STORE.getState().getCurrentReqRespNotification();
        boolean isActionServerValidated = currentNotification.getActionResult();
        if (isActionServerValidated){
            CLIENT_STORE.dispatchAction(new ClientSetAvailableGamesAction(currentNotification.getGames()));
        }
    }
}
