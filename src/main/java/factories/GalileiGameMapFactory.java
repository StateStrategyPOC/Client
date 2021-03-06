package factories;

import common.GameMap;

import java.io.File;

/**
 * Represents a factory of Galilei game maps
 * 
 * @see GameMapFactory
 */
public class GalileiGameMapFactory extends GameMapFactory {
	/**
	 * @see GameMapFactory#makeMap()
	 */
	@Override
	public GameMap makeMap() {
		return new GameMap(this.makeGraph(new File("maps/Galileo_map.txt")), 97, 1,
				23, 14, "GALILEI");
	}
}
