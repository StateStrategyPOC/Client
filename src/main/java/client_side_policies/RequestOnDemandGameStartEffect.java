package client_side_policies;

import client.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.*;
import client_store.Effect;
import client_store_actions.ClientStartGameAction;
import common.RRClientNotification;

import java.util.ArrayList;

public class RequestOnDemandGameStartEffect implements SidePolicy {

    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ServerMethodsNameProvider SERVER_NAMES_PROVIDER = ServerMethodsNameProvider.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(CLIENT_STORE.getState().getPlayer().getPlayerToken());
        ActionOnTheWire request = new ActionOnTheWire(SERVER_NAMES_PROVIDER.onDemandGameStart(),parameters);
        CLIENT_STORE.propagateAction(request);
        RRClientNotification currentClientNotification = CLIENT_STORE.getState().getCurrentReqRespNotification();
        boolean isActionServerValidated = currentClientNotification.getActionResult();
        if (isActionServerValidated){
            CLIENT_STORE.dispatchAction(new ClientStartGameAction(currentClientNotification.getGameMapName()));
        }
    }
}
