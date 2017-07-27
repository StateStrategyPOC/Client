package client_store_effects;

import client.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.ClientStore;
import client_store.Effect;
import client_store.State;
import client_store.StoreAction;
import client_store_actions.ClientPublishChatMessage;
import common.RRClientNotification;

import java.util.ArrayList;

public class SendChatMessageEffect implements Effect {
    @Override
    public void apply(StoreAction action, State state) {
        ClientPublishChatMessage castedAction = (ClientPublishChatMessage) action;
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ServerMethodsNameProvider SERVER_NAMES_PROVIDER = ServerMethodsNameProvider.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(castedAction.getChatMessage());
        parameters.add(CLIENT_STORE.getState().getPlayer().getPlayerToken());
        ActionOnTheWire request = new ActionOnTheWire(SERVER_NAMES_PROVIDER.publishChatMsg(),parameters);
        CLIENT_STORE.propagateAction(request);
    }
}