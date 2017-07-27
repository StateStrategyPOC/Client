package client_store_effects;

import client.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.ClientStore;
import client_store.Effect;
import client_store.State;
import client_store.StoreAction;
import client_store_actions.*;
import common.*;

import java.util.ArrayList;

public class DiscardCardEffect implements Effect {
    @Override
    public void apply(StoreAction action, State state) {
        ClientRequestDiscardObjectCard castedAction = (ClientRequestDiscardObjectCard) action;
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ServerMethodsNameProvider SERVER_ACTION_WIRE_PROVIDER = ServerMethodsNameProvider.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        Player player = CLIENT_STORE.getState().getPlayer();
        ObjectCard objectCard = castedAction.getObjectCard();
        StoreAction action_ = new DiscardAction(objectCard);
        parameters.add(action_);
        parameters.add(player.getPlayerToken());
        ActionOnTheWire request = new ActionOnTheWire(SERVER_ACTION_WIRE_PROVIDER.makeAction(), parameters);
        CLIENT_STORE.propagateAction(new ClientSetRequestAction(request));
        boolean isActionServerValidated = CLIENT_STORE.getState().getCurrentReqRespNotification().getActionResult();
        if (isActionServerValidated) {
            CLIENT_STORE.dispatchAction(new ClientDiscardObjectCardAction(objectCard));
        }

    }
}
