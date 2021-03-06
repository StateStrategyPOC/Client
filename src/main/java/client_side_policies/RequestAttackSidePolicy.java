package client_side_policies;

import client.ReqRespHandler;
import common.EncodedBehaviourIdentifiers;
import client_store.*;
import client_store_actions.ClientRequestAttackAction;
import client_store_actions.ClientMoveToSectorAction;
import client_store_actions.ClientUseObjectCardAction;
import common.*;

import java.util.ArrayList;

public class RequestAttackSidePolicy implements SidePolicy {
    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientRequestAttackAction castedAction = (ClientRequestAttackAction) action;
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        Coordinate coordinate = castedAction.getCoordinate();
        Player player = state.getPlayer();
        boolean humanAttack = player.getPlayerToken().getPlayerType().equals(PlayerType.HUMAN);
        Sector targetSector = state.getGameMap().getSectorByCoords(coordinate);
        AttackObjectCard card = null;
        if (humanAttack) {
            card = new AttackObjectCard(targetSector);
            parameters.add(new UseObjAction(card));
            parameters.add(player.getPlayerToken());

        } else {
            parameters.add(new MoveAttackAction(targetSector));
            parameters.add(player.getPlayerToken());
        }
        ActionOnTheWire request = new ActionOnTheWire(EncodedBehaviourIdentifiers.makeAction(),parameters);
        ReqRespHandler reqRespHandler = ReqRespHandler.getInstance();
        reqRespHandler.initRequestResponse(request);
        boolean isActionServerValidated = state.getCurrentReqRespNotification().isActionResult();
        if (isActionServerValidated && state.isConnectionActive()){
            CLIENT_STORE.propagateAction(new ClientMoveToSectorAction(targetSector));
            if (humanAttack){
                CLIENT_STORE.propagateAction(new ClientUseObjectCardAction(card));
            }
        }
    }
}
