package client_store_effects;

import client_store.ClientState;
import client_store.Effect;
import client_store.State;
import client_store.StoreAction;

/**
 * Created by giorgiopea on 25/03/17.
 */
public class AddPubSubHandlerEffect implements Effect {

    @Override
    public void apply(StoreAction action, State state) {
        ClientState castedState = (ClientState) state;
        castedState.getCurrentPubSubHandler().start();
    }
}
