package client_store;

import common.StoreAction;

public interface StatePolicy {
    ClientState apply(ClientState state, StoreAction action);
}
