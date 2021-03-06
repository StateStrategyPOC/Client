package client;

import common.ObjectCard;

import javax.swing.*;

/**
 * Represents an object card label displayed in the gui
 *
 */
public class ObjectCardLabel extends JLabel {
	private static final long serialVersionUID = 1L;
	private final ObjectCard objectCard;

	/**
	 * Constructs an object card label displayed in the gui. This label is
	 * constructed from the object card it refers to.
	 * 
	 * @param objectCard
	 *            the object card associated with this label
	 */
	public ObjectCardLabel(ObjectCard objectCard) {
		this.objectCard = objectCard;
	}

	/**
	 * Gets the object card associated with the label
	 * 
	 * @return the object card associated with the label
	 */
	public ObjectCard getCard() {
		return objectCard;
	}
}
