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
		return this.matrix.get(i, j).getType();
	}

	/**
	 * Adds a new hexagon to the hash map and updates the count array.
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
		if (previous == null) {
			this.matrix.put(x, y, ret);
		} else if (previous != null && previous.getType() != ret.getType()) {
			this.count[previous.getOrdinalType()]--;
		}
		this.count[type.ordinal()]++;
		if (type != this.popularType) {
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

	/**
	 * Finds a sea cell that is next to the coast of the island
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @return the point that is a sea cell next to land
	 */
	public Point findSeaNextToLand() {
		int i = (int) (Math.random() * this.sizeX);
		int j = (int) (Math.random() * this.sizeY);

		while ((this.matrix.get(i, j).getType() != "SEA")
				&& (this.matrix.get(i, j).getType() != "LAKE")) {
			i = (int) (Math.random() * this.sizeX);
			j = (int) (Math.random() * this.sizeY);
		}

		ArrayList<Point> toCheck = this.getNeighbours(new Point(i, j));
		int countL = 0, countS = 0;
		for (int k = 0; k < toCheck.size(); k++) {
			if ((this.matrix.get(toCheck.get(k).x, toCheck.get(k).y).getType() == "LAND")
					|| (this.matrix.get(toCheck.get(k).x, toCheck.get(k).y)
							.getType() == "MOUNTAIN")) {
				countL++;
				if ((this.matrix.get(toCheck.get(k).x, toCheck.get(k).y)
						.getType() == "SEA")
						|| (this.matrix.get(toCheck.get(k).x, toCheck.get(k).y)
								.getType() == "LAKE"))
					countS++;
			}
		}
		if ((countL > 0) && (countS == 0))
			return (new Point(i, j));
		else
			return null;
	}

	/**
	 * Finds a land cell that is on the coast of the island
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @return the point that is a land cell next to sea
	 */
	public Point findLandNextToSea() {
		int i = (int) (Math.random() * this.sizeX);
		int j = (int) (Math.random() * this.sizeY);

		while ((this.matrix.get(i, j).getType() != "LAND")
				&& (this.matrix.get(i, j).getType() != "MOUNTAIN")) {
			// System.out.println("Searching -i="+i+" j="+j+"-- type = " +
			// this.matrix.get(i,j).getType());
			i = (int) (Math.random() * this.sizeX);
			j = (int) (Math.random() * this.sizeY);
		}

		ArrayList<Point> toCheck = this.getNeighbours(new Point(i, j));
		for (int k = 0; k < toCheck.size(); k++) {
			if ((this.matrix.get(toCheck.get(k).x, toCheck.get(k).y).getType() == "SEA")) {
				return (new Point(i, j));
			}
		}
		return null;
	}

	/**
	 * Does necessary editing on the map to fulfill ground percentage
	 * restriction
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @param groundPer
	 *            desired ground percentage of the map
	 */
	public void applyGroundPercentage(int groundPer) {
		// Decide how many must be removed
		int seaNo = this.getSeaNo();
		int landNo = this.getLandNo();
		int lakeNo = this.getLakeNo();
		int mountainNo = this.getMountainNo();
		int waterNo = seaNo + lakeNo;
		int groundNo = landNo + mountainNo;

		int realWaterNo = ((100 - groundPer) * (this.sizeX * this.sizeY)) / 100;
		int realGroundNo = ((groundPer) * (this.sizeX * this.sizeY)) / 100;
		
		/*
		System.out.println("waterNo = " + waterNo + " groundNo = " + groundNo
				+ " realWaterNo = " + realWaterNo + " realGroundNo = "
				+ realGroundNo);
		*/
		if (waterNo < realWaterNo) { // add sea / remove land
			//System.out.println("Must remove " + (groundNo - realGroundNo)
					//+ " lands cells");
			for (int i = 0; i < (groundNo - realGroundNo); i++) {
				Point p = null;
				while (p == null)
					p = this.findLandNextToSea();
				this.matrix.get(p.x, p.y).setType(Hexagon.type.SEA);
			}
		} else { // add land / remove sea
			//System.out.println("Must remove " + (waterNo - realWaterNo)
					//+ " sea cells");
			for (int i = 0; i < (waterNo - realWaterNo); i++) {
				Point p = null;
				while (p == null)
					p = this.findSeaNextToLand();
				this.matrix.get(p.x, p.y).setType(Hexagon.type.LAND);
			}
		}
	}
	
	public void applyWaterPercentage (int waterPer, boolean lakes, boolean rivers)	{
		//Decide how many water cells have to be added
		int groundNo = this.getLandNo() + this.getMountainNo();
		int waterInsideNo = (waterPer*groundNo) / 100;
		
		while (waterInsideNo >0)	{
			if (lakes)	{
				waterInsideNo -= this.createLake();
			}
			if (rivers)	{
				waterInsideNo -= this.createRiver();
			}
		}
		
		//-----TESTING
		int totalNo = this.getLakeNo() + this.getLandNo() + this.getMountainNo();
		int realWaterNo = this.getLakeNo();
		int realGroundNo = this.getLandNo() + this.getMountainNo();
		int waterPercentage = (100 * realWaterNo) / totalNo;
		int groundPercentage = (100 * realGroundNo) / totalNo;
		System.out.println("realWaterNo inside = " + realWaterNo);
		System.out.println("waterPer = " + waterPercentage + " groundPer = " + groundPercentage);
		
	}

	// --------CLEAN FUNCTIONS

	/**
	 * Sets all the cells next to borders to Sea, in order for the map to be an
	 * island
	 * 
	 * @author Orestis Melkonian
	 */
	public void cleanUp() {
		for (int i = 0; i < this.sizeX; i++) {
			for (int l = 0; l < (int) (Math.max(1, sizeY / 50)); l++) {
				this.matrix.get(i, l).setType(Hexagon.type.SEA);
				this.matrix.get(i, this.sizeY - (l + 1)).setType(
						Hexagon.type.SEA);
			}
		}
		for (int i = 0; i < this.sizeY; i++) {
			for (int l = 0; l < (int) (Math.max(1, sizeX / 50)); l++) {
				this.matrix.get(l, i).setType(Hexagon.type.SEA);
				this.matrix.get(this.sizeX - (l + 1), i).setType(
						Hexagon.type.SEA);
			}
		}
		if (this.sizeX < this.sizeY) {

		}
	}

	/**
	 * Cleans the Island of any extra water elements
	 * 
	 * @author Orestis Melkonian
	 */
	public void cleanIsland() {
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				if ((this.matrix.get(i, j).getType() == "SEA")
						|| (this.matrix.get(i, j).getType() == "LAKE")) {
					int allLand = 0;
					ArrayList<Point> toCheck = this.getNeighbours(new Point(i,
							j));
					for (int k = 0; k < toCheck.size(); k++) {
						if ((this.matrix
								.get(toCheck.get(k).x, toCheck.get(k).y)
								.getType() == "SEA")
								|| (this.matrix.get(toCheck.get(k).x,
										toCheck.get(k).y).getType() == "LAKE"))
							allLand++;
					}
					if (allLand < 3) // only one or none neighboring sea cells
						this.matrix.get(i, j).setType(Hexagon.type.LAND);
				}
			}
		}
	}

	/**
	 * Cleans the Sea of any extra land elements
	 * 
	 * @author Orestis Melkonian
	 */
	public void cleanSea() {
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				if ((this.matrix.get(i, j).getType() == "LAND")
						|| (this.matrix.get(i, j).getType() == "MOUNTAIN")) {
					int allLand = 0;
					ArrayList<Point> toCheck = this.getNeighbours(new Point(i,
							j));
					for (int k = 0; k < toCheck.size(); k++) {
						if ((this.matrix
								.get(toCheck.get(k).x, toCheck.get(k).y)
								.getType() == "LAND")
								|| (this.matrix.get(toCheck.get(k).x,
										toCheck.get(k).y).getType() == "MOUNTAIN"))
							allLand++;
					}
					if (allLand < 2) // only one or none neighboring land cells
						this.matrix.get(i, j).setType(Hexagon.type.SEA);
				}
			}
		}
	}

	/**
	 * Creates a river in the Island
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @return the number of land cells converted to sea cells
	 */
	private int createRiver() {
		int ret = 0;
		Point p = null;
		while (p == null)
			p = this.findLandNextToSea();
		// Here we have a coastal ground cell
		this.setHexagon(p.x, p.y, Hexagon.type.SEA);
		ret++;
		boolean iter = true;
		// Pick neighbor land
		while (iter == true) {
			ArrayList<Point> neighbors = this.getNeighbours(p);
			for (int k = 0; k < neighbors.size(); k++) {
				int x = neighbors.get(k).x, y = neighbors.get(k).y;
				Point n = new Point(x, y);
				if ((this.getType(x, y) != Hexagon.type.LAND.toString())
						&& (this.getType(x, y) != Hexagon.type.MOUNTAIN
								.toString())) {
					continue;
				}
				// Check all neighbors
				ArrayList<Point> neigh = this.getNeighbours(n);
				int countS = 0;
				int l;
				for (l = 0; l < neigh.size(); l++) {
					if (this.getType(neigh.get(l).x, neigh.get(l).y) == Hexagon.type.SEA
							.toString())
						countS++;
				}
				if (countS > 1)
					continue;
				p.x = x;
				p.y = y;
				if ((this.matrix.get(p.x, p.y).getType() == Hexagon.type.LAKE
						.toString())
						|| (this.matrix.get(p.x, p.y).getType() == Hexagon.type.SEA
								.toString())) {
					iter = false;
				}
				if (!((this.matrix.get(p.x, p.y).getType() == Hexagon.type.LAKE.toString()) || (this.matrix.get(p.x, p.y).getType() == Hexagon.type.SEA.toString())))
					ret++;
				this.setHexagon(p.x, p.y, Hexagon.type.SEA);
			}
			if (Math.random() > 0.9)
				iter = false;
		}
		return ret;
	}

	/**
	 * Creates a lake in the Island
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @return the number of land cells converted to lake cells
	 */
	private int createLake() {
		int ret = 0;
		boolean foundSea = true;
		ArrayList<Point> toCheck = null;
		int i = (int) (Math.random() * this.sizeX);
		int j = (int) (Math.random() * this.sizeY);
		while (foundSea == true) {
			foundSea = false;

			i = (int) (Math.random() * this.sizeX);
			j = (int) (Math.random() * this.sizeY);

			while ((this.matrix.get(i, j).getType() != "LAND")
					&& (this.matrix.get(i, j).getType() != "MOUNTAIN")) {
				i = (int) (Math.random() * this.sizeX);
				j = (int) (Math.random() * this.sizeY);
			}

			toCheck = this.getNeighbours(new Point(i, j));
			for (int k = 0; k < toCheck.size(); k++) {
				if (this.matrix.get(toCheck.get(k).x, toCheck.get(k).y)
						.getType() == "SEA") {
					foundSea = true;
				}
			}
		}
		// Here we have a sea cell in the middle of 6 lands
		// Make a lake
		this.setHexagon(i, j, Hexagon.type.LAKE);
		ret++;
		for (int k = 0; k < toCheck.size(); k++) {
			if (Math.random() > 0.1) {
				if ((this.matrix.get(toCheck.get(k).x, toCheck.get(k).y).getType() == Hexagon.type.LAND.toString()) || (this.matrix.get(toCheck.get(k).x, toCheck.get(k).y).getType() == Hexagon.type.MOUNTAIN.toString()))
					ret++;
				this.setHexagon(toCheck.get(k).x, toCheck.get(k).y, Hexagon.type.SEA);
				System.out.println("TYPE = " + this.getType(toCheck.get(k).x, toCheck.get(k).y));
			}
		}
		return ret;
	}
}
