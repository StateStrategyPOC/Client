package client_side_policies;


import client.EncodedBehaviourIdentifiers;
import client.ReqRespHandler;
import client_store.ClientState;
import client_store.ClientStore;
import client_store.SidePolicy;
import client_store_actions.ClientMoveToSectorAction;
import client_store_actions.ClientRequestMoveToSectorAction;
import client_store_actions.ClientSetDrawnSectorObjectCard;
import common.*;

import java.util.ArrayList;

public class RequestMoveToSectorSidePolicy implements SidePolicy {


    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientRequestMoveToSectorAction castedAction = (ClientRequestMoveToSectorAction) action;
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ReqRespHandler reqRespHandler = ReqRespHandler.getInstance();
        EncodedBehaviourIdentifiers encodedBehaviourIdentifiers = EncodedBehaviourIdentifiers.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        Sector targetSector = CLIENT_STORE.getState().getGameMap().getSectorByCoords(castedAction.getCoordinate());
        //CLIENT_STORE.propagateAction(new ClientAskAttackAction(false));
        MoveAction action_ = new MoveAction(targetSector);
        parameters.add(action_);
        parameters.add(CLIENT_STORE.getState().getPlayer().getPlayerToken());
        ActionOnTheWire request = new ActionOnTheWire(encodedBehaviourIdentifiers.makeAction(), parameters);
        reqRespHandler.initRequestResponse(request);
        boolean isActionServerValidated = CLIENT_STORE.getState().getCurrentReqRespNotification().getActionResult();
        if (isActionServerValidated) {
            RRNotification notification = CLIENT_STORE.getState().getCurrentReqRespNotification();
            CLIENT_STORE.propagateAction(new ClientMoveToSectorAction(targetSector));
            if (notification.getDrawnSectorCard() != null){
                CLIENT_STORE.propagateAction(
                        new ClientSetDrawnSectorObjectCard(
                                notification.getDrawnSectorCard(), notification.getDrawnObjectCard()));
            }
        }
    }
}
