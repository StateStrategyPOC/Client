package client_side_policies;

import client.ReqRespHandler;
import common.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.*;
import client_store_actions.ClientRequestJoinNewGameAction;
import client_store_actions.ClientSetPlayerAction;
import client_store_actions.ClientSetRequestAction;
import common.RRClientNotification;

import java.util.ArrayList;

public class RequestJoinNewGameSidePolicy implements SidePolicy{
    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ClientRequestJoinNewGameAction castedAction = (ClientRequestJoinNewGameAction) action;
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(castedAction.getMapName());
        parameters.add(castedAction.getPlayerName());
        ActionOnTheWire request = new ActionOnTheWire(ServerMethodsNameProvider.getInstance().joinNewGame(),parameters);
        ReqRespHandler reqRespHandler = ReqRespHandler.getInstance();
        reqRespHandler.initRequestResponse(request);
        RRClientNotification currentNotification = CLIENT_STORE.getState().getCurrentReqRespNotification();
        boolean isActionServerValidated = currentNotification.getActionResult();
        if (isActionServerValidated){
            CLIENT_STORE.propagateAction(new ClientSetPlayerAction(castedAction.getPlayerName(), currentNotification.getPlayerToken()));
        }
    }
}
