package client_side_policies;

import client.ReqRespHandler;
import common.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.*;
import client_store_actions.ClientRequestJoinGameAction;
import client_store_actions.ClientSetPlayerAction;
import common.RRClientNotification;
import common.StoreAction;

import java.util.ArrayList;

public class RequestJoinGameSidePolicy implements SidePolicy {
    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientRequestJoinGameAction castedAction = (ClientRequestJoinGameAction) action;
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ServerMethodsNameProvider SERVER_ACTION_WIRE_PROVIDER = ServerMethodsNameProvider.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(castedAction.getGameId());
        parameters.add(castedAction.getPlayerName());
        ActionOnTheWire request = new ActionOnTheWire(SERVER_ACTION_WIRE_PROVIDER.joinGame(),parameters);
        ReqRespHandler reqRespHandler = ReqRespHandler.getInstance();
        reqRespHandler.initRequestResponse(request);
        RRClientNotification currentNotification = CLIENT_STORE.getState().getCurrentReqRespNotification();
        boolean isActionServerValidated = currentNotification.getActionResult();
        if (isActionServerValidated){
            CLIENT_STORE.propagateAction(new ClientSetPlayerAction(castedAction.getPlayerName(), currentNotification.getPlayerToken()));
        }
    }
}
