package client_store;

public interface SidePolicy {
    void apply(ClientState state, StoreAction action);
}
