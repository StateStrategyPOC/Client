package client_side_policies;

import client.ReqRespHandler;
import common.ActionOnTheWire;
import common.EncodedBehaviourIdentifiers;
import client_store.*;
import client_store_actions.ClientRequestJoinNewGameAction;
import client_store_actions.ClientSetPlayerAction;
import common.RRNotification;
import common.StoreAction;

import java.util.ArrayList;

public class RequestJoinNewGameSidePolicy implements SidePolicy{
    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ClientRequestJoinNewGameAction castedAction = (ClientRequestJoinNewGameAction) action;
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(castedAction.getMapName());
        parameters.add(castedAction.getPlayerName());
        ActionOnTheWire request = new ActionOnTheWire(EncodedBehaviourIdentifiers.joinNewGame(),parameters);
        ReqRespHandler reqRespHandler = ReqRespHandler.getInstance();
        reqRespHandler.initRequestResponse(request);
        RRNotification currentNotification = state.getCurrentReqRespNotification();
        boolean isActionServerValidated = currentNotification.isActionResult();
        if (isActionServerValidated && state.isConnectionActive()){
            CLIENT_STORE.propagateAction(new ClientSetPlayerAction(castedAction.getPlayerName(), currentNotification.getPlayerToken()));
        }
    }
}
