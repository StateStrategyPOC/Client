package client_store_actions;

import client_store.StoreAction;

public class ClientPublishChatMessage extends StoreAction {
    private static String type = "@CLIENT_PUBLISH_CHAT_MSG";
    public static String getType(){
        return type;
    }

    private final String chatMessage;
    public ClientPublishChatMessage(String message) {
        super("@CLIENT_PUBLISH_CHAT_MSG","@CLIENT_GROUP");
        this.chatMessage = message;
    }

    public String getChatMessage() {
        return chatMessage;
    }
}
