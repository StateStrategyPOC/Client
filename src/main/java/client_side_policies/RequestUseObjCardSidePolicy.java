package client_side_policies;

import client.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.*;
import client_store.Effect;
import client_store_actions.*;
import common.*;

import java.util.ArrayList;

public class RequestUseObjCardSidePolicy implements SidePolicy {


    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientRequestUseObjectCard castedAction = (ClientRequestUseObjectCard) action;
        ObjectCard objectCard = castedAction.getObjectCard();
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ServerMethodsNameProvider SERVER_ACTION_WIRE_PROVIDER = ServerMethodsNameProvider.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        Player player = CLIENT_STORE.getState().getPlayer();
        if (player.getPrivateDeck().getContent().contains(objectCard)) {
            if (objectCard instanceof LightsObjectCard) {
                CLIENT_STORE.dispatchAction(new ClientAskSectorToLightAction(true));
            } else if (objectCard instanceof AttackObjectCard) {
                CLIENT_STORE.dispatchAction(new ClientAskAttackAction(true));
            } else {
                StoreAction action_ = new UseObjAction(objectCard);
                parameters.add(action);
                parameters.add(player.getPlayerToken());
                ActionOnTheWire request = new ActionOnTheWire(SERVER_ACTION_WIRE_PROVIDER.makeAction(),parameters);
                boolean isActionServerValidated = CLIENT_STORE.getState().getCurrentReqRespNotification().getActionResult();
                if (isActionServerValidated) {
                    CLIENT_STORE.dispatchAction(new ClientUseObjectCard(objectCard));
                    if (objectCard instanceof TeleportObjectCard) {
                        CLIENT_STORE.dispatchAction(new ClientTeleportToStartingSectorAction());
                    } else if (objectCard instanceof SuppressorObjectCard) {
                        CLIENT_STORE.dispatchAction(new ClientSuppressAction(true));
                    } else if (objectCard instanceof AdrenalineObjectCard) {
                        CLIENT_STORE.dispatchAction(new ClientAdrenlineAction());
                    }
                }

            }
        }
    }
}
