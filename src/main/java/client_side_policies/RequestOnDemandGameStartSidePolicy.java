package client_side_policies;

import client.ReqRespHandler;
import common.ActionOnTheWire;
import common.EncodedBehaviourIdentifiers;
import client_store.*;
import common.RRNotification;
import common.StoreAction;

import java.util.ArrayList;

public class RequestOnDemandGameStartSidePolicy implements SidePolicy {

    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(CLIENT_STORE.getState().getPlayer().getPlayerToken());
        ActionOnTheWire request = new ActionOnTheWire(EncodedBehaviourIdentifiers.onDemandGameStart(),parameters);
        ReqRespHandler reqRespHandler = ReqRespHandler.getInstance();
        reqRespHandler.initRequestResponse(request);
    }
}
