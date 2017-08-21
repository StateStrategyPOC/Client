package client_side_policies;

import client.ReqRespHandler;
import common.ActionOnTheWire;
import common.EncodedBehaviourIdentifiers;
import client_store.*;
import client_store_actions.ClientEndTurnAction;
import client_store_actions.ClientRequestEndTurnAction;
import common.EndTurnAction;
import common.StoreAction;

import java.util.ArrayList;

public class RequestEndTurnSidePolicy implements SidePolicy {
    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(new EndTurnAction());
        parameters.add(state.getPlayer().getPlayerToken());
        ActionOnTheWire request = new ActionOnTheWire(EncodedBehaviourIdentifiers.makeAction(),parameters);
        ReqRespHandler reqRespHandler = ReqRespHandler.getInstance();
        reqRespHandler.initRequestResponse(request);
        boolean isActionServerValidated = state.getCurrentReqRespNotification().isActionResult();
        if (isActionServerValidated && state.isConnectionActive()){
            CLIENT_STORE.propagateAction(new ClientEndTurnAction());
        }
    }
}
