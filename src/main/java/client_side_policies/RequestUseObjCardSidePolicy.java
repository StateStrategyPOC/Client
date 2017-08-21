package client_side_policies;

import client.ReqRespHandler;
import common.EncodedBehaviourIdentifiers;
import client_store.*;
import client_store_actions.*;
import common.*;

import java.util.ArrayList;

public class RequestUseObjCardSidePolicy implements SidePolicy {


    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientRequestUseObjectCardAction castedAction = (ClientRequestUseObjectCardAction) action;
        ObjectCard objectCard = castedAction.getObjectCard();
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        Player player = state.getPlayer();
        if (player.getPrivateDeck().getContent().contains(objectCard)) {
            if (objectCard instanceof LightsObjectCard) {
                CLIENT_STORE.propagateAction(new ClientAskSectorToLightAction(true));
            } else if (objectCard instanceof AttackObjectCard) {
                CLIENT_STORE.propagateAction(new ClientAskAttackAction(true));
            } else {
                StoreAction action_ = new UseObjAction(objectCard);
                parameters.add(action_);
                parameters.add(player.getPlayerToken());
                ActionOnTheWire request = new ActionOnTheWire(EncodedBehaviourIdentifiers.makeAction(),parameters);
                ReqRespHandler reqRespHandler = ReqRespHandler.getInstance();
                reqRespHandler.initRequestResponse(request);
                boolean isActionServerValidated = state.getCurrentReqRespNotification().isActionResult();
                if (isActionServerValidated && state.isConnectionActive()) {
                    CLIENT_STORE.propagateAction(new ClientUseObjectCardAction(objectCard));
                    if (objectCard instanceof TeleportObjectCard) {
                        CLIENT_STORE.propagateAction(new ClientTeleportAction());
                    } else if (objectCard instanceof SuppressorObjectCard) {
                        CLIENT_STORE.propagateAction(new ClientSuppressAction(true));
                    } else if (objectCard instanceof AdrenalineObjectCard) {
                        CLIENT_STORE.propagateAction(new ClientAdrenlineAction());
                    }
                }

            }
        }
    }
}
