package client_store_effects;

import client.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.ClientStore;
import client_store.Effect;
import client_store.State;
import client_store.StoreAction;
import client_store_actions.ClientRequestGlobalNoise;
import client_store_actions.ClientSetConnectionActiveAction;
import client_store_actions.ClientSetDrawnSectorObjectCard;
import common.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class GlobalNoiseEffect implements Effect {
    @Override
    public void apply(StoreAction action, State state) {
        ClientRequestGlobalNoise castedAction = (ClientRequestGlobalNoise) action;
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ServerMethodsNameProvider SERVER_NAMES_PROVIDER = ServerMethodsNameProvider.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        Sector targetSector = CLIENT_STORE.getState().getGameMap().getSectorByCoords(castedAction.getCoordinate());
        if (targetSector != null) {
            SectorCard globalNoiseCard = new GlobalNoiseSectorCard(castedAction.isHasObject(),
                    targetSector);
            StoreAction action_ = new UseSectorCardAction(globalNoiseCard);
            parameters.add(action);
            parameters.add(CLIENT_STORE.getState().getPlayer().getPlayerToken());
            ActionOnTheWire request = new ActionOnTheWire(SERVER_NAMES_PROVIDER.makeAction(), parameters);
            CLIENT_STORE.propagateAction(request);
            boolean isActionServerValidated = CLIENT_STORE.getState().getCurrentReqRespNotification().getActionResult();
            if (isActionServerValidated){
                CLIENT_STORE.dispatchAction(new ClientSetDrawnSectorObjectCard(null, null));
            }

        } else {
            throw new IllegalArgumentException(
                    "The sector you have indicated does not exists, please try again");
        }
    }
}
