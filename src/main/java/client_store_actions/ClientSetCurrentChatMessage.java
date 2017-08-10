package client_store_actions;

import common.StoreAction;

/**
 * Created by giorgiopea on 28/03/17.
 */
public class ClientSetCurrentChatMessage extends StoreAction {
    private static String type = "@CLIENT_PUBLISH_CHAT_MSG";
    public static String getType(){
        return type;
    }
    private final String message;
    public ClientSetCurrentChatMessage(String msg) {
        super("@CLIENT_PUBLISH_CHAT_MSG","@CLIENT_GROUP");
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}
