package client_store;

import common.StoreAction;

public interface SidePolicy {
    void apply(ClientState state, StoreAction action);
}
