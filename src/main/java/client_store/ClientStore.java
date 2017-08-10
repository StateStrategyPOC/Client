package client_store;

import common.StoreAction;

import java.util.HashMap;
import java.util.Map;
import java.util.Observer;

/**
 * Created by giorgiopea on 24/03/17.
 *
 */
public class ClientStore {

    private final Map<String, Resolver> actionGroupToResolver;
    private final static ClientStore instance = new ClientStore();
    private final StoreLogger STORE_LOGGER;
    private ObservableClientState observableState;


    public static ClientStore getInstance(){
        return instance;
    }

    private ClientStore(){
        this.actionGroupToResolver = new HashMap<>();
        this.STORE_LOGGER = StoreLogger.getInstance();
        this.produceInitialState();
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

    private void init(ClientState initialState) {
        this.observableState = new ObservableClientState(initialState);
    }

    private void produceInitialState(){
        ClientState initialState = new ClientState();
        this.init(initialState);
    }

    /**
     * Propagates an
     * @param action
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
