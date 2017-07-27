package client_store_effects;

import client.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.ClientStore;
import client_store.Effect;
import client_store.State;
import client_store.StoreAction;
import client_store_actions.ClientAskSectorToLightAction;
import client_store_actions.ClientRequestLightsAction;
import client_store_actions.ClientSetConnectionActiveAction;
import client_store_actions.ClientUseObjectCard;
import common.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class LightsEffect implements Effect {
    @Override
    public void apply(StoreAction action, State state) {
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
            CLIENT_STORE.propagateAction(request);
            boolean isActionServerValidated = CLIENT_STORE.getState().getCurrentReqRespNotification().getActionResult();
            if (isActionServerValidated) {
                CLIENT_STORE.dispatchAction(new ClientUseObjectCard(lightsCard));
                CLIENT_STORE.dispatchAction(new ClientAskSectorToLightAction(false));
            }
        } else {
            throw new IllegalArgumentException(
                    "Undefined sector, please try again");
        }
    }
}
