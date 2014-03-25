package ptoma.hexoral.map;

import java.awt.Point;
import java.util.ArrayList;

import com.google.common.collect.Table;
import com.google.common.collect.HashBasedTable;

public class WorldMap {
	protected int sizeX;
	protected int sizeY;
	protected Table<Integer, Integer, Hexagon> matrix;
	protected Hexagon.type popularType;
	protected int count[];

	/**
	 * Constructor of WorldMap.
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @param x
	 *            width
	 * @param y
	 *            height
	 */
	public WorldMap(int x, int y) {
		this.popularType = Hexagon.type.SEA;
		this.sizeX = x;
		this.sizeY = y;
		this.count = new int[Hexagon.type.values().length];
		for (int i = 0; i < this.count.length; i++) {
			this.count[i] = 0;
		}
		this.count[this.popularType.ordinal()] = this.sizeX*this.sizeY;
		this.matrix = HashBasedTable.create();
	}

	/**
	 * sizeX getter
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @return width of the map
	 */
	public int getSizeX() {
		return (this.sizeX);
	}

	/**
	 * sizeX setter
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @param desired
	 *            width of the map
	 */
	public void setSizeX(int x) {
		this.sizeX = x;
	}

	/**
	 * sizeY getter
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @return height of the map
	 */
	public int getSizeY() {
		return (this.sizeY);
	}

	/**
	 * sizeY setter
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @param desired
	 *            height of the map
	 */
	public void setSizeY(int y) {
		this.sizeY = y;
	}

	/**
	 * @author Orestis Melkonian
	 * 
	 * @return String Map dimension
	 */
	public String getDimension() {
		return (this.sizeX + "x" + this.sizeY);
	}

	/**
	 * 
	 * @param i
	 *            is the X coordinate
	 * @param j
	 *            is the Y coordinate
	 * @return a string of the type of the hexagon
	 */
	public String getType(int i, int j) {
		return this.getHexagon(i, j).getType();
	}

	/**
	 * Adds a new hexagon to the hash map and updates the count array.
	 * error when type == popularType
	 * 
	 * @param x
	 *            coordinate
	 * @param y
	 *            coordinate
	 * @param type
	 *            of the Hexagon to be created
	 */
	public void setHexagon(int x, int y, Hexagon.type type) {
		Hexagon previous = this.matrix.get(x, y);
		Hexagon ret = new Hexagon(type, x, y);
		if ((previous == null) && (type != this.popularType)) {
			this.matrix.put(x, y, ret);
			this.count[this.popularType.ordinal()]--;
			this.count[ret.getOrdinalType()]++;
		} 
		else if (previous != null && previous.getType() != ret.getType()) {
			this.count[previous.getOrdinalType()]--;
			this.count[ret.getOrdinalType()]++;
			if (type == this.popularType)
				this.matrix.remove(x, y);
			else
				this.matrix.put(x, y, ret);
		}
	}

	/**
	 * Return a hexagon object of the specified coordinates.
	 * 
	 * @param x
	 *            coordinate
	 * @param y
	 *            coordinate
	 * @return Hexagon Object of the specified coordinates.
	 */
	public Hexagon getHexagon(int x, int y) {
		Hexagon ret = this.matrix.get(x, y);
		if (ret == null) {
			return new Hexagon(this.popularType, x, y);
		} else {
			return ret;
		}
	}

	/**
	 * Erases the map completely.
	 */
	public void erase() {
		this.matrix.clear();
		for (int i = 0; i < this.count.length; i++) {
			this.count[i] = 0;
		}
		this.count[this.popularType.ordinal()] = this.sizeX*this.sizeY;
	}

	/**
	 * Will return a list of the Points of every hexagons near the given
	 * hexagon.
	 * 
	 * @author Stefanos Gatsios
	 * 
	 * @param w
	 *            is the point in map from which to return the neighbor cells.
	 * @return ArrayList of Points
	 */
	public ArrayList<Point> getNeighbours(Point w) {
		ArrayList<Point> ret = new ArrayList<Point>();
		if (w.x % 2 == 0) { // if row is even
			if (w.x > 0) {
				ret.add(new Point(w.x - 1, w.y)); // TOP_RIGHT
				if (w.y > 0) {
					ret.add(new Point(w.x - 1, w.y - 1)); // TOP_LEFT
				}
			}
			if (w.x < this.sizeX - 1) {
				ret.add(new Point(w.x + 1, w.y)); // BOTTOM_RIGHT
				if (w.y > 0) {
					ret.add(new Point(w.x + 1, w.y - 1)); // BOTTOM_LEFT
				}
			}
		} else { // else row is odd
			if (w.x > 0) {
				ret.add(new Point(w.x - 1, w.y)); // TOP_LEFT
				if (w.y < this.sizeY - 1) {
					ret.add(new Point(w.x - 1, w.y + 1)); // TOP_RIGHT
				}
			}
			if (w.x < this.sizeX - 1) {
				ret.add(new Point(w.x + 1, w.y)); // BOTTOM_LEFT
				if (w.y > this.sizeY - 1) {
					ret.add(new Point(w.x + 1, w.y + 1)); // BOTTOM_RIGHT
				}
			}
		}
		if (w.y < this.sizeY - 1) {
			ret.add(new Point(w.x, w.y + 1)); // RIGHT
		}
		if (w.y > 0) {
			ret.add(new Point(w.x, w.y - 1)); // LEFT
		}
		return ret;
	}

	/**
	 * Returns the closest border (N,E,S,W) of the given point
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @param x
	 *            horizontal coordinate
	 * @param y
	 *            vertical coordinate
	 * @return closest border (N,E,S,W)
	 */
	public char distFromBorderNESW(int x, int y) {
		int distN = 0, distE = 0, distS = 0, distW = 0;

		distN = x;
		distE = this.sizeY - 1 - y;
		distS = this.sizeX - 1 - x;
		distW = y;

		int distMin = Math.min(distW, Math.min(distE, Math.min(distN, distS)));

		if (distMin == distN)
			return 'N';
		else if (distMin == distE)
			return 'E';
		else if (distMin == distS)
			return 'S';
		else if (distMin == distW)
			return 'W';
		else
			return 'R';
	}

	/**
	 * Computes the angle between three points in degrees.
	 * 
	 * @param center
	 *            point
	 * @param current
	 *            point
	 * @param previous
	 *            point
	 * @return the angle between them
	 */
	public static double angleBetween(Point center, Point current,
			Point previous) {

		return Math.toDegrees(Math.atan2(current.x - center.x, current.y
				- center.y)
				- Math.atan2(previous.x - center.x, previous.y - center.y));
	}

	/**
	 * Calculates the distance from the given point to the center
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @param x
	 *            horizontal coordinate
	 * @param y
	 *            vertical coordinate
	 * @return distance from center of the given point
	 */
	public int distFromCenterEdges(int x, int y) {
		return (int) (new Point(x, y).distance(new Point(sizeX / 2, sizeY / 2)));
	}

	/**
	 * Counts the number of land cells on the map
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @return number of Land cells on the map
	 */
	public int getLandNo() {
		return this.count[Hexagon.type.LAND.ordinal()];
	}

	/**
	 * Counts the number of sea cells on the map
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @return number of Sea cells on the map
	 */
	public int getSeaNo() {
		return this.count[Hexagon.type.SEA.ordinal()];
	}

	/**
	 * Counts the number of lake cells on the map
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @return number of Lake cells on the map
	 */
	public int getLakeNo() {
		return this.count[Hexagon.type.LAKE.ordinal()];
	}

	/**
	 * Counts the number of mountain cells on the map
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @return number of Mountain cells on the map
	 */
	public int getMountainNo() {
		return this.count[Hexagon.type.MOUNTAIN.ordinal()];
	}

}
