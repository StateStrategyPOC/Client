package client_store_actions;

import common.ObjectCard;
import common.SectorCard;
import common.StoreAction;

/**
 * An action for signalling that a Sector Card and possibly an Object Card have been drawn
 */
public class ClientSetDrawnSectorObjectCardAction extends StoreAction {

    private final SectorCard drawnSectorCard;
    private final ObjectCard drawnObjectCard;

    public ClientSetDrawnSectorObjectCardAction(SectorCard drawnSectorCard, ObjectCard drawnObjectCard) {
        super("@CLIENT_SET_DRAWN_SECTOR_OBJECT_CARD","@CLIENT_GROUP");
        this.drawnSectorCard = drawnSectorCard;
        this.drawnObjectCard = drawnObjectCard;
    }

    public SectorCard getDrawnSectorCard() {
        return drawnSectorCard;
    }

    public ObjectCard getDrawnObjectCard() {
        return drawnObjectCard;
    }

    @Override
    public String toString() {
        return "ClientSetDrawnSectorObjectCardAction{" +
                "drawnSectorCard=" + drawnSectorCard +
                ", drawnObjectCard=" + drawnObjectCard +
                ", actionIdentifier='" + actionIdentifier + '\'' +
                ", actionGroupIdentifier='" + actionGroupIdentifier + '\'' +
                '}';
    }
}
