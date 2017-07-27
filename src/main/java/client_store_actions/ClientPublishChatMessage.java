package client_store_actions;

import client_store.StoreAction;

public class ClientPublishChatMessage extends StoreAction {
    private final String chatMessage;
    public ClientPublishChatMessage(String message) {
        super("@CLIENT_PUBLISH_CHAT_MSG");
        this.chatMessage = message;
    }

    public String getChatMessage() {
        return chatMessage;
    }
}
