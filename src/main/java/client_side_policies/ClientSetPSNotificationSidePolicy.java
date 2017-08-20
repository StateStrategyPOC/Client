package client_side_policies;

import client_store.ClientState;
import client_store.ClientStore;
import client_store.SidePolicy;
import client_store_actions.*;
import common.*;

public class ClientSetPSNotificationSidePolicy implements SidePolicy {
    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientStore CLIENT_STORE = ClientStore
                .getInstance();
        ClientSetCurrentPSNotificationAction castedAction = (ClientSetCurrentPSNotificationAction) action;
        PSNotification notification = castedAction.getPsNotification();
        if (notification.isGameCanBeStarted()){
            CLIENT_STORE.propagateAction(new ClientStartableGameAction());
        }
        if (notification.isGameNeedsToStart()){
            CLIENT_STORE.propagateAction(new ClientStartGameAction(notification.getGameMapName()));
        }
        if (notification.isTurnNeedsToEnd()){
            CLIENT_STORE.propagateAction(new ClientEndTurnAction());
        }
        if (notification.isTurnNeedsToStart()){
            CLIENT_STORE.propagateAction(new ClientStartTurnAction());
        }
        if (notification.isAlienWin() || notification.isHumanWin()){
            CLIENT_STORE.propagateAction(new ClientSetWinnersAction(notification.isAlienWin(),notification.isHumanWin()));
        }
        if (notification.getAttackedPlayers() != null){
            for (PlayerToken playerToken : notification.getAttackedPlayers()){
                if (playerToken.equals(CLIENT_STORE.getState().getPlayer().getPlayerToken())){
                    if (CLIENT_STORE.getState().getPlayer().getPrivateDeck().getContent().contains(new DefenseObjectCard())){
                        CLIENT_STORE.propagateAction(new UseObjAction(new DefenseObjectCard()));
                    }
                }
            }
        }
        if (notification.getEscapedPlayer() != null){
            if (notification.getEscapedPlayer().equals(CLIENT_STORE.getState().getPlayer().getPlayerToken())){
                CLIENT_STORE.propagateAction(new ClientSetPlayerStateAction(PlayerState.ESCAPED));
            }
            else {
                CLIENT_STORE.propagateAction(new ClientDisableRescueSector(notification.getEscapingSector()));
            }
        }

    }
}
