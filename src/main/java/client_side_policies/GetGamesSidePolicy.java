package client_side_policies;


import client.ReqRespHandler;
import common.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.*;
import client_store_actions.*;
import common.RRClientNotification;

import java.util.ArrayList;

public  class GetGamesSidePolicy implements SidePolicy {

    @Override
    public void apply(ClientState state,StoreAction action) {
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ServerMethodsNameProvider SERVER_ACTION_WIRE_PROVIDER = ServerMethodsNameProvider.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        ActionOnTheWire request = new ActionOnTheWire(SERVER_ACTION_WIRE_PROVIDER.getGames(),parameters);
        ReqRespHandler reqRespHandler = ReqRespHandler.getInstance();
        reqRespHandler.initRequestResponse(request);
        RRClientNotification currentNotification = CLIENT_STORE.getState().getCurrentReqRespNotification();
        boolean isActionServerValidated = currentNotification.getActionResult();
        if (isActionServerValidated && CLIENT_STORE.getState().isConnectionActive()){
            CLIENT_STORE.propagateAction(new ClientSetAvailableGamesAction(currentNotification.getGames()));
        }
    }
}
