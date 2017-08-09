package client_side_policies;

import common.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.*;
import client_store_actions.ClientAskSectorToLightAction;
import client_store_actions.ClientRequestLightsAction;
import client_store_actions.ClientSetRequestAction;
import client_store_actions.ClientUseObjectCard;
import common.*;

import java.util.ArrayList;

public class RequestLightsSidePolicy implements SidePolicy {

    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientRequestLightsAction castedAction = (ClientRequestLightsAction) action;
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ServerMethodsNameProvider SERVER_NAMES_PROVIDER = ServerMethodsNameProvider.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        Sector targetSector = CLIENT_STORE.getState().getGameMap().getSectorByCoords(castedAction.getCoordinate());
        ActionOnTheWire request = new ActionOnTheWire(SERVER_NAMES_PROVIDER.makeAction(),parameters);
        if (targetSector != null) {
            ObjectCard lightsCard = new LightsObjectCard(targetSector);
            StoreAction action_ = new UseObjAction(lightsCard);
            parameters.add(action_);
            parameters.add(CLIENT_STORE.getState().getPlayer().getPlayerToken());
            CLIENT_STORE.propagateAction(new ClientSetRequestAction(request));
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
