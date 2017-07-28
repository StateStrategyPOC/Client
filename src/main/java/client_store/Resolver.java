package client_store;

import java.util.HashMap;
import java.util.Map;

public abstract class Resolver {
    protected final Map<String, StatePolicy> actionsToStatePolicies;
    protected final Map<String, SidePolicy> actionsToSidePolicies;

    public Resolver() {
        this.actionsToStatePolicies = new HashMap<>();
        this.actionsToSidePolicies = new HashMap<>();
        this.fillMaps();
    }

    private void fillMaps() {
        this.fillStatePolicyMap();
        this.fillSidePolicyMap();
    }

    protected abstract void fillStatePolicyMap();
    protected abstract void fillSidePolicyMap();


    public PolicyCouple resolve(StoreAction action) throws Exception {
        StatePolicy statePolicy = this.actionsToStatePolicies.get(action.type);
        SidePolicy sidePolicy = this.actionsToSidePolicies.get(action.type);
        if (statePolicy == null && sidePolicy == null){
            throw new Exception("No policy for the action is defined");
        }
        return new PolicyCouple(statePolicy,sidePolicy);
    }
}
