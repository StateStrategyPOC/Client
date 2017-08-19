package client_store;

import client.PubSubHandler;
import common.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created by giorgiopea on 24/03/17.
 */
public class ClientState extends State implements Serializable {

    private int tcpPort;
    private PubSubHandler currentPubSubHandler;
    private List<GamePublicData> availableGames;
    private Player player;
    private String host;
    private GameMap gameMap;
    private boolean isGameStarted;
    private boolean isMyTurn;
    private boolean startableGame;
    private RRNotification currentReqRespNotification;
    private boolean askLights;
    private boolean askAttack;
    private String lastChatMessage;
    private PSNotification currentPubSubNotification;
    private long delayReturnToGameList;
    private boolean connectionActive;
    private boolean aliensHaveWon;
    private boolean humansHaveWon;
    private boolean inRoom;

    public ClientState() {
        this.tcpPort = 29999;
        this.host = "localhost";
        this.delayReturnToGameList = 10000;
        this.connectionActive = true;
        this.inRoom = false;
    }

    public int getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(int tcpPort) {
        this.tcpPort = tcpPort;
    }

    public PubSubHandler getCurrentPubSubHandler() {
        return currentPubSubHandler;
    }

    public void setCurrentPubSubHandler(PubSubHandler currentPubSubHandler) {
        this.currentPubSubHandler = currentPubSubHandler;
    }

    public List<GamePublicData> getAvailableGames() {
        return availableGames;
    }

    public void setAvailableGames(List<GamePublicData> availableGames) {
        this.availableGames = availableGames;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public boolean isGameStarted() {
        return isGameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        isGameStarted = gameStarted;
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    public void setMyTurn(boolean myTurn) {
        isMyTurn = myTurn;
    }

    public boolean isStartableGame() {
        return startableGame;
    }

    public void setStartableGame(boolean startableGame) {
        this.startableGame = startableGame;
    }

    public RRNotification getCurrentReqRespNotification() {
        return currentReqRespNotification;
    }

    public void setCurrentReqRespNotification(RRNotification currentReqRespNotification) {
        this.currentReqRespNotification = currentReqRespNotification;
    }

    public boolean isAskLights() {
        return askLights;
    }

    public void setAskLights(boolean askLights) {
        this.askLights = askLights;
    }

    public boolean isAskAttack() {
        return askAttack;
    }

    public void setAskAttack(boolean askAttack) {
        this.askAttack = askAttack;
    }

    public String getLastChatMessage() {
        return lastChatMessage;
    }

    public void setLastChatMessage(String lastChatMessage) {
        this.lastChatMessage = lastChatMessage;
    }

    public PSNotification getCurrentPubSubNotification() {
        return currentPubSubNotification;
    }

    public void setCurrentPubSubNotification(PSNotification currentPubSubNotification) {
        this.currentPubSubNotification = currentPubSubNotification;
    }

    public long getDelayReturnToGameList() {
        return delayReturnToGameList;
    }

    public void setDelayReturnToGameList(long delayReturnToGameList) {
        this.delayReturnToGameList = delayReturnToGameList;
    }

    public boolean isConnectionActive() {
        return connectionActive;
    }

    public void setConnectionActive(boolean connectionActive) {
        this.connectionActive = connectionActive;
    }

    public boolean isAliensHaveWon() {
        return aliensHaveWon;
    }

    public void setAliensHaveWon(boolean aliensHaveWon) {
        this.aliensHaveWon = aliensHaveWon;
    }

    public boolean isHumansHaveWon() {
        return humansHaveWon;
    }

    public void setHumansHaveWon(boolean humansHaveWon) {
        this.humansHaveWon = humansHaveWon;
    }

    public boolean isInRoom() {
        return inRoom;
    }

    public void setInRoom(boolean inRoom) {
        this.inRoom = inRoom;
    }

    @Override
    public String toString() {
        return "ClientState{" +
                "tcpPort=" + tcpPort +
                ", currentPubSubHandler=" + currentPubSubHandler +
                ", availableGames=" + availableGames +
                ", player=" + player +
                ", host='" + host + '\'' +
                ", gameMap=" + gameMap +
                ", isGameStarted=" + isGameStarted +
                ", isMyTurn=" + isMyTurn +
                ", startableGame=" + startableGame +
                ", currentReqRespNotification=" + currentReqRespNotification +
                ", askLights=" + askLights +
                ", askAttack=" + askAttack +
                ", lastChatMessage='" + lastChatMessage + '\'' +
                ", currentPubSubNotification=" + currentPubSubNotification +
                ", delayReturnToGameList=" + delayReturnToGameList +
                ", connectionActive=" + connectionActive +
                ", aliensHaveWon=" + aliensHaveWon +
                ", humansHaveWon=" + humansHaveWon +
                ", inRoom=" + inRoom +
                '}';
    }
}
