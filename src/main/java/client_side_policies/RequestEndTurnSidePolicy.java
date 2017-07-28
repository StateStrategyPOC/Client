package client_side_policies;

import client.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.*;
import client_store_actions.ClientEndTurnAction;
import client_store_actions.ClientRequestEndTurnAction;
import client_store_actions.ClientSetRequestAction;

import java.util.ArrayList;

public class RequestEndTurnSidePolicy implements SidePolicy {
    @Override
    public void apply(ClientState state,StoreAction action) {
        ClientRequestEndTurnAction castedAction = (ClientRequestEndTurnAction) action;
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ServerMethodsNameProvider SERVER_ACTION_WIRE_PROVIDER = ServerMethodsNameProvider.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(CLIENT_STORE.getState().getPlayer().getPlayerToken());
        ActionOnTheWire request = new ActionOnTheWire(SERVER_ACTION_WIRE_PROVIDER.makeAction(),parameters);
        CLIENT_STORE.propagateAction(new ClientSetRequestAction(request));
        boolean isActionServerValidated = CLIENT_STORE.getState().getCurrentReqRespNotification().getActionResult();
        if (isActionServerValidated){
            CLIENT_STORE.propagateAction(new ClientEndTurnAction());
        }
    }
}
