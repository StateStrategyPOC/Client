package client_side_policies;


import common.EncodedBehaviourIdentifiers;
import client.ReqRespHandler;
import client_store.ClientState;
import client_store.ClientStore;
import client_store.SidePolicy;
import client_store_actions.ClientMoveToSectorAction;
import client_store_actions.ClientRequestMoveToSectorAction;
import client_store_actions.ClientRescuePlayerAction;
import client_store_actions.ClientSetDrawnSectorObjectCardAction;
import common.*;

import java.util.ArrayList;

public class RequestMoveToSectorSidePolicy implements SidePolicy {


    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientRequestMoveToSectorAction castedAction = (ClientRequestMoveToSectorAction) action;
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ReqRespHandler reqRespHandler = ReqRespHandler.getInstance();

        ArrayList<Object> parameters = new ArrayList<>();
        Sector targetSector = state.getGameMap().getSectorByCoords(castedAction.getCoordinate());
        //CLIENT_STORE.propagateAction(new ClientAskAttackAction(false));
        MoveAction action_ = new MoveAction(targetSector);
        parameters.add(action_);
        parameters.add(state.getPlayer().getPlayerToken());
        ActionOnTheWire request = new ActionOnTheWire(EncodedBehaviourIdentifiers.makeAction(), parameters);
        reqRespHandler.initRequestResponse(request);
        boolean isActionServerValidated = state.getCurrentReqRespNotification().isActionResult();
        if (isActionServerValidated && state.isConnectionActive()) {
            RRNotification notification = state.getCurrentReqRespNotification();
            CLIENT_STORE.propagateAction(new ClientMoveToSectorAction(targetSector));
            if (notification.getDrawnSectorCard() != null){
                CLIENT_STORE.propagateAction(
                        new ClientSetDrawnSectorObjectCardAction(
                                notification.getDrawnSectorCard(), notification.getDrawnObjectCard()));
            }
            if (notification.getDrawnRescueCard() != null){
                CLIENT_STORE.propagateAction(new ClientRescuePlayerAction(notification.getDrawnRescueCard()));
            }
        }
    }
}
