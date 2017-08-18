package client_side_policies;

import client.ReqRespHandler;
import common.ActionOnTheWire;
import client.EncodedBehaviourIdentifiers;
import client_store.*;
import common.RRClientNotification;
import common.StoreAction;

import java.util.ArrayList;

public class RequestOnDemandGameStartSidePolicy implements SidePolicy {

    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        EncodedBehaviourIdentifiers SERVER_NAMES_PROVIDER = EncodedBehaviourIdentifiers.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(CLIENT_STORE.getState().getPlayer().getPlayerToken());
        ActionOnTheWire request = new ActionOnTheWire(SERVER_NAMES_PROVIDER.onDemandGameStart(),parameters);
        ReqRespHandler reqRespHandler = ReqRespHandler.getInstance();
        reqRespHandler.initRequestResponse(request);
        RRClientNotification currentClientNotification = CLIENT_STORE.getState().getCurrentReqRespNotification();
        boolean isActionServerValidated = currentClientNotification.getActionResult();
        //if (isActionServerValidated){
        //    CLIENT_STORE.propagateAction(new ClientStartGameAction(currentClientNotification.getGameMapName()));
        //    CLIENT_STORE.propagateAction(new ClientStartTurnAction());
        //}
    }
}
