package common;

import client_store.StoreAction;

/**
 * Represents the action of drawing a rescue card from the rescue card deck
 * 
 * @author Andrea Sessa
 * @author Giorgio Pea
 *
 */
public class DrawRescueCardAction extends StoreAction {

	public DrawRescueCardAction() {
        super("@GAMEACTION_DRAW_RESCUE_CARD","@GAMEACTION_GROUP");
	}
}
