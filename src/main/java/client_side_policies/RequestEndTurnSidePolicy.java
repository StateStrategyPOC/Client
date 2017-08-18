package client_side_policies;

import client.ReqRespHandler;
import common.ActionOnTheWire;
import client.EncodedBehaviourIdentifiers;
import client_store.*;
import client_store_actions.ClientEndTurnAction;
import client_store_actions.ClientRequestEndTurnAction;
import common.EndTurnAction;
import common.StoreAction;

import java.util.ArrayList;

public class RequestEndTurnSidePolicy implements SidePolicy {
    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientRequestEndTurnAction castedAction = (ClientRequestEndTurnAction) action;
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        EncodedBehaviourIdentifiers SERVER_ACTION_WIRE_PROVIDER = EncodedBehaviourIdentifiers.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(new EndTurnAction());
        parameters.add(CLIENT_STORE.getState().getPlayer().getPlayerToken());
        ActionOnTheWire request = new ActionOnTheWire(SERVER_ACTION_WIRE_PROVIDER.makeAction(),parameters);
        ReqRespHandler reqRespHandler = ReqRespHandler.getInstance();
        reqRespHandler.initRequestResponse(request);
        boolean isActionServerValidated = CLIENT_STORE.getState().getCurrentReqRespNotification().getActionResult();
        if (isActionServerValidated){
            CLIENT_STORE.propagateAction(new ClientEndTurnAction());
        }
    }
}
