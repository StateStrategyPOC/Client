package client_side_policies;

import client.ReqRespHandler;
import client_store_actions.ClientStartTurnAction;
import common.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.*;
import client_store_actions.ClientStartGameAction;
import common.RRClientNotification;
import common.StoreAction;

import java.util.ArrayList;

public class RequestOnDemandGameStartSidePolicy implements SidePolicy {

    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ServerMethodsNameProvider SERVER_NAMES_PROVIDER = ServerMethodsNameProvider.getInstance();
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
