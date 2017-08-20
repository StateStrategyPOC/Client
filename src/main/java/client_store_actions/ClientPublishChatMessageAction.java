package client_store_actions;

import common.StoreAction;

/**
 * An action for signalling the request of the action of sending a chat message
 */
public class ClientPublishChatMessageAction extends StoreAction {


    private final String chatMessage;
    public ClientPublishChatMessageAction(String message) {
        super("@CLIENT_PUBLISH_CHAT_MSG","@CLIENT_GROUP");
        this.chatMessage = message;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    @Override
    public String toString() {
        return "ClientPublishChatMessageAction{" +
                "chatMessage='" + chatMessage + '\'' +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}
