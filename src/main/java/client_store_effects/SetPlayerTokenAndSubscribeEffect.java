package client_store_effects;

import client.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.ClientStore;
import client_store.Effect;
import client_store.State;
import client_store.StoreAction;
import client_store_actions.ClientSetPlayerToken;

import java.util.ArrayList;

public class SetPlayerTokenAndSubscribeEffect implements Effect {
    @Override
    public void apply(StoreAction action, State state) {
        ClientSetPlayerToken castedAction = (ClientSetPlayerToken) action;
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ServerMethodsNameProvider SERVER_NAMES_PROVIDER = ServerMethodsNameProvider.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(castedAction.getPlayerToken());
        ActionOnTheWire request = new ActionOnTheWire(SERVER_NAMES_PROVIDER.subscribe(),parameters);
        CLIENT_STORE.propagateAction(request);
    }
}
