package factories;

import common.GameMap;

import java.io.File;

/**
 * Represents a factory of Fermi game maps
 * 
 * @see GameMapFactory
 *
 */
public class FermiGameMapFactory extends GameMapFactory {

	/**
	 * @see GameMapFactory#makeMap()
	 */
	@Override
	public GameMap makeMap() {
		return new GameMap(this.makeGraph(new File("maps/Fermi_map.txt")), 97, 1,
				23, 14, "FERMI");
	}

}
