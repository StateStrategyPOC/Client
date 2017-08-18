package client_side_policies;


import client.ReqRespHandler;
import common.ActionOnTheWire;
import client.EncodedBehaviourIdentifiers;
import client_store.*;
import client_store_actions.*;
import common.*;

import java.util.ArrayList;
import java.util.List;

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
            List<Card> drawnCards = CLIENT_STORE.getState().getCurrentReqRespNotification().getDrawnCards();
            CLIENT_STORE.propagateAction(new ClientMoveToSectorAction(targetSector));
            if (drawnCards.size() == 1) {
                CLIENT_STORE.propagateAction(
                        new ClientSetDrawnSectorObjectCard(
                                (SectorCard) drawnCards.get(0), null));
            } else if (drawnCards.size() == 2) {
                CLIENT_STORE.propagateAction(
                        new ClientSetDrawnSectorObjectCard(
                                (SectorCard) drawnCards.get(0),
                                (ObjectCard) drawnCards.get(1)));
            }
        }
    }
}
