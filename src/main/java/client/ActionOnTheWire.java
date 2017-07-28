package client;

import java.io.Serializable;
import java.util.ArrayList;

public class ActionOnTheWire implements Serializable {

    private String actionIdentifier;
    private String actionGroupIdentifier;
    private ArrayList<Object> parameters;

    public ActionOnTheWire(String actionIdentifier, String actionGroupIdentifier, ArrayList<Object> parameters) {
        this.actionIdentifier = actionIdentifier;
        this.actionGroupIdentifier = actionGroupIdentifier
        this.parameters = parameters;
    }

    public String getActionIdentifier() {
        return actionIdentifier;
    }

    public String getActionGroupIdentifier() {
        return actionGroupIdentifier;
    }

    public ArrayList<Object> getParameters() {
        return parameters;
    }
}
