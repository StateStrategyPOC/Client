package client_store;

public interface StatePolicy {
    ClientState apply(ClientState state, StoreAction action);
}
