package ptoma.hexoral.map;

import java.util.ArrayList;

import com.google.common.collect.Table;
import com.google.common.collect.HashBasedTable;

//import java.util.ArrayList;
//import SHIT
public class WorldMap {
	protected int sizeX;
	protected int sizeY;
	protected Table<Integer, Integer, Hexagon> matrix;
	protected Hexagon.type popularType;
	protected int count[];

	/**
	 * Constructor with parameters.
	 * 
	 * @param x
	 *            horizontal dimension
	 * @param y
	 *            vertical dimension
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

	public ArrayList<Coords> getRightNeighbours(Coords w) {
		ArrayList<Coords> ret = new ArrayList<Coords>();
		if (w.x % 2 == 0) { // if row is even
			if (w.x > 0) {
				ret.add(new Coords(w.x - 1, w.y)); // TOP_RIGHT
			}
			if (w.x < this.sizeX - 1) {
				ret.add(new Coords(w.x + 1, w.y)); // BOTTOM_RIGHT
			}
		} else { // else row is odd
			if (w.x > 0) {
				if (w.y < this.sizeY - 1) {
					ret.add(new Coords(w.x - 1, w.y + 1)); // TOP_RIGHT
				}
			}
			if (w.x < this.sizeX - 1) {
				if (w.y > this.sizeY - 1) {
					ret.add(new Coords(w.x + 1, w.y + 1)); // BOTTOM_RIGHT
				}
			}
		}
		if (w.y < this.sizeY - 1) {
			ret.add(new Coords(w.x, w.y + 1)); // RIGHT
		}
		return ret;
	}

	public ArrayList<Coords> getLeftNeighbours(Coords w) {
		ArrayList<Coords> ret = new ArrayList<Coords>();
		if (w.x % 2 == 0) { // if row is even
			if (w.x > 0) {
				if (w.y > 0) {
					ret.add(new Coords(w.x - 1, w.y - 1)); // TOP_LEFT
				}
			}
			if (w.x < this.sizeX - 1) {
				if (w.y > 0) {
					ret.add(new Coords(w.x + 1, w.y - 1)); // BOTTOM_LEFT
				}
			}
		} else { // else row is odd
			if (w.x > 0) {
				ret.add(new Coords(w.x - 1, w.y)); // TOP_LEFT
			}
			if (w.x < this.sizeX - 1) {
				ret.add(new Coords(w.x + 1, w.y)); // BOTTOM_LEFT
			}
		}
		if (w.y > 0) {
			ret.add(new Coords(w.x, w.y - 1)); // LEFT
		}
		return ret;
	}

	/**
	 * Compute distance from closest border.
	 * 
	 * @param x
	 *            horizontal coordinate.
	 * @param y
	 *            vertical coordinate.
	 * @return number of cells between point and border
	 */
	public int distFromBorder(int x, int y) {
		int distN = 0, distE = 0, distS = 0, distW = 0;

		distN = x;
		distE = this.sizeY - 1 - y;
		distS = this.sizeX - 1 - x;
		distW = y;

		return Math.min(distW, Math.min(distE, Math.min(distN, distS)));
	}

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

	public int distFromEdges(int x, int y) {
		int distN = x;
		int distE = this.sizeY - 1 - y;
		int distS = this.sizeX - 1 - x;
		int distW = y;

		int distNW = distN + distW;
		int distNE = distN + distE;
		int distSW = distS + distW;
		int distSE = distS + distE;

		return Math.min(distSW, Math.min(distSE, Math.min(distNW, distNE)));
	}

	public int distFromCenterEdges(int x, int y) {
		int a = (int) (Math.pow(Math.abs(y - (this.sizeY - 1) / 2), 2));
		int b = (int) (Math.pow(Math.abs(x - (this.sizeX - 1) / 2), 2));
		return (int) (Math.sqrt(a + b));
	}

	public int distFromCenter(int x, int y) {
		return ((int) (Math.sqrt((Math.pow(this.sizeX / 2 - x, 2))
				+ (Math.pow(this.sizeY / 2 - y, 2)))));
	}

	public int distFromMiddles(int x, int y) {
		if (x < (this.sizeX - 1) / 2) {
			if (y < (this.sizeX - 1) / 2) // NW
				if (distFromBorderNESW(x, y) == 'N')
					return (x + (this.sizeY - 1) / 2 - y);
				else
					return (y + (this.sizeX - 1) / 2 - x);
			else // NE
			if (distFromBorderNESW(x, y) == 'N')
				return (x + y - (this.sizeY - 1));
			else
				return ((this.sizeY - 1) - y + (this.sizeX - 1) / 2 - x);
		} else {
			if (y < (this.sizeX - 1) / 2) // SW
				if (distFromBorderNESW(x, y) == 'S')
					return ((this.sizeX - 1) - x + (this.sizeY - 1) / 2 - y);
				else
					return (y + (this.sizeX - 1) - x);
			else // SE
			if (distFromBorderNESW(x, y) == 'S')
				return ((this.sizeX - 1) - x + y - (this.sizeY - 1) / 2);
			else
				return ((this.sizeY - 1) - y + x - (this.sizeX - 1));
		}
	}

	/**
	 * Calculates the distance between 2 points.
	 * 
	 * @param p0
	 *            Coords object of first point
	 * @param p1
	 *            Coords object of second point
	 * @return the distance between them
	 */
	public static double distance(Coords p0, Coords p1) {
		return Math.sqrt((p1.x - p0.x) * (p1.x - p0.x) + (p1.y - p0.y)
				* (p1.y - p0.y));
	}

	public String getArea(int x, int y) {
		if (x < (this.sizeX - 1) / 2) {
			if (y < (this.sizeX - 1) / 2) // NW
				return "NW";
			else
				// NE
				return "NE";
		} else {
			if (y < (this.sizeX - 1) / 2) // SW
				return "SW";
			else
				// SE
				return "SE";
		}
	}

	public int getLandNo() {
		return this.count[Hexagon.type.LAND.ordinal()];
	}

	public int getSeaNo() {
		return this.count[Hexagon.type.SEA.ordinal()];
	}

	public int getLakeNo() {
		return this.count[Hexagon.type.LAKE.ordinal()];
	}

	public int getMountainNo() {
		return this.count[Hexagon.type.MOUNTAIN.ordinal()];
	}

	public Coords findSeaNextToLand() {
		int i = (int) (Math.random() * this.sizeX);
		int j = (int) (Math.random() * this.sizeY);

		while ((this.matrix.get(i, j).getType() != "SEA")
				&& (this.matrix.get(i, j).getType() != "LAKE")) {
			i = (int) (Math.random() * this.sizeX);
			j = (int) (Math.random() * this.sizeY);
		}

		ArrayList<Coords> toCheck = this.getNeighbours(new Coords(i, j));
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
			return (new Coords(i, j));
		else
			return null;
	}

	public Coords findLandNextToSea() {
		int i = (int) (Math.random() * this.sizeX);
		int j = (int) (Math.random() * this.sizeY);

		while ((this.matrix.get(i, j).getType() != "LAND")
				&& (this.matrix.get(i, j).getType() != "MOUNTAIN")) {
			// System.out.println("Searching -i="+i+" j="+j+"-- type = " +
			// this.matrix.get(i,j).getType());
			i = (int) (Math.random() * this.sizeX);
			j = (int) (Math.random() * this.sizeY);
		}

		ArrayList<Coords> toCheck = this.getNeighbours(new Coords(i, j));
		for (int k = 0; k < toCheck.size(); k++) {
			if ((this.matrix.get(toCheck.get(k).x, toCheck.get(k).y).getType() == "SEA")) {
				return (new Coords(i, j));
			}
		}
		return null;
	}

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

		System.out.println("waterNo = " + waterNo + " groundNo = " + groundNo
				+ " realWaterNo = " + realWaterNo + " realGroundNo = "
				+ realGroundNo);

		if (waterNo < realWaterNo) { // add sea / remove land
			System.out.println("Must remove " + (groundNo - realGroundNo)
					+ " lands cells");
			for (int i = 0; i < (groundNo - realGroundNo); i++) {
				Coords p = null;
				while (p == null)
					p = this.findLandNextToSea();
				this.matrix.get(p.x, p.y).setType(Hexagon.type.SEA);
			}
		} else { // add land / remove sea
			System.out.println("Must remove " + (waterNo - realWaterNo)
					+ " sea cells");
			for (int i = 0; i < (waterNo - realWaterNo); i++) {
				Coords p = null;
				while (p == null)
					p = this.findSeaNextToLand();
				this.matrix.get(p.x, p.y).setType(Hexagon.type.LAND);
			}
		}

		/*
		 * seaNo = this.getSeaNo(); landNo = this.getLandNo(); lakeNo =
		 * this.getLakeNo(); mountainNo = this.getMountainNo(); waterNo = seaNo
		 * + lakeNo; groundNo = landNo + mountainNo;
		 */
		/*
		 * System.out.println("waterNo = " + waterNo + " groundNo = " + groundNo
		 * + " realWaterNo = " + realWaterNo + " realGroundNo = " +
		 * realGroundNo);
		 */
	}

	// --------CLEAN FUNCTIONS

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

	public void cleanIsland() {
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				if ((this.matrix.get(i, j).getType() == "SEA")
						|| (this.matrix.get(i, j).getType() == "LAKE")) {
					int allLand = 0;
					ArrayList<Coords> toCheck = this.getNeighbours(new Coords(
							i, j));
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

	public void cleanSea() {
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				if ((this.matrix.get(i, j).getType() == "LAND")
						|| (this.matrix.get(i, j).getType() == "MOUNTAIN")) {
					int allLand = 0;
					ArrayList<Coords> toCheck = this.getNeighbours(new Coords(
							i, j));
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

	public void createRiver() {

		Coords p = null;
		while (p == null)
			p = this.findLandNextToSea();
		int i = p.x, j = p.y;
		// Here we have a coastal ground cell
		// Make a river
		this.matrix.get(i, j).setType(Hexagon.type.LAKE);

		int direction = (int) (Math.random() * 6); // pick random direction
		// System.out.println(direction);
		boolean iter = true;
		int counter = 0;
		while (iter == true) {
			// direction = (int) (Math.random() * 7);
			counter++;
			// System.out.println(direction);
			switch (direction) {
			case 0: { // left
				j--;
				break;
			}
			case 1: { // top-left
				if (i % 2 == 0) {
					i--;
					j--;
				} else
					i--;
				break;
			}
			case 2: { // top-right
				if (i % 2 == 0)
					i--;
				else {
					i--;
					j++;
				}
				break;
			}
			case 3: { // right
				j++;
				break;
			}
			case 4: { // bottom-right
				if (i % 2 == 0)
					i++;
				else {
					i++;
					j++;
				}
				break;
			}
			case 5: { // bottom-left
				if (i % 2 == 0) {
					i++;
					j--;
				} else
					i++;
				break;
			}
			}
			if ((this.matrix.get(i, j).getType() == "SEA")
					|| (counter > Math.min(sizeX, sizeY) / 4))
				iter = false;
			this.matrix.get(i, j).setType(Hexagon.type.LAKE);
			/*
			 * double limit; if (Math.max(this.sizeX, this.sizeY) > 300) { limit
			 * = 0.99; } else if (Math.max(this.sizeX, this.sizeY) > 100) {
			 * limit = 0.98; } else if (Math.max(this.sizeX, this.sizeY) > 50) {
			 * limit = 0.95; } else { limit = 0.9999; }
			 * 
			 * if (Math.random() > limit) iter = false;
			 */
		}
	}

	public void createLake() {
		boolean foundSea = true;
		ArrayList<Coords> toCheck = null;
		int i = (int) (Math.random() * this.sizeX);
		int j = (int) (Math.random() * this.sizeY);
		while (foundSea == true) {
			foundSea = false;

			i = (int) (Math.random() * this.sizeX);
			j = (int) (Math.random() * this.sizeY);

			while ((this.matrix.get(i, j).getType() != "LAND")
					&& (this.matrix.get(i, j).getType() != "MOUNTAIN")) {
				// System.out.println("Searching -i="+i+" j="+j+"-- type = " +
				// this.matrix.get(i,j).getType());
				i = (int) (Math.random() * this.sizeX);
				j = (int) (Math.random() * this.sizeY);
			}

			toCheck = this.getNeighbours(new Coords(i, j));
			for (int k = 0; k < toCheck.size(); k++) {
				if (this.matrix.get(toCheck.get(k).x, toCheck.get(k).y)
						.getType() == "SEA") {
					// || (this.matrix.get(toCheck.get(k).x, toCheck.get(k).y)
					// .getType() == "LAKE")) {
					foundSea = true;
				}
			}
		}
		// Here we have a sea cell in the middle of 6 lands
		// Make a lake
		this.matrix.get(i, j).setType(Hexagon.type.LAKE);
		for (int k = 0; k < toCheck.size(); k++) {
			if (Math.random() > 0.1)
				this.matrix.get(toCheck.get(k).x, toCheck.get(k).y).setType(
						Hexagon.type.LAKE);
		}
	}

	public int decideLakeNo(int waterPercentage) {
		int ret = 0;
		int groundNo = this.getLandNo() + this.getMountainNo();
		while (waterPercentage > 0) {
			ret += groundNo / 100;
			waterPercentage -= 5;
		}
		return ret;

	}

	public int decideRiverNo(int waterPercentage) {
		int ret = 0;
		int groundNo = this.getLandNo() + this.getMountainNo();
		while (waterPercentage > 0) {
			ret += groundNo / 100;
			waterPercentage -= 5;
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
	public static double angleBetween(Coords center, Coords current,
			Coords previous) {

		return Math.toDegrees(Math.atan2(current.x - center.x, current.y
				- center.y)
				- Math.atan2(previous.x - center.x, previous.y - center.y));
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
}
