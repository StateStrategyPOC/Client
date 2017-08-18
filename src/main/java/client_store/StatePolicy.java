package client_store;

import common.StoreAction;

/**
 * Represents a lambda MVC State Policy
 */
public interface StatePolicy {
    ClientState apply(ClientState state, StoreAction action);
}
