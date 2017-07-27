package client_store_effects;

import client.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.ClientStore;
import client_store.Effect;
import client_store.State;
import client_store.StoreAction;
import client_store_actions.ClientEndTurnAction;
import client_store_actions.ClientRequestEndTurnAction;

import java.util.ArrayList;

public class EndTurnEffect implements Effect {
    @Override
    public void apply(StoreAction action, State state) {
        ClientRequestEndTurnAction castedAction = (ClientRequestEndTurnAction) action;
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ServerMethodsNameProvider SERVER_ACTION_WIRE_PROVIDER = ServerMethodsNameProvider.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(CLIENT_STORE.getState().getPlayer().getPlayerToken());
        ActionOnTheWire request = new ActionOnTheWire(SERVER_ACTION_WIRE_PROVIDER.makeAction(),parameters);
        CLIENT_STORE.propagateAction(request);
        boolean isActionServerValidated = CLIENT_STORE.getState().getCurrentReqRespNotification().getActionResult();
        if (isActionServerValidated){
            CLIENT_STORE.dispatchAction(new ClientEndTurnAction());
        }
    }
}
