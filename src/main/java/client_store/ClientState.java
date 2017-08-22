package client_store;

import client.PubSubHandler;
import common.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by giorgiopea on 24/03/17.
 */
public class ClientState extends State implements Serializable {

    private int tcpPort;
    private List<GamePublicData> availableGames;
    private Player player;
    private String host;
    private GameMap gameMap;
    private RRNotification currentReqRespNotification;
    private PSNotification currentPubSubNotification;
    private long delayReturnToGameList;
    private boolean connectionActive;
    private boolean inRoom;

    public ClientState() {
        this.availableGames = new ArrayList<>();
        this.tcpPort = 29999;
        this.host = "localhost";
        this.delayReturnToGameList = 5000;
        this.connectionActive = true;
        this.inRoom = false;
        this.currentReqRespNotification = new RRNotification(false,null,null,null,null,null,null,null);
        this.currentPubSubNotification = new PSNotification(null,null,null,false,false,null,null,false,false,false,false,null);
    }

    public int getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(int tcpPort) {
        this.tcpPort = tcpPort;
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

    public RRNotification getCurrentReqRespNotification() {
        return currentReqRespNotification;
    }

    public void setCurrentReqRespNotification(RRNotification currentReqRespNotification) {
        this.currentReqRespNotification = currentReqRespNotification;
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

    public boolean isInRoom() {
        return inRoom;
    }

    public void setInRoom(boolean inRoom) {
        this.inRoom = inRoom;
    }


}
