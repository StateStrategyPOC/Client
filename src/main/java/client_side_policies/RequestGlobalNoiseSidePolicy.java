package client_side_policies;

import client.ReqRespHandler;
import common.EncodedBehaviourIdentifiers;
import client_store.*;
import client_store_actions.ClientRequestGlobalNoiseAction;
import client_store_actions.ClientSetDrawnSectorObjectCardAction;
import common.*;

import java.util.ArrayList;

public class RequestGlobalNoiseSidePolicy implements SidePolicy {
    @Override
    public void apply(ClientState state,StoreAction action) {
        ClientRequestGlobalNoiseAction castedAction = (ClientRequestGlobalNoiseAction) action;
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        Sector targetSector = CLIENT_STORE.getState().getGameMap().getSectorByCoords(castedAction.getCoordinate());
        if (targetSector != null) {
            SectorCard globalNoiseCard = new GlobalNoiseSectorCard(castedAction.isHasObject(),
                    targetSector);
            StoreAction action_ = new UseSectorCardAction(globalNoiseCard);
            parameters.add(action_);
            parameters.add(CLIENT_STORE.getState().getPlayer().getPlayerToken());
            ActionOnTheWire request = new ActionOnTheWire(EncodedBehaviourIdentifiers.makeAction(), parameters);
            ReqRespHandler reqRespHandler = ReqRespHandler.getInstance();
            reqRespHandler.initRequestResponse(request);
            boolean isActionServerValidated = CLIENT_STORE.getState().getCurrentReqRespNotification().isActionResult();
            if (isActionServerValidated){
                CLIENT_STORE.propagateAction(new ClientSetDrawnSectorObjectCardAction(null, null));
            }

        } else {
            throw new IllegalArgumentException(
                    "The sector you have indicated does not exists, please try again");
        }
    }
}
