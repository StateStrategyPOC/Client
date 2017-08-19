package client_side_policies;

import client.ReqRespHandler;
import common.ActionOnTheWire;
import common.EncodedBehaviourIdentifiers;
import client_store.*;
import client_store_actions.ClientPublishChatMessageAction;
import common.StoreAction;

import java.util.ArrayList;

public class SendChatMessageSidePolicy implements SidePolicy {

    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientPublishChatMessageAction castedAction = (ClientPublishChatMessageAction) action;
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(castedAction.getChatMessage());
        parameters.add(CLIENT_STORE.getState().getPlayer().getPlayerToken());
        ActionOnTheWire request = new ActionOnTheWire(EncodedBehaviourIdentifiers.publishChatMsg(),parameters);
        ReqRespHandler reqRespHandler = ReqRespHandler.getInstance();
        reqRespHandler.initRequestResponse(request);
    }
}
