package client_side_policies;

import common.ActionOnTheWire;
import client.ServerMethodsNameProvider;
import client_store.*;
import client_store_actions.ClientRequestAttackAction;
import client_store_actions.ClientMoveToSectorAction;
import client_store_actions.ClientSetRequestAction;
import client_store_actions.ClientUseObjectCard;
import common.*;

import java.util.ArrayList;

public class RequestAttackSidePolicy implements SidePolicy {
    @Override
    public void apply(ClientState state, StoreAction action) {
        ClientRequestAttackAction castedAction = (ClientRequestAttackAction) action;
        ClientStore CLIENT_STORE = ClientStore.getInstance();
        ServerMethodsNameProvider SERVER_ACTION_WIRE_PROVIDER = ServerMethodsNameProvider.getInstance();
        ArrayList<Object> parameters = new ArrayList<>();
        Coordinate coordinate = castedAction.getCoordinate();
        Player player = CLIENT_STORE.getState().getPlayer();
        boolean humanAttack = player.getPlayerToken().getPlayerType().equals(PlayerType.HUMAN);
        Sector targetSector = CLIENT_STORE.getState().getGameMap().getSectorByCoords(coordinate);
        AttackObjectCard card = null;
        if (humanAttack) {
            card = new AttackObjectCard(targetSector);
            parameters.add(new UseObjAction(card));
            parameters.add(player.getPlayerToken());

        } else {
            parameters.add(new MoveAttackAction(targetSector));
            parameters.add(player.getPlayerToken());
        }
        ActionOnTheWire request = new ActionOnTheWire(SERVER_ACTION_WIRE_PROVIDER.makeAction(),parameters);
        CLIENT_STORE.propagateAction(new ClientSetRequestAction(request));
        boolean isActionServerValidated = CLIENT_STORE.getState().getCurrentReqRespNotification().getActionResult();
        if (isActionServerValidated){
            CLIENT_STORE.propagateAction(new ClientMoveToSectorAction(targetSector));
            if (humanAttack){
                CLIENT_STORE.propagateAction(new ClientUseObjectCard(card));
            }
        }
    }
}
