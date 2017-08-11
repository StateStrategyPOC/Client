package client_store;

import client_side_policies.*;
import client_state_policies.*;

public class ClientGroupResolver extends Resolver {


    public ClientGroupResolver() {
        super();

    }

    @Override
    protected void fillPoliciesMap() {
        this.policiesMap.put("@CLIENT_START_GAME",
                new PolicyCouple(new StartGameStatePolicy(), null));
        this.policiesMap.put("@CLIENT_START_TURN",
                new PolicyCouple(new StartTurnStatePolicy(), null));
        this.policiesMap.put("@CLIENT_MOVE_TO_SECTOR",
                new PolicyCouple(new MoveToSectorStatePolicy(), null));
        this.policiesMap.put("@CLIENT_TELEPORT_TO_STARTING_SECTOR",
                new PolicyCouple(new TeleportStatePolicy(), null));
        this.policiesMap.put("@CLIENT_USE_OBJECT_CARD",
                new PolicyCouple(new UseObjectCardStatePolicy(), null));
        this.policiesMap.put("@CLIENT_SET_PLAYER",
                new PolicyCouple(new SetPlayerStatePolicy(), new SetPlayerSidePolicy()));
        this.policiesMap.put("@CLIENT_ADRENALINE",
                new PolicyCouple(new AdrenalineStatePolicy(), null));
        this.policiesMap.put("@CLIENT_DISCARD_OBJECT_CARD",
                new PolicyCouple(new DiscardObjectCardStatePolicy(), null));
        this.policiesMap.put("@CLIENT_END_TURN",
                new PolicyCouple(new EndTurnStatePolicy(), null));
        this.policiesMap.put("@CLIENT_SET_AVAILABLE_GAMES",
                new PolicyCouple(new SetAvailableGamesStatePolicy(), null));
        this.policiesMap.put("@CLIENT_PUBLISH_MSG",
                new PolicyCouple(new PublishMsgStatePolicy(), null));
        this.policiesMap.put("@CLIENT_SET_RR",
                new PolicyCouple(new SetRRStatePolicy(), null));
        this.policiesMap.put("@CLIENT_SET_PS",
                new PolicyCouple(new SetPSStatePolicy(), new ClientSetPSNotificationSidePolicy()));
        this.policiesMap.put("@CLIENT_ASK_FOR_SECTOR_TO_ATTACK",new PolicyCouple(new AskAttackStatePolicy(),null));
        this.policiesMap.put("@CLIENT_ASK_SECTOR_TO_LIGHT",
                new PolicyCouple(new AskSectorToLightStatePolicy(), null));
        this.policiesMap.put("@CLIENT_SUPPRESS",
                new PolicyCouple(new SuppressStatePolicy(), null));
        this.policiesMap.put("@CLIENT_SET_CONNECTION_ACTIVE",
                new PolicyCouple(new SetConnectionActiveStatePolicy(), null));
        this.policiesMap.put("@CLIENT_SET_DRAWN_SECTOR_OBJECT_CARD",
                new PolicyCouple(new SetDrawnSectorObjectCardStatePolicy(), null));
        this.policiesMap.put("@CLIENT_SET_PLAYER_STATE",
                new PolicyCouple(new SetPlayerStatePolicy(), null));
        this.policiesMap.put("@CLIENT_SET_WINNERS",
                new PolicyCouple(new SetWinnersStatePolicy(), null));
        this.policiesMap.put("@CLIENT_STARTABLE_GAME",
                new PolicyCouple(new StartableGameStatePolicy(), null));
        this.policiesMap.put("@CLIENT_SET_REQUEST",
                new PolicyCouple(new SetRequestStatePolicy(), null));
        this.policiesMap.put("@CLIENT_PUBLISH_CHAT_MSG",
                new PolicyCouple(null, new SendChatMessageSidePolicy()));
        this.policiesMap.put("@CLIENT_REQUEST_USE_OBJ_CARD",
                new PolicyCouple(null  , new RequestUseObjCardSidePolicy()));
        this.policiesMap.put("@CLIENT_REQUEST_MOVE_TO_SECTOR",
                new PolicyCouple(null, new RequestMoveToSectorSidePolicy()));
        this.policiesMap.put("@CLIENT_REQUEST_LIGHTS",
                new PolicyCouple(null, new RequestLightsSidePolicy()));
        this.policiesMap.put("@CLIENT_REQUEST_DISCARD_OBJ_CARD",new PolicyCouple(null,new RequestDiscardCardSidePolicy()));
        this.policiesMap.put("@CLIENT_REQUEST_JOIN_NEW_GAME",
                new PolicyCouple(null, new RequestJoinNewGameSidePolicy()));
        this.policiesMap.put("@CLIENT_REQUEST_JOIN_GAME",
                new PolicyCouple(null, new RequestJoinGameSidePolicy()));
        this.policiesMap.put("@CLIENT_REQUEST_ATTACK",new PolicyCouple(null,new RequestAttackSidePolicy()));
        this.policiesMap.put("@CLIENT_REQUEST_GLOBAL_NOISE",
                new PolicyCouple(null, new RequestGlobalNoiseSidePolicy()));
        this.policiesMap.put("@CLIENT_REQUEST_END_TURN",
                new PolicyCouple(null, new RequestEndTurnSidePolicy()));
        this.policiesMap.put("@CLIENT_REQUEST_ATTACK",
                new PolicyCouple(null, new RequestAttackSidePolicy()));
        this.policiesMap.put("@CLIENT_REQUEST_USE_OBJECT_CARD",new PolicyCouple(null,new RequestUseObjCardSidePolicy()));
        this.policiesMap.put("@CLIENT_GET_GAMES",
                new PolicyCouple(null, new GetGamesSidePolicy()));
        this.policiesMap.put("@CLIENT_ON_DEMAND_GAME_START",
                new PolicyCouple(null, new RequestOnDemandGameStartSidePolicy()));
        this.policiesMap.put("@CLIENT_SET_REQUEST", new PolicyCouple(new BlankPolicy(),null));
        this.policiesMap.put("@CLIENT_SET_IN_ROOM",new PolicyCouple(new InRoomStatePolicy(),null));
    }


}
