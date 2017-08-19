package client_side_policies;


import client.ReqRespHandler;
import common.ActionOnTheWire;
import common.EncodedBehaviourIdentifiers;
import client_store.*;
import client_store_actions.*;
import common.RRNotification;
import common.StoreAction;

import java.util.ArrayList;

public  class GetGamesSidePolicy implements SidePolicy {

    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        ActionOnTheWire request = new ActionOnTheWire(EncodedBehaviourIdentifiers.getGames(),parameters);
        ReqRespHandler reqRespHandler = ReqRespHandler.getInstance();
        reqRespHandler.initRequestResponse(request);
        RRNotification currentNotification = CLIENT_STORE.getState().getCurrentReqRespNotification();
        boolean isActionServerValidated = currentNotification.isActionResult();
        if (isActionServerValidated && CLIENT_STORE.getState().isConnectionActive()){
            CLIENT_STORE.propagateAction(new ClientSetAvailableGamesAction(currentNotification.getAvailableGames()));
        }
    }
}
