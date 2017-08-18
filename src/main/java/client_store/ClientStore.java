package client_store;

import common.StoreAction;

import java.util.HashMap;
import java.util.Map;
import java.util.Observer;

/**
 * Represents a lambda MVC Store
 */
public class ClientStore {

    private final Map<String, Resolver> actionGroupToResolver;
    private final static ClientStore instance = new ClientStore();
    private final StoreLogger STORE_LOGGER;
    private final ObservableClientState observableState;


    public static ClientStore getInstance(){
        return instance;
    }

    private ClientStore(){
        this.actionGroupToResolver = new HashMap<>();
        this.STORE_LOGGER = StoreLogger.getInstance();
        this.observableState = new ObservableClientState(new ClientState());
        this.fillResolverMap();
    }
    private void fillResolverMap(){
        this.actionGroupToResolver.put("@CLIENT_GROUP", new ClientGroupResolver());
    }


    public ClientState getState() {
        return this.observableState.getClientState();
    }


    public void observeState(Observer observer) {
        this.observableState.addObserver(observer);
    }

    /**
     * Performs lambda MVC Action propagation
     * @param action The Action to be propagated
     */
    public synchronized void propagateAction(StoreAction action) {
        Resolver resolver = this.actionGroupToResolver.get(action.getActionGroupIdentifier());
        try {
            PolicyCouple policyCouple = resolver.resolve(action);
            this.STORE_LOGGER.logPreStatePropagation(action,this.getState());
            if (policyCouple.getStatePolicy() != null){
                this.observableState.setState(policyCouple.getStatePolicy().apply(this.getState(), action),action);
            }
            this.STORE_LOGGER.logPostStatePropagation(this.getState());
            if (policyCouple.getSidePolicy() != null){
                policyCouple.getSidePolicy().apply(this.getState(),action);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
