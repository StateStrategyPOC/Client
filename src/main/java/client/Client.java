package client;

import common.*;


public class Client {
    private Player player;
    private GameMap gameMap;
    private boolean isGameStarted;

    private static Client instance = new Client();


    public static Client getInstance() {
        return instance;
    }

    private Client() {
        this.isGameStarted = false;
    }
    public boolean isGameStarted(){
        return isGameStarted;
    }
    public void setGameStarted(boolean gameStarted) {
        this.isGameStarted = gameStarted;
    }

    public GameMap getGameMap() {
        return this.gameMap;
    }

    public void setGameMap(GameMap map) {
        this.gameMap = map;
    }

    /**
     * Sets the client's current sector to the given coordinates and
     * stores the fact that the client has moved.
     * @param coordinate The coordinate of the sector the client has to move to.
     */
    public void move(Coordinate coordinate) {
        Sector targetSector = this.getGameMap().getSectorByCoords(coordinate);
        this.player.setHasMoved(true);
        this.player.setCurrentSector(targetSector);
    }

    /**
     * Sets client's properties to reflect the ending of a game turn
     */
    public void endTurn() {
        this.player.setSedated(false);
        this.player.setAdrenalined(false);
        this.player.setHasMoved(false);
    }


    /**
     * Sets client's properties to reflect a teleport object usage
     */
    public void teleport() {
        if (this.player.getPlayerToken().getPlayerType().equals(PlayerType.HUMAN)) {
            this.player.setCurrentSector(this.gameMap.getHumanSector());
        } else {
            this.player.setCurrentSector(this.gameMap.getAlienSector());
        }

    }

    public Player getPlayer() {
        return this.player;
    }
    public void setPlayer(String playerName) {
        this.player = new Player(playerName);
    }

}