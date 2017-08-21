package client_side_policies;

import client.ReqRespHandler;
import common.EncodedBehaviourIdentifiers;
import client_store.*;
import client_store_actions.*;
import common.*;

import java.util.ArrayList;

public class RequestDiscardCardSidePolicy implements SidePolicy {
    @Override
    public void apply(ClientState state,StoreAction action) {
        ClientRequestDiscardObjectCardAction castedAction = (ClientRequestDiscardObjectCardAction) action;
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        Player player = state.getPlayer();
        ObjectCard objectCard = castedAction.getObjectCard();
        StoreAction action_ = new DiscardAction(objectCard);
        parameters.add(action_);
        parameters.add(player.getPlayerToken());
        ActionOnTheWire request = new ActionOnTheWire(EncodedBehaviourIdentifiers.makeAction(), parameters);
        ReqRespHandler reqRespHandler = ReqRespHandler.getInstance();
        reqRespHandler.initRequestResponse(request);
        boolean isActionServerValidated = state.getCurrentReqRespNotification().isActionResult();
        if (isActionServerValidated && state.isConnectionActive()) {
            CLIENT_STORE.propagateAction(new ClientDiscardObjectCardAction(objectCard));
        }

    }
}
