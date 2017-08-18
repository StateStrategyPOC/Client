package client_side_policies;

import client.ReqRespHandler;
import common.ActionOnTheWire;
import client.EncodedBehaviourIdentifiers;
import client_store.*;
import client_store_actions.ClientAskSectorToLightAction;
import client_store_actions.ClientRequestLightsAction;
import client_store_actions.ClientUseObjectCard;
import common.*;

import java.util.ArrayList;

public class RequestLightsSidePolicy implements SidePolicy {

    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientRequestLightsAction castedAction = (ClientRequestLightsAction) action;
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        EncodedBehaviourIdentifiers SERVER_NAMES_PROVIDER = EncodedBehaviourIdentifiers.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        Sector targetSector = CLIENT_STORE.getState().getGameMap().getSectorByCoords(castedAction.getCoordinate());
        ActionOnTheWire request = new ActionOnTheWire(SERVER_NAMES_PROVIDER.makeAction(),parameters);
        if (targetSector != null) {
            ObjectCard lightsCard = new LightsObjectCard(targetSector);
            StoreAction action_ = new UseObjAction(lightsCard);
            parameters.add(action_);
            parameters.add(CLIENT_STORE.getState().getPlayer().getPlayerToken());
            ReqRespHandler reqRespHandler = ReqRespHandler.getInstance();
            reqRespHandler.initRequestResponse(request);
            boolean isActionServerValidated = CLIENT_STORE.getState().getCurrentReqRespNotification().getActionResult();
            if (isActionServerValidated) {
                CLIENT_STORE.propagateAction(new ClientUseObjectCard(lightsCard));
                CLIENT_STORE.propagateAction(new ClientAskSectorToLightAction(false));
            }
        } else {
            throw new IllegalArgumentException(
                    "Undefined sector, please try again");
        }
    }
}
