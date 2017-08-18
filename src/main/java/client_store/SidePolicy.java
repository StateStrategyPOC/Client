package client_store;

import common.StoreAction;

/**
 * Represents a lambda MVC Side Policy
 */
public interface SidePolicy {
    void apply(ClientState state, StoreAction action);
}
