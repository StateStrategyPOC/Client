package client_reducers;

import client.PubSubHandler;
import client_store.ClientState;
import client_store.Reducer;
import client_store.State;
import client_store.StoreAction;
import client_store_actions.ClientAddPubSubHandlerAction;

/**
 * Created by giorgiopea on 25/03/17.
 *
 */
public class CommunicationReducer implements Reducer {
    @Override
    public State reduce(StoreAction action, State state) {
        ClientState castedState = (ClientState) state;
        switch (action.type){
            case "@COMMUNICATION_ADD_PUB_SUB_HANDLER":
                return this.addPubSubHandler(action,castedState);
            case "@COMMUNICATION_REMOVE_PUBSUB_HANDLER":
                return this.removePubSubHandler(castedState);
        }
        return castedState;
    }

    private State removePubSubHandler(ClientState state) {
        state.setCurrentPubSubHandler(null);
        return state;

    }

    private State addPubSubHandler(StoreAction action, ClientState state) {
        ClientAddPubSubHandlerAction castedAction = (ClientAddPubSubHandlerAction) action;
        state.setCurrentPubSubHandler(new PubSubHandler(castedAction.getSocket(), castedAction.getInputStream()));
        return state;
    }
}
