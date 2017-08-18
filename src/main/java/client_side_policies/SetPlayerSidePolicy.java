package client_side_policies;

import client.ReqRespHandler;
import client_store_actions.ClientSetInRoomAction;
import client_store_actions.ClientSetPlayerAction;
import common.ActionOnTheWire;
import client.EncodedBehaviourIdentifiers;
import client_store.*;
import common.StoreAction;

import java.util.ArrayList;

public class SetPlayerSidePolicy implements SidePolicy {


    @Override
    public void apply(ClientState state, StoreAction action) {
            ClientSetPlayerAction castedAction = (ClientSetPlayerAction) action;
            ClientStore CLIENT_STORE = ClientStore.getInstance();
            EncodedBehaviourIdentifiers SERVER_NAMES_PROVIDER = EncodedBehaviourIdentifiers.getInstance();
            ArrayList<Object> parameters = new ArrayList<>();
            parameters.add(castedAction.getPlayerToken());
            ActionOnTheWire request = new ActionOnTheWire(SERVER_NAMES_PROVIDER.subscribe(),parameters);
            CLIENT_STORE.propagateAction(new ClientSetInRoomAction(true));
            ReqRespHandler reqRespHandler = ReqRespHandler.getInstance();
            reqRespHandler.initRequestResponse(request);
    }
}
