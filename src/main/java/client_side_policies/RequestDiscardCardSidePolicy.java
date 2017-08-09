package client_side_policies;

import common.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.*;
import client_store_actions.*;
import common.*;

import java.util.ArrayList;

public class RequestDiscardCardSidePolicy implements SidePolicy {
    @Override
    public void apply(ClientState state,StoreAction action) {
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
            CLIENT_STORE.propagateAction(new ClientRequestDiscardObjectCardAction(objectCard));
        }

    }
}
