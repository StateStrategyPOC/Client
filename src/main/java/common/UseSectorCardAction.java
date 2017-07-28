package common;

import client_store.StoreAction;

/**
 * Represents the action of using a sector card in the game
 * 
 * @author Andrea Sessa
 * @author Giorgio Pea
 * @version 1.0
 */
public class UseSectorCardAction extends StoreAction {

    private SectorCard sectorCard;

    public UseSectorCardAction(SectorCard sectorCard) {
        super("@GAMEACTION_USE_SECTOR_CARD","@GAMEACTION_GROUP");
        this.sectorCard = sectorCard;
    }

    public SectorCard getSectorCard() {
        return sectorCard;
    }
}
