package ptoma.hexoral.map;

import ptoma.hexoral.map.Hexagon.type;

import java.util.ArrayList;

//import java.util.ArrayList;
//import SHIT
public class Map {
	private int sizeX;
	private int sizeY;
	private Hexagon[][] matrix;

	/**
	 * @author Orestis Default constructor
	 */
	public Map() {
		this.sizeX = 30;
		this.sizeY = 30;

		matrix = new Hexagon[sizeX][sizeY];
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				matrix[i][j] = new Hexagon(Hexagon.type.LAND);
			}
		}
	}

	/**
	 * Constructor with parameters.
	 * 
	 * @param x
	 *            horizontal dimension
	 * @param y
	 *            vertical dimension TODO Load map from file functionality. TODO
	 *            Define how the map file will look like.
	 */
	public Map(int x, int y) {
		this.sizeX = x;
		this.sizeY = y;

		this.matrix = new Hexagon[sizeX][sizeY];
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				matrix[i][j] = new Hexagon(Hexagon.type.LAND, i, j);
			}
		}
	}

	/**
	 * sizeX getter
	 * 
	 * @author Orestis
	 * @return int sizeX
	 */
	public int getSizeX() {
		return (this.sizeX);
	}

	/**
	 * sizeX setter
	 * 
	 * @author Orestis
	 * @param the
	 *            new sizeX value
	 */
	public void setSizeX(int x) {
		this.sizeX = x;
	}

	/**
	 * sizeY getter
	 * 
	 * @author Orestis
	 * @return int sizeY
	 */
	public int getSizeY() {
		return (this.sizeY);
	}

	/**
	 * sizeY setter
	 * 
	 * @author Orestis
	 * @param the
	 *            new sizeY value
	 */
	public void setSizeY(int y) {
		this.sizeY = y;
	}

	/**
	 * @author Orestis
	 * @return String Map dimension
	 */
	public String getDimension() {
		return (this.sizeX + "x" + this.sizeY);
	}

	/**
	 * Will return a list of the Coords of every hexagons near the given
	 * hexagon.
	 * 
	 * @param w
	 *            is the point in map from which to return the neighbour cells.
	 * @return ArrayList of Coords
	 */
	public ArrayList<Coords> getNeighbours(Coords w) {
		ArrayList<Coords> ret = new ArrayList<Coords>();
		if (w.x % 2 == 0) { // if row is even
			if (w.x > 0) {
				ret.add(new Coords(w.x - 1, w.y)); // TOP_RIGHT
				if (w.y > 0) {
					ret.add(new Coords(w.x - 1, w.y - 1)); // TOP_LEFT
				}
			}
			if (w.x < this.sizeX - 1) {
				ret.add(new Coords(w.x + 1, w.y)); // BOTTOM_RIGHT
				if (w.y > 0) {
					ret.add(new Coords(w.x + 1, w.y - 1)); // BOTTOM_LEFT
				}
			}
		} else { // else row is odd
			if (w.x > 0) {
				ret.add(new Coords(w.x - 1, w.y)); // TOP_LEFT
				if (w.y < this.sizeY - 1) {
					ret.add(new Coords(w.x - 1, w.y + 1)); // TOP_RIGHT
				}
			}
			if (w.x < this.sizeX - 1) {
				ret.add(new Coords(w.x + 1, w.y)); // BOTTOM_LEFT
				if (w.y > this.sizeY - 1) {
					ret.add(new Coords(w.x + 1, w.y + 1)); // BOTTOM_RIGHT
				}
			}
		}
		if (w.y < this.sizeY - 1) {
			ret.add(new Coords(w.x, w.y + 1)); // RIGHT
		}
		if (w.y > 0) {
			ret.add(new Coords(w.x, w.y - 1)); // LEFT
		}
		return ret;
	}
}
