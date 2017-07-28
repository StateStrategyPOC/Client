package client_state_policies;

import client_store.ClientState;
import client_store.StatePolicy;
import client_store.StoreAction;
import client_store_actions.ClientSetCurrentChatMessage;

public class PublishMsgStatePolicy implements StatePolicy {
    @Override
    public ClientState apply(ClientState state, StoreAction action) {
        ClientSetCurrentChatMessage castedAction = (ClientSetCurrentChatMessage) action;
        state.setLastChatMessage(castedAction.getMessage());
        return state;
    }
}
