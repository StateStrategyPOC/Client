package client_side_policies;

import client_store_actions.ClientSetInRoomAction;
import client_store_actions.ClientSetPlayerAction;
import common.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.*;
import client_store_actions.ClientSetPlayerToken;
import client_store_actions.ClientSetRequestAction;

import java.util.ArrayList;

public class SetPlayerSidePolicy implements SidePolicy {


    @Override
    public void apply(ClientState state, StoreAction action) {
            ClientSetPlayerAction castedAction = (ClientSetPlayerAction) action;
            ClientStore CLIENT_STORE = ClientStore.getInstance();
            ServerMethodsNameProvider SERVER_NAMES_PROVIDER = ServerMethodsNameProvider.getInstance();
            ArrayList<Object> parameters = new ArrayList<>();
            parameters.add(castedAction.getPlayerToken());
            ActionOnTheWire request = new ActionOnTheWire(SERVER_NAMES_PROVIDER.subscribe(),parameters);
            CLIENT_STORE.propagateAction(new ClientSetInRoomAction(true));
            CLIENT_STORE.propagateAction(new ClientSetRequestAction(request));
    }
}
