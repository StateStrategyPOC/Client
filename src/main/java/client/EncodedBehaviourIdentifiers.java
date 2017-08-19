package client;

/**
 * Created by giorgiopea on 25/04/17.
 *
 */
public class EncodedBehaviourIdentifiers {

    private static EncodedBehaviourIdentifiers instance = new EncodedBehaviourIdentifiers();
    public static EncodedBehaviourIdentifiers getInstance(){
        return instance;
    }

    private EncodedBehaviourIdentifiers(){

    }

    public String subscribe(){
        return "$subscribe";
    }

    public String onDemandGameStart() {
        return "$onDemandGameStart";
    }

    public String makeAction() {
        return "$makeAction";
    }

    public String joinNewGame() {
        return "$joinNewGame";
    }
    public String joinGame(){
        return "$joinGame";
    }

    public String publishChatMsg() {
        return "$pushChatMessage";
    }

    public String getGames() {
        return "$getAvailableGames";
    }
}
