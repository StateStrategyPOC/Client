package client_side_policies;

import client.ReqRespHandler;
import common.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.*;
import client_store_actions.ClientRequestGlobalNoise;
import client_store_actions.ClientSetDrawnSectorObjectCard;
import common.*;

import java.util.ArrayList;

public class RequestGlobalNoiseSidePolicy implements SidePolicy {
    @Override
    public void apply(ClientState state,StoreAction action) {
        ClientRequestGlobalNoise castedAction = (ClientRequestGlobalNoise) action;
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ServerMethodsNameProvider SERVER_NAMES_PROVIDER = ServerMethodsNameProvider.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        Sector targetSector = CLIENT_STORE.getState().getGameMap().getSectorByCoords(castedAction.getCoordinate());
        if (targetSector != null) {
            SectorCard globalNoiseCard = new GlobalNoiseSectorCard(castedAction.isHasObject(),
                    targetSector);
            StoreAction action_ = new UseSectorCardAction(globalNoiseCard);
            parameters.add(action_);
            parameters.add(CLIENT_STORE.getState().getPlayer().getPlayerToken());
            ActionOnTheWire request = new ActionOnTheWire(SERVER_NAMES_PROVIDER.makeAction(), parameters);
            ReqRespHandler reqRespHandler = ReqRespHandler.getInstance();
            reqRespHandler.initRequestResponse(request);
            boolean isActionServerValidated = CLIENT_STORE.getState().getCurrentReqRespNotification().getActionResult();
            if (isActionServerValidated){
                CLIENT_STORE.propagateAction(new ClientSetDrawnSectorObjectCard(null, null));
            }

        } else {
            throw new IllegalArgumentException(
                    "The sector you have indicated does not exists, please try again");
        }
    }
}
