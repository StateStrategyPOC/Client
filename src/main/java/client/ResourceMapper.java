package client;

import common.Card;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a mapper that maps a card in the game with its image(filename)
 * 
 * @author Andrea Sessa
 * @author Giorgio Pea
 * @version 1.0
 */
public class ResourceMapper {
	private final Map<String, String> cardMapper;

	/**
	 * Constructs a mapper that maps a card with its image(filename). This
	 * mapper is initialized with the right associations between every card used
	 * in the game and its corresponding image(filename)
	 */
	public ResourceMapper() {
		cardMapper = new HashMap<>();

		cardMapper.put("AdrenalineObjectCard", "cards" + File.separator
				+ "Adrenaline.png");
		cardMapper.put("SuppressorObjectCard", "cards" + File.separator
				+ "Sedatives.png");
		cardMapper.put("LightObjectCard", "cards" + File.separator
				+ "Spotlight.png");
		cardMapper.put("AttackObjectCard", "cards" + File.separator
				+ "Attack.png");
		cardMapper.put("TeleportObjectCard", "cards" + File.separator
				+ "Teleport.png");
		cardMapper.put("DefenseObjectCard", "cards" + File.separator
				+ "Defense.png");

		cardMapper.put("LocalNoiseSectorCard", "cards" + File.separator
				+ "LocalNoise.png");
		cardMapper.put("GlobalNoiseSectorCard", "cards" + File.separator
				+ "GlobalNoiseCard.png");
		cardMapper.put("SilenceSectorCard", "cards" + File.separator
				+ "SilenceCard.png");

		cardMapper.put("RescueCardGREEN", "cards" + File.separator
				+ "RescueOpen.png");
		cardMapper.put("RescueCardRED", "cards" + File.separator
				+ "RescueClosed.png");
	}

	/**
	 * Gets the image associated with a given card in the game
	 * 
	 * @param card
	 *            the card for which retrieve its associated image
	 * @return the image associated with the given card in the game
	 */
	public String getCardImage(Card card) {
		return cardMapper.get(card.toString());
	}
}
