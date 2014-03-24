package ptoma.hexoral.map;

import java.awt.Point;

// TODO Add more types to enum type

/**
 * Hexagon tiles.
 * 
 * @author Stefanos
 * 
 */
public class Hexagon {
	/**
	 * A standard way to communicate for the directions.
	 * 
	 * @author steve_000
	 * 
	 */
	static public enum directions {
		LEFT, TOP_LEFT, TOP_RIGHT, RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
	};

	/**
	 * Types of the hexagons.
	 * 
	 * @author steve_000
	 * 
	 */
	static public enum type {
		SEA, LAND, MOUNTAIN, LAKE
	};

	/**
	 * Type of terrain.
	 */
	private type terrainType;
	/**
	 * Coordinates of hexagon.
	 */
	private Point w;

	/**
	 * Basic Constructor of Hexagon. Coords are (0,0)
	 * 
	 * @param terrainType
	 *            for type of terrain
	 */
	public Hexagon(type terrainType) {
		this.terrainType = terrainType;
		this.w = new Point(0, 0);
	}

	/**
	 * Constructor for hexagon based on terrain and location.
	 * 
	 * @param terrainType
	 *            for type of terrain
	 * @param w
	 *            for the location of the hexagon
	 */
	public Hexagon(type terrainType, Point w) {
		this.terrainType = terrainType;
		this.w = w;
	}

	/**
	 * Constructor for Hexagons based on location represented in ints.
	 * 
	 * @param terrainType
	 *            type of terrain.
	 * @param x
	 *            X coordinate.
	 * @param y
	 *            Y coordinate.
	 */
	public Hexagon(type terrainType, int x, int y) {
		this.terrainType = terrainType;
		this.w = new Point(x, y);
	}

	@Override
	public String toString() {
		return "Hello world from Hexagon";
	}

	/**
	 * @author Stefanos Return the type of the Hexagon like SEA or LAND etc...
	 * @return String The type of the Hexagon
	 */
	public String getType() {
		return terrainType.toString();
	}

	/**
	 * Setter for type of hexagon.
	 * 
	 * @author Stefanos
	 * @param terrainType
	 *            of type Hexagon.type
	 */
	public void setType(type terrainType) {
		this.terrainType = terrainType;
	}

	/**
	 * Getter for coords of hexagon.
	 * 
	 * @return Coords of Hexagon
	 */
	public Point getCoords() {
		return this.w;
	}

	/**
	 * Setter for coordinates for hexagon.
	 * 
	 * @param x
	 *            X coordinate
	 * @param y
	 *            Y coordinate
	 */
	public void setCoords(int x, int y) {
		this.w = new Point(x, y);
	}

	/**
	 * Return the integer assigned to the enum.
	 * 
	 * @return int of the ordinal of the enum
	 */
	public int getOrdinalType() {
		return this.terrainType.ordinal();
	}
}
