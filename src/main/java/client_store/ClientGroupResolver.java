package client_store;

import client_state_policies.*;

import java.util.HashMap;
import java.util.Map;

public class ClientGroupResolver extends Resolver {


    public ClientGroupResolver() {
        super();

    }

    @Override
    protected void fillStatePolicyMap() {
        this.actionsToStatePolicies.put("@CLIENT_START_GAME", new StartGameStatePolicy());
        this.actionsToStatePolicies.put("@CLIENT_START_TURN", new StartTurnStatePolicy());
        this.actionsToStatePolicies.put("@CLIENT_MOVE_TO_SECTOR", new MoveToSectorStatePolicy());
        this.actionsToStatePolicies.put("@CLIENT_TELEPORT_TO_STARTING_SECTOR", new TeleportStatePolicy());
        this.actionsToStatePolicies.put("@CLIENT_USE_OBJECT_CARD", new UseObjectCardStatePolicy());
        this.actionsToStatePolicies.put("@CLIENT_SET_PLAYER", new SetPlayerStatePolicy());
        this.actionsToStatePolicies.put("@CLIENT_ADRENALINE", new AdrenalineStatePolicy());
        this.actionsToStatePolicies.put("@CLIENT_DISCARD_OBJECT_CARD", new AdrenalineStatePolicy());
        this.actionsToStatePolicies.put("@CLIENT_END_TURN", new EndTurnStatePolicy());
        this.actionsToStatePolicies.put("@CLIENT_SET_AVAILABLE_GAMES", new SetAvailableGamesStatePolicy());
        this.actionsToStatePolicies.put("@CLIENT_PUBLISH_MSG", new PublishMsgStatePolicy());
        this.actionsToStatePolicies.put("@CLIENT_SET_RR", new SetRRStatePolicy());
        this.actionsToStatePolicies.put("@CLIENT_SET_PS", new SetPSStatePolicy());
        this.actionsToStatePolicies.put("@CLIENT_ASK_SECTOR_TO_LIGHT", new AskSectorToLightStatePolicy());
        this.actionsToStatePolicies.put("@CLIENT_SUPPRESS", new SuppressStatePolicy());
        this.actionsToStatePolicies.put("@CLIENT_SET_CONNECTION_ALIVE", new SetConnectionActiveStatePolicy());
        this.actionsToStatePolicies.put("@CLIENT_SET_DRAWN_SECTOR_OBJECT_CARD", new SetDrawnSectorObjectCardStatePolicy());
        this.actionsToStatePolicies.put("@CLIENT_SET_PLAYER_STATE", new SetPlayerStatePolicy());
        this.actionsToStatePolicies.put("@CLIENT_SET_WINNERS", new SetWinnersStatePolicy());
        this.actionsToStatePolicies.put("@CLIENT_STARTABLE_GAME", new StartableGameStatePolicy());
        this.actionsToStatePolicies.put("@CLIENT_SET_REQUEST", new SetRequestStatePolicy());

    }

    @Override
    protected void fillSidePolicyMap() {
        this.actionsToSidePolicies.put();
        this.actionsToSidePolicies.put("@CLIENT_START_GAME", new StartGameStatePolicy());
        this.actionsToSidePolicies.put("@CLIENT_START_GAME", new StartGameStatePolicy());
        this.actionsToSidePolicies.put("@CLIENT_START_GAME", new StartGameStatePolicy());
        this.actionsToSidePolicies.put("@CLIENT_START_GAME", new StartGameStatePolicy());
    }

}
