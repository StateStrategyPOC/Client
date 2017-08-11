package client_side_policies;

import client.ReqRespHandler;
import common.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.*;
import client_store_actions.ClientPublishChatMessage;
import common.StoreAction;

import java.util.ArrayList;

public class SendChatMessageSidePolicy implements SidePolicy {

    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientPublishChatMessage castedAction = (ClientPublishChatMessage) action;
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ServerMethodsNameProvider SERVER_NAMES_PROVIDER = ServerMethodsNameProvider.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(castedAction.getChatMessage());
        parameters.add(CLIENT_STORE.getState().getPlayer().getPlayerToken());
        ActionOnTheWire request = new ActionOnTheWire(SERVER_NAMES_PROVIDER.publishChatMsg(),parameters);
        ReqRespHandler reqRespHandler = ReqRespHandler.getInstance();
        reqRespHandler.initRequestResponse(request);
    }
}
