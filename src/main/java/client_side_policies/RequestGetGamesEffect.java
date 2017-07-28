package client_side_policies;


import client.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.*;
import client_store.Effect;
import client_store_actions.*;
import common.RRClientNotification;

import java.util.ArrayList;

public  class RequestGetGamesEffect implements SidePolicy {

    @Override
    public void apply(ClientState state,StoreAction action) {
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
