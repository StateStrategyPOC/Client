package client_side_policies;


import client.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.*;
import client_store.Effect;
import client_store_actions.*;
import common.*;

import java.util.ArrayList;
import java.util.List;

public class RequestMoveToSectorEffect implements SidePolicy {


    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientRequestMoveToSectorAction castedAction = (ClientRequestMoveToSectorAction) action;
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ServerMethodsNameProvider SERVER_NAMES_PROVIDER = ServerMethodsNameProvider.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();

        Sector targetSector = CLIENT_STORE.getState().getGameMap().getSectorByCoords(castedAction.getCoordinate());
        CLIENT_STORE.dispatchAction(new ClientAskAttackAction(false));
        StoreAction action_ = new MoveAction(targetSector);
        parameters.add(action_);
        parameters.add(CLIENT_STORE.getState().getPlayer().getPlayerToken());
        ActionOnTheWire request = new ActionOnTheWire(SERVER_NAMES_PROVIDER.makeAction(), parameters);
        CLIENT_STORE.propagateAction(request);
        RRClientNotification rrClientNotification = CLIENT_STORE.getState().getCurrentReqRespNotification();
        boolean isActionServerValidated = CLIENT_STORE.getState().getCurrentReqRespNotification().getActionResult();
        if (isActionServerValidated) {
            List<Card> drawnCards = CLIENT_STORE.getState().getCurrentReqRespNotification().getDrawnCards();
            CLIENT_STORE.dispatchAction(new ClientMoveToSectorAction(targetSector));
            if (drawnCards.size() == 1) {
                CLIENT_STORE.dispatchAction(
                        new ClientSetDrawnSectorObjectCard(
                                (SectorCard) drawnCards.get(0), null));
            } else if (drawnCards.size() == 2) {
                CLIENT_STORE.dispatchAction(
                        new ClientSetDrawnSectorObjectCard(
                                (SectorCard) drawnCards.get(0),
                                (ObjectCard) drawnCards.get(1)));
            }
        }
    }
}
