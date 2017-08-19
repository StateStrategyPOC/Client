package client_store_actions;

import common.StoreAction;

public class ClientPublishChatMessage extends StoreAction {


    private final String chatMessage;
    public ClientPublishChatMessage(String message) {
        super("@CLIENT_PUBLISH_CHAT_MSG","@CLIENT_GROUP");
        this.chatMessage = message;
    }

    public String getChatMessage() {
        return chatMessage;
    }
}
