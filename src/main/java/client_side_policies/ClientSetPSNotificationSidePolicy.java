package client_side_policies;

import client_store.ClientState;
import client_store.ClientStore;
import client_store.SidePolicy;
import client_store_actions.*;
import common.StoreAction;
import common.PSClientNotification;

public class ClientSetPSNotificationSidePolicy implements SidePolicy {
    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientStore CLIENT_STORE = ClientStore
                .getInstance();
        ClientSetCurrentPubSubNotificationAction castedAction = (ClientSetCurrentPubSubNotificationAction) action;
        PSClientNotification notification = castedAction.getPsNotification();
        if (notification.isGameCanBeStarted()){
            CLIENT_STORE.propagateAction(new ClientStartableGameAction());
        }
        if (notification.isGameNeedToStart()){
            CLIENT_STORE.propagateAction(new ClientStartGameAction(notification.getGameMapName()));
        }
        if (notification.isTurnNeedToEnd()){
            CLIENT_STORE.propagateAction(new ClientRequestEndTurnAction());
        }
        if (notification.isTurnNeedToStart()){
            CLIENT_STORE.propagateAction(new ClientStartTurnAction());
        }
        if (notification.getAlienWins() || notification.getHumanWins()){
            CLIENT_STORE.propagateAction(new ClientSetWinnersAction(notification.getAlienWins(),notification.getHumanWins()));
        }
    }
}
