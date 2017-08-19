package client_side_policies;

import client.ReqRespHandler;
import client.EncodedBehaviourIdentifiers;
import client_store.*;
import client_store_actions.*;
import common.*;

import java.util.ArrayList;

public class RequestDiscardCardSidePolicy implements SidePolicy {
    @Override
    public void apply(ClientState state,StoreAction action) {
        ClientRequestDiscardObjectCard castedAction = (ClientRequestDiscardObjectCard) action;
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        EncodedBehaviourIdentifiers SERVER_ACTION_WIRE_PROVIDER = EncodedBehaviourIdentifiers.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        Player player = CLIENT_STORE.getState().getPlayer();
        ObjectCard objectCard = castedAction.getObjectCard();
        StoreAction action_ = new DiscardAction(objectCard);
        parameters.add(action_);
        parameters.add(player.getPlayerToken());
        ActionOnTheWire request = new ActionOnTheWire(SERVER_ACTION_WIRE_PROVIDER.makeAction(), parameters);
        ReqRespHandler reqRespHandler = ReqRespHandler.getInstance();
        reqRespHandler.initRequestResponse(request);
        boolean isActionServerValidated = CLIENT_STORE.getState().getCurrentReqRespNotification().isActionResult();
        if (isActionServerValidated) {
            CLIENT_STORE.propagateAction(new ClientRequestDiscardObjectCardAction(objectCard));
        }

    }
}
