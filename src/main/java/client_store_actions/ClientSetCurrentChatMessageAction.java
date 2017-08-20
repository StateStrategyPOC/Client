package client_store_actions;

import common.StoreAction;

/**
 * An action for signalling the availability of a new chat message
 */
public class ClientSetCurrentChatMessageAction extends StoreAction {

    private final String message;
    public ClientSetCurrentChatMessageAction(String msg) {
        super("@CLIENT_PUBLISH_CHAT_MSG","@CLIENT_GROUP");
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ClientSetCurrentChatMessageAction{" +
                "message='" + message + '\'' +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}
