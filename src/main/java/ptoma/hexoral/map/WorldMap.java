package ptoma.hexoral.map;

import java.awt.Point;
import java.util.ArrayList;

import com.google.common.collect.Table;
import com.google.common.collect.HashBasedTable;

/**
 * The container of the map. Using hashmaps.
 * 
 * @author steve
 * 
 */
public class WorldMap {

	/**
	 * The size X of the map.
	 */
	protected int sizeX;
	/**
	 * The size Y of the map.
	 */
	protected int sizeY;
	/**
	 * Hash table maping coordinates to Hexagons.
	 */
	protected Table<Integer, Integer, Hexagon> matrix;
	/**
	 * According to the poapular type, these are not stored inside the map.
	 */
	private Hexagon.type popularType;
	/**
	 * Holds Information about the count of types.
	 */
	private int[] count;

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
	public WorldMap(final int x, final int y) {
		this.popularType = Hexagon.type.SEA;
		this.sizeX = x;
		this.sizeY = y;
		this.count = new int[Hexagon.type.values().length];
		for (int i = 0; i < this.count.length; i++) {
			this.count[i] = 0;
		}
		this.count[this.popularType.ordinal()] = this.sizeX * this.sizeY;
		this.matrix = HashBasedTable.create();
	}

	/**
	 * sizeX getter.
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @return width of the map
	 */
	public final int getSizeX() {
		return this.sizeX;
	}

	/**
	 * sizeX setter.
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @param x
	 *            desired width of the map
	 */
	public final void setSizeX(final int x) {
		this.sizeX = x;
	}

	/**
	 * sizeY getter.
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @return height of the map
	 */
	public final int getSizeY() {
		return this.sizeY;
	}

	/**
	 * sizeY setter.
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @param y
	 *            desired height of the map
	 */
	public final void setSizeY(final int y) {
		this.sizeY = y;
	}

	/**
	 * @author Orestis Melkonian
	 * 
	 * @return String Map dimension
	 */
	public final String getDimension() {
		return this.sizeX + "x" + this.sizeY;
	}

	/**
	 * 
	 * @param i
	 *            is the X coordinate
	 * @param j
	 *            is the Y coordinate
	 * @return a string of the type of the hexagon
	 */
	public final String getType(final int i, final int j) {
		return this.getHexagon(i, j).getType();
	}

	/**
	 * Adds a new hexagon to the hash map and updates the count array. error
	 * when type == popularType
	 * 
	 * @param x
	 *            coordinate
	 * @param y
	 *            coordinate
	 * @param type
	 *            of the Hexagon to be created
	 */
	public final void setHexagon(final int x, final int y,
			final Hexagon.type type) {
		final Hexagon previous = this.matrix.get(x, y);
		final Hexagon ret = new Hexagon(type, x, y);
		if ((previous == null) && (type != this.popularType)) {
			this.matrix.put(x, y, ret);
			this.count[this.popularType.ordinal()]--;
			this.count[ret.getOrdinalType()]++;
		} else if (previous != null && previous.getType() != ret.getType()) {
			this.count[previous.getOrdinalType()]--;
			this.count[ret.getOrdinalType()]++;
			if (type == this.popularType) {
				this.matrix.remove(x, y);
			} else {
				this.matrix.put(x, y, ret);
			}
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
	public final Hexagon getHexagon(final int x, final int y) {
		final Hexagon ret = this.matrix.get(x, y);
		if (ret == null) {
			return new Hexagon(this.popularType, x, y);
		} else {
			return ret;
		}
	}

	/**
	 * Erases the map completely.
	 */
	public final void erase() {
		this.matrix.clear();
		for (int i = 0; i < this.count.length; i++) {
			this.count[i] = 0;
		}
		this.count[this.popularType.ordinal()] = this.sizeX * this.sizeY;
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
		// if row is even
		if (w.x % 2 == 0) {
			if (w.x > 0) {
				// TOP RIGHT
				ret.add(new Point(w.x - 1, w.y));
				if (w.y > 0) {
					// TOP LEFT
					ret.add(new Point(w.x - 1, w.y - 1));
				}
			}
			if (w.x < this.sizeX - 1) {
				// BOTTOM RIGHT
				ret.add(new Point(w.x + 1, w.y));
				if (w.y > 0) {
					// BOTTOM LEFT
					ret.add(new Point(w.x + 1, w.y - 1));
				}
			}
		} else {
			// else row is odd
			if (w.x > 0) {
				// TOP LEFT
				ret.add(new Point(w.x - 1, w.y));
				if (w.y < this.sizeY - 1) {
					// TOP RIGHT
					ret.add(new Point(w.x - 1, w.y + 1));
				}
			}
			if (w.x < this.sizeX - 1) {
				// BOTTOM LEFT
				ret.add(new Point(w.x + 1, w.y));
				if (w.y > this.sizeY - 1) {
					// BOTTOM RIGHT
					ret.add(new Point(w.x + 1, w.y + 1));
				}
			}
		}
		if (w.y < this.sizeY - 1) {
			// RIGHT
			ret.add(new Point(w.x, w.y + 1));
		}
		if (w.y > 0) {
			// LEFT
			ret.add(new Point(w.x, w.y - 1));
		}
		return ret;
	}

	/**
	 * Returns the closest border (N,E,S,W) of the given point.
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @param x
	 *            horizontal coordinate
	 * @param y
	 *            vertical coordinate
	 * @return closest border (N,E,S,W)
	 */
	public final char distFromBorderNESW(final int x, final int y) {
		int distN = 0;
		int distE = 0;
		int distS = 0;
		int distW = 0;
		char ret;
		distN = x;
		distE = this.sizeY - 1 - y;
		distS = this.sizeX - 1 - x;
		distW = y;

		final int distMin = Math.min(distW,
				Math.min(distE, Math.min(distN, distS)));

		if (distMin == distN) {
			ret = 'N';
		} else if (distMin == distE) {
			ret = 'E';
		} else if (distMin == distS) {
			ret = 'S';
		} else if (distMin == distW) {
			ret = 'W';
		} else {
			ret = 'R';
		}
		return ret;
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
	public static double angleBetween(final Point center, final Point current,
			final Point previous) {

		return Math.toDegrees(Math.atan2(current.x - center.x, current.y
				- center.y)
				- Math.atan2(previous.x - center.x, previous.y - center.y));
	}

	/**
	 * Calculates the distance from the given point to the center.
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @param x
	 *            horizontal coordinate
	 * @param y
	 *            vertical coordinate
	 * @return distance from center of the given point
	 */
	public final int distFromCenterEdges(final int x, final int y) {
		return (int) (new Point(x, y).distance(new Point(this.sizeX / 2,
				this.sizeY / 2)));
	}

	/**
	 * Counts the number of land cells on the map.
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @return number of Land cells on the map
	 */
	public final int getLandNo() {
		return this.count[Hexagon.type.LAND.ordinal()];
	}

	/**
	 * Counts the number of sea cells on the map.
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @return number of Sea cells on the map
	 */
	public final int getSeaNo() {
		return this.count[Hexagon.type.SEA.ordinal()];
	}

	/**
	 * Counts the number of lake cells on the map.
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @return number of Lake cells on the map
	 */
	public final int getLakeNo() {
		return this.count[Hexagon.type.LAKE.ordinal()];
	}

	/**
	 * Counts the number of mountain cells on the map.
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @return number of Mountain cells on the map
	 */
	public final int getMountainNo() {
		return this.count[Hexagon.type.MOUNTAIN.ordinal()];
	}

}
