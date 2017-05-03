package client;

import common.*;
import factories.FermiGameMapFactory;
import factories.GalileiGameMapFactory;
import factories.GalvaniGameMapFactory;
import factories.GameMapFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * A manager that acts as CONTROLLER.
 * The gui components of this application signal various user interactions to this manager,
 * and this manager implements the business logic of the application.
 */
public class ClientServices {
    private final Client client;
    private final CommunicationHandler communicationHandler;
    private final ServerMethodsNameProvider serverMethodsNameProvider;
    private GuiManager guiManager;
    private static ClientServices instance;
    private List<GamePublicData> availableGames;
    private RRClientNotification currentRrNotification;
    private PSClientNotification currentPsNotification;

    public static ClientServices getInstance(){
        if (instance == null){
            instance = new ClientServices();
        }
        return instance;
    }

    private ClientServices(){
        this.client = Client.getInstance();
        this.communicationHandler = CommunicationHandler.getInstance();
        this.serverMethodsNameProvider = ServerMethodsNameProvider.getInstance();
    }
    public void initWithGuiManager(GuiManager guiManager){
        this.guiManager = guiManager;
    }
    /**
     * Requests to the server the creation of a new game with a given map.
     *
     * @param gameMapName The name of the map of the game to be created.
     * @param playerName  The name of the client in the game.
     */
    public void joinNewGame(String gameMapName, String playerName) {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(gameMapName);
        parameters.add(playerName);
        try {
            RemoteMethodCall methodCall = this.communicationHandler.newComSession(new RemoteMethodCall(this.serverMethodsNameProvider.joinNewGame(), parameters));
            this.processRemoteInvocation(methodCall);;
            this.guiManager.setConnectionActiveReaction(true);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            this.guiManager.setConnectionActiveReaction(false);
        }
        boolean isActionServerValidated = this.currentRrNotification.getActionResult();
        if (isActionServerValidated){
            this.client.setPlayer(playerName);
            this.setPlayerTokenAndSubscribe(this.currentRrNotification.getPlayerToken());
        }
    }

    /**
     * Requests to the server the join of a game.
     *
     * @param gameId     The id of the game to join.
     * @param playerName The name of the client in the game.
     */
    public void joinGame(int gameId, String playerName) {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(gameId);
        parameters.add(playerName);
        try {
            RemoteMethodCall methodCall = this.communicationHandler.newComSession(new RemoteMethodCall(this.serverMethodsNameProvider.joinGame(), parameters));
            this.processRemoteInvocation(methodCall);
            this.guiManager.setConnectionActiveReaction(true);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            this.guiManager.setConnectionActiveReaction(false);
        }
        boolean isActionServerValidated = this.currentRrNotification.getActionResult();
        if (isActionServerValidated){
            this.client.setPlayer(playerName);
            this.setPlayerTokenAndSubscribe(this.currentRrNotification.getPlayerToken());
        }
    }

    /**
     * Publishes a message in the chat window.
     * This method is invoked indirectly using reflection.
     *
     * @param msg The message to be published.
     */
    private void publishChatMsg(String msg) {
        this.guiManager.publishChatMessage(msg);
    }


    /**
     * Moves the client to the sector at the given coordinates and executes the other logic related to this action.
     * This action is validated and registered by contacting the game server.
     *
     * @param coordinate The coordinates of the sector to move to.
     */
    public void moveToSector(Coordinate coordinate) {
        Sector targetSector = this.client.getGameMap().getSectorByCoords(coordinate);
        ArrayList<Object> parameters = new ArrayList<>();
        Action action = new MoveAction(targetSector);
        parameters.add(action);
        parameters.add(this.client.getPlayer().getPlayerToken());
        try {
            RemoteMethodCall methodCall = this.communicationHandler.newComSession(new RemoteMethodCall("makeAction", parameters));
            this.processRemoteInvocation(methodCall);
            this.guiManager.setConnectionActiveReaction(true);
            this.guiManager.displayResponseMsg(this.currentRrNotification.getMessage());
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            this.guiManager.setConnectionActiveReaction(false);
        }
        boolean isActionServerValidated = this.currentRrNotification.getActionResult();
        if (isActionServerValidated){
            this.client.move(coordinate);
            this.guiManager.moveToSectorReaction();
            List<Card> drawnCards = this.currentRrNotification.getDrawnCards();
            if (drawnCards.size() == 1) {
                this.guiManager.setDrawnSectorObjectCardReaction(null,(SectorCard) drawnCards.get(0));
            } else if (drawnCards.size() == 2) {
                this.client.getPlayer().getPrivateDeck().addCard((ObjectCard) drawnCards.get(1));
                this.guiManager.setDrawnSectorObjectCardReaction((ObjectCard) drawnCards.get(1),(SectorCard) drawnCards.get(0));
            }
        }

    }

    /**
     * Signals that there's the possibility, by the client, to start the game without waiting for 8 players to join.
     * This method is invoked indirectly using reflection.
     */
    private void signalStartableGame() {
        this.guiManager.signalStartableGame();
    }

    /**
     * Makes the client use an object card. This action is validated and registered by contacting the game server.
     *
     * @param objectCard The object card to be used
     */
    public void useObjCard(ObjectCard objectCard) {
        if (this.client.getPlayer().getPrivateDeck().getContent().contains(objectCard)) {
            if (objectCard instanceof LightsObjectCard) {
                this.guiManager.askForSectorToLightReaction();
            } else if (objectCard instanceof AttackObjectCard) {
                this.guiManager.askForSectorToAttackReaction();
            } else {
                ArrayList<Object> parameters = new ArrayList<>();
                Action action = new UseObjAction(objectCard);
                parameters.add(action);
                parameters.add(this.client.getPlayer().getPlayerToken());
                try {
                    RemoteMethodCall methodCall = this.communicationHandler.newComSession(new RemoteMethodCall(this.serverMethodsNameProvider.makeAction(), parameters));
                    this.processRemoteInvocation(methodCall);
                    this.guiManager.setConnectionActiveReaction(true);
                    this.guiManager.displayResponseMsg(this.currentRrNotification.getMessage());
                } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IOException e1) {
                    this.guiManager.setConnectionActiveReaction(false);
                }
                boolean isActionServerValidated = this.currentRrNotification.getActionResult();
                if (isActionServerValidated) {
                    this.client.getPlayer().getPrivateDeck().removeCard(objectCard);
                    this.guiManager.useObjectCardReaction(objectCard);
                    if (objectCard instanceof TeleportObjectCard) {
                        this.client.teleport();
                        this.guiManager.teleportToStartingSectorReaction();
                    } else if (objectCard instanceof SuppressorObjectCard) {
                        this.client.getPlayer().setSedated(true);
                    } else if (objectCard instanceof AdrenalineObjectCard) {
                        this.client.getPlayer().setAdrenalined(true);
                    }
                }

            }
        }


    }

    /**
     * Allows the client to act on the game as consequence of a favorable turn switch.
     * This method is invoked indirectly using reflection.
     */
    private void startTurn() {
        this.guiManager.startTurn();
    }

    /**
     * Makes the client indicate a sector to the other players of the game as a consequence of
     * a global noise sector card. This action is validated and registered by contacting the game server.
     *
     * @param coordinate The coordinates of the sector to be indicated
     * @param hasObject  If there's an object card associated with the global noise sector card(irrelevant)
     */
    public void globalNoise(Coordinate coordinate, boolean hasObject) {
        Sector targetSector = this.client.getGameMap().getSectorByCoords(coordinate);
        if (targetSector != null) {
            SectorCard globalNoiseCard = new GlobalNoiseSectorCard(hasObject,
                    targetSector);
            ArrayList<Object> parameters = new ArrayList<>();
            Action action = new UseSectorCardAction(globalNoiseCard);
            parameters.add(action);
            parameters.add(this.client.getPlayer().getPlayerToken());
            try {
                RemoteMethodCall methodCall = this.communicationHandler.newComSession(new RemoteMethodCall(this.serverMethodsNameProvider.makeAction(), parameters));
                this.processRemoteInvocation(methodCall);
                this.guiManager.setConnectionActiveReaction(true);
                this.guiManager.displayResponseMsg(this.currentRrNotification.getMessage());
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e1) {
                this.guiManager.setConnectionActiveReaction(false);
            }
            boolean isActionServerValidated = this.currentRrNotification.getActionResult();
            if (isActionServerValidated){
                this.guiManager.setDrawnSectorObjectCardReaction(null,null);
            }
        } else {
            throw new IllegalArgumentException(
                    "The sector you have indicated does not exists, please try again");
        }
    }

    /**
     * Makes the client indicate a sector and discover if there are players in the sector itself or in the adjacent ones
     * as a consequence of a lights object card. This action is validated and registered by contacting the game server.
     *
     * @param coordinate The coordinates of the sector for the ligths object card effect
     */
    public void lights(Coordinate coordinate) {
        Sector targetSector = this.client.getGameMap().getSectorByCoords(coordinate);
        if (targetSector != null) {
            ObjectCard lightsCard = new LightsObjectCard(targetSector);
            ArrayList<Object> parameters = new ArrayList<>();
            Action action = new UseObjAction(lightsCard);
            parameters.add(action);
            parameters.add(this.client.getPlayer().getPlayerToken());
            try {
                RemoteMethodCall remoteMethodCall = this.communicationHandler.newComSession(new RemoteMethodCall(this.serverMethodsNameProvider.makeAction(), parameters));
                this.processRemoteInvocation(remoteMethodCall);
                this.guiManager.setConnectionActiveReaction(true);
                this.guiManager.displayResponseMsg(this.currentRrNotification.getMessage());
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e1) {
                this.guiManager.setConnectionActiveReaction(false);
            }
            boolean isActionServerValidated = this.currentRrNotification.getActionResult();
            if (isActionServerValidated){
                this.client.getPlayer().getPrivateDeck().removeCard(lightsCard);
                this.guiManager.useObjectCardReaction(lightsCard);
            }
        } else {
            throw new IllegalArgumentException(
                    "Undefined sector, please try again");
        }

    }

    /**
     * Makes the client end its turn. This action is validated and registered by contacting the game server.
     */
    public void endTurn() {
        ArrayList<Object> parameters = new ArrayList<>();
        Action action = new EndTurnAction();
        parameters.add(action);
        parameters.add(this.client.getPlayer().getPlayerToken());
        try {
            RemoteMethodCall remoteMethodCall = this.communicationHandler.newComSession(new RemoteMethodCall(this.serverMethodsNameProvider.makeAction(), parameters));
            this.processRemoteInvocation(remoteMethodCall);
            this.guiManager.setConnectionActiveReaction(true);
            this.guiManager.displayResponseMsg(this.currentRrNotification.getMessage());
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            this.guiManager.setConnectionActiveReaction(false);
        }
        boolean isActionServerValidated = this.currentRrNotification.getActionResult();
        if (isActionServerValidated){
            this.client.endTurn();
            this.guiManager.endTurn();
        }

    }

    private void forceEndTurn(){
        this.guiManager.displayResponseMsg("You have taken too much you will skip your turn");
        this.client.endTurn();
        this.guiManager.endTurn();
    }

    /**
     * Makes the client discard a given object card. This action is validated and registered by contacting the game server.
     *
     * @param objectCard The object card to be discarded.
     */
    public void discardCard(ObjectCard objectCard) {
        if (this.client.getPlayer().getPrivateDeck().getContent().contains(objectCard)) {
            ArrayList<Object> parameters = new ArrayList<>();
            Action action = new DiscardAction(objectCard);
            parameters.add(action);
            parameters.add(this.client.getPlayer().getPlayerToken());
            try {
                RemoteMethodCall remoteMethodCall = this.communicationHandler.newComSession(new RemoteMethodCall("makeAction", parameters));
                this.processRemoteInvocation(remoteMethodCall);
                this.guiManager.setConnectionActiveReaction(true);
                this.guiManager.displayResponseMsg(this.currentRrNotification.getMessage());
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            } catch (IOException e1) {
                this.guiManager.setConnectionActiveReaction(false);
            }
            boolean isActionServerValidated = this.currentRrNotification.getActionResult();
            if (isActionServerValidated){
                this.client.getPlayer().getPrivateDeck().removeCard(objectCard);
                this.guiManager.discardObjectCardReaction();
            }
        }
    }

    /**
     * Invokes a method on instances of this class from the given {@link RemoteMethodCall} using reflection.
     *
     * @param remoteClientInvocation A description of what method with what arguments
     *                               need to be invoked on instances of this class
     * @throws IllegalAccessException Reflection related exception.
     * @throws IllegalArgumentException Reflection related exception.
     * @throws InvocationTargetException Reflection related exception.
     * @throws NoSuchMethodException Reflection related exception.
     * @throws SecurityException Reflection related exception.
     */
    public void processRemoteInvocation(RemoteMethodCall remoteClientInvocation)
            throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        String methodName = remoteClientInvocation.getMethodName();
        ArrayList<Object> parameters = remoteClientInvocation
                .getMethodParameters();
        Class<?>[] parametersClasses = new Class[parameters.size()];
        for (int i = 0; i < parameters.size(); i++) {
            parametersClasses[i] = parameters.get(i).getClass();
        }
        this.getClass().getDeclaredMethod(methodName, parametersClasses)
                .invoke(this, parameters.toArray());
    }

    /**
     * Handles a synchronous notification that has been produced by the server
     * in response to a client request.
     * This method is invoked indirectly using reflection.
     *
     * @param clientNotification The produced notification
     */
    private void syncNotification(RRClientNotification clientNotification){
        this.currentRrNotification = clientNotification;
    }

    /**
     * Handles an asynchronous notification that has been produced by the server.
     * This method is invoked indirectly using reflection.
     *
     * @param psNotification The produced notification,
     */
    private void asyncNotification(PSClientNotification psNotification) {
        this.currentPsNotification = psNotification;
        this.guiManager.publishChatMessage(psNotification.getMessage());
        if (psNotification.getHumanWins() || psNotification.getAlienWins()) {
            this.client.setGameStarted(false);
            this.guiManager.setWinnersReaction(psNotification.getHumanWins(),psNotification.getAlienWins());
            this.communicationHandler.getPubSubHandler().setListeningFlag(false);
        }
        if (psNotification.getEscapedPlayer() != null) {
            if (psNotification.getEscapedPlayer().equals(this.client.getPlayer().getPlayerToken())){
                this.client.getPlayer().setPlayerState(PlayerState.ESCAPED);
                this.guiManager.setPlayerStateReaction();
                this.client.setGameStarted(false);
            }
        }
        if (psNotification.getDeadPlayers().contains(this.client.getPlayer().getPlayerToken())) {
            this.client.getPlayer().setPlayerState(PlayerState.DEAD);
            this.guiManager.setPlayerStateReaction();
            this.client.setGameStarted(false);
        } else if (psNotification.getAttackedPlayers().contains(this.client.getPlayer().getPlayerToken())) {
            this.guiManager.useObjectCardReaction(new DefenseObjectCard());
        }
    }
    /**
     * Makes the game associated to this client start. This method is invoked indirectly using reflection.
     *
     * @param mapName The name of the game map.
     */
    public void setMapAndStartGame(String mapName) {
        this.client.setGameStarted(true);
        GameMap gameMap;
        GameMapFactory factory;
        switch (mapName) {
            case "GALILEI":
                factory = new GalileiGameMapFactory();
                break;
            case "FERMI":
                factory = new FermiGameMapFactory();
                break;
            case "GALVANI":
                factory = new GalvaniGameMapFactory();
                break;
            default:
                throw new IllegalArgumentException("The type of map is undefined");
        }
        gameMap = factory.makeMap();
        this.client.setGameMap(gameMap);
        if (this.client.getPlayer().getPlayerToken().getPlayerType().equals(PlayerType.ALIEN)) {
            this.client.getPlayer().setCurrentSector(gameMap.getAlienSector());
        } else {
            this.client.getPlayer().setCurrentSector(gameMap.getHumanSector());
        }
        this.client.setGameStarted(true);
        this.guiManager.startGameReaction();
    }
    /**
     * Makes the client send a chat message to the other players. This action is validated and registered by contacting the game server.
     *
     * @param message The message to be sent.
     */
    public void sendMessage(String message) {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(message);
        parameters.add(this.client.getPlayer().getPlayerToken());
        try {
            RemoteMethodCall remoteMethodCall = this.communicationHandler.newComSession(
                    new RemoteMethodCall(this.serverMethodsNameProvider.publishChatMsg(), parameters));
            this.processRemoteInvocation(remoteMethodCall);
            this.guiManager.setConnectionActiveReaction(true);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            this.guiManager.setConnectionActiveReaction(false);
        }
    }
    /**
     * Makes the client attack a sector at given coordinates. This action is validated and registered by contacting the game server.
     *
     * @param coordinate The coordinates of the sector to be attacked.
     */
    public void attack(Coordinate coordinate) {
        boolean humanAttack = this.client.getPlayer().getPlayerToken().getPlayerType().equals(PlayerType.HUMAN);
        Sector targetSector = this.client.getGameMap().getSectorByCoords(coordinate);
        ArrayList<Object> parameters = new ArrayList<>();
        AttackObjectCard card = null;
        if (humanAttack) {
            card = new AttackObjectCard(targetSector);
            Action action = new UseObjAction(card);
            parameters.add(action);
            parameters.add(this.client.getPlayer().getPlayerToken());

        } else {
            Action action = new MoveAttackAction(targetSector);
            parameters.add(action);
            parameters.add(this.client.getPlayer().getPlayerToken());
        }
        try {
            RemoteMethodCall remoteMethodCall = this.communicationHandler.newComSession(
                    new RemoteMethodCall(this.serverMethodsNameProvider.makeAction(), parameters));
            this.processRemoteInvocation(remoteMethodCall);
            this.guiManager.setConnectionActiveReaction(true);
            if (client.isGameStarted()){
                this.guiManager.displayResponseMsg(this.currentRrNotification.getMessage());
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            this.guiManager.setConnectionActiveReaction(false);
        }
        boolean isActionServerValidated = this.currentRrNotification.getActionResult();
        if (isActionServerValidated){
            this.client.move(coordinate);
            if (humanAttack){
                this.guiManager.useObjectCardReaction(card);
            }
            this.guiManager.moveToSectorReaction();
        }
    }
    /**
     * Sets the games that are running of waiting to be run on the game server.
     * This method is executed indirectly using reflection.
     *
     * @param avGames The list of data relative to the games available on the server.
     */
    private void setAvailableGames(ArrayList<GamePublicData> avGames) {
        this.availableGames = avGames;
        this.guiManager.setAvailableGamesReaction();
    }

    /**
     * Sets the identification token for the client and subscribes the client to asynchronous
     * notification/method calls by the server.
     * This method is invoked indirectly using reflection.
     *
     * @param playerToken The client's identification token.
     */
    private void setPlayerTokenAndSubscribe(PlayerToken playerToken) {
        this.client.getPlayer().setPlayerToken(playerToken);
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(playerToken);
        try {
            this.communicationHandler.newComSession(
                    new RemoteMethodCall(this.serverMethodsNameProvider.subscribe(),parameters));
            this.guiManager.setConnectionActiveReaction(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            this.guiManager.setConnectionActiveReaction(false);
        }

    }
    /**
     * Requests to the game server a list of available games.
     */
    public void getGames() {
        try {
            RemoteMethodCall methodCall = this.communicationHandler.newComSession(new RemoteMethodCall(this.serverMethodsNameProvider.getGames(), new ArrayList<>()));
            this.processRemoteInvocation(methodCall);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            this.guiManager.setConnectionActiveReaction(false);
        }
    }
    /**
     * Requests to the server the start of a created game without waiting for 8 players to join the game.
     */
    public void onDemandGameStart() {
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(this.client.getPlayer().getPlayerToken());
        try {
            RemoteMethodCall methodCall = this.communicationHandler.newComSession(
                    new RemoteMethodCall(this.serverMethodsNameProvider.onDemandGameStart(), parameters));
            this.processRemoteInvocation(methodCall);
            this.guiManager.setConnectionActiveReaction(true);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            this.guiManager.setConnectionActiveReaction(false);
        }
    }
    public RRClientNotification getCurrentRrNotification(){
        return this.currentRrNotification;
    }
    public PSClientNotification getCurrentPsNotification(){
        return this.currentPsNotification;
    }
    public List<GamePublicData> getAvailableGames(){
        return this.availableGames;
    }
}
