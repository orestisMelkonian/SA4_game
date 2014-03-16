package ptoma.hexoral.map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

//import java.util.ArrayList;
//import SHIT
public class WorldMap {
	protected int sizeX;
	protected int sizeY;
	protected Hexagon[][] matrix;

	/**
	 * @author Orestis Default constructor
	 */
	public WorldMap() {
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
	public WorldMap(int x, int y) {
		this.sizeX = x;
		this.sizeY = y;

		this.matrix = new Hexagon[sizeX][sizeY];

		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				matrix[i][j] = new Hexagon(Hexagon.type.SEA);
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

	public void printMap() {
		// TODO Auto-generated method stub
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("myPNmap.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				if (this.matrix[i][j].getType() == "SEA")
					writer.print('S');
				else
					writer.print('L');
			}
			writer.println();
		}
		writer.close();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("myPNmap.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	// TODO write javadoc here
	public static double angleBetween(Coords center, Coords current,
			Coords previous) {

		return Math.toDegrees(Math.atan2(current.x - center.x, current.y
				- center.y)
				- Math.atan2(previous.x - center.x, previous.y - center.y));
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
		int count = 0;
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				if (this.matrix[i][j].getType() == "LAND")
					count++;
			}
		}
		return count;
	}

	public int getSeaNo() {
		int count = 0;
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				if (this.matrix[i][j].getType() == "SEA")
					count++;
			}
		}
		return count;
	}

	public int getLakeNo() {
		int count = 0;
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				if (this.matrix[i][j].getType() == "LAKE")
					count++;
			}
		}
		return count;
	}

	public int getMountainNo() {
		int count = 0;
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				if (this.matrix[i][j].getType() == "MOUNTAIN")
					count++;
			}
		}
		return count;
	}

	public Coords findCoastalLand() {
		int i = (int) (Math.random() * this.sizeX);
		int j = (int) (Math.random() * this.sizeY);
		int countS = 0;
		while ((this.matrix[i][j].getType() != "LAND")) {
			// System.out.println("Searching -i="+i+" j="+j+"-- type = " +
			// this.matrix[i][j].getType());
			i = (int) (Math.random() * this.sizeX);
			j = (int) (Math.random() * this.sizeY);
		}

		String area = this.getArea(i, j);
		if (area == "NW") {
			countS = 0;
			ArrayList<Coords> leftNeigh = this.getLeftNeighbours(new Coords(i,
					j));
			for (int k = 0; k < leftNeigh.size(); k++) {
				if (this.matrix[leftNeigh.get(k).x][leftNeigh.get(k).y]
						.getType() == "SEA")
					countS++;
			}
			if (countS >= 2)
				return (new Coords(i, j));
		} else if (area == "NE") {
			countS = 0;
			ArrayList<Coords> leftNeigh = this.getRightNeighbours(new Coords(i,
					j));
			for (int k = 0; k < leftNeigh.size(); k++) {
				if (this.matrix[leftNeigh.get(k).x][leftNeigh.get(k).y]
						.getType() == "SEA")
					countS++;
			}
			if (countS >= 2)
				return (new Coords(i, j));
		} else if (area == "SW") {
			countS = 0;
			ArrayList<Coords> leftNeigh = this.getLeftNeighbours(new Coords(i,
					j));
			for (int k = 0; k < leftNeigh.size(); k++) {
				if (this.matrix[leftNeigh.get(k).x][leftNeigh.get(k).y]
						.getType() == "SEA")
					countS++;
			}
			if (countS >= 2)
				return (new Coords(i, j));
		} else if (area == "SE") {
			countS = 0;
			ArrayList<Coords> leftNeigh = this.getRightNeighbours(new Coords(i,
					j));
			for (int k = 0; k < leftNeigh.size(); k++) {
				if (this.matrix[leftNeigh.get(k).x][leftNeigh.get(k).y]
						.getType() == "SEA")
					countS++;
			}
			if (countS >= 2)
				return (new Coords(i, j));
		} else
			System.out.println("ERROR! in getArea()");
		return null;

	}

	public Coords findSeaNextToLand() {
		int i = (int) (Math.random() * this.sizeX);
		int j = (int) (Math.random() * this.sizeY);

		while ((this.matrix[i][j].getType() != "SEA")
				&& (this.matrix[i][j].getType() != "LAKE")) {
			i = (int) (Math.random() * this.sizeX);
			j = (int) (Math.random() * this.sizeY);
		}

		ArrayList<Coords> toCheck = this.getNeighbours(new Coords(i, j));
		for (int k = 0; k < toCheck.size(); k++) {
			if ((this.matrix[toCheck.get(k).x][toCheck.get(k).y].getType() == "LAND")
					|| (this.matrix[toCheck.get(k).x][toCheck.get(k).y]
							.getType() == "MOUNTAIN")) {
				return (new Coords(i, j));
			}
		}
		return null;
	}

	public Coords findLandNextToSea() {
		int i = (int) (Math.random() * this.sizeX);
		int j = (int) (Math.random() * this.sizeY);

		while ((this.matrix[i][j].getType() != "LAND")
				&& (this.matrix[i][j].getType() != "MOUNTAIN")) {
			// System.out.println("Searching -i="+i+" j="+j+"-- type = " +
			// this.matrix[i][j].getType());
			i = (int) (Math.random() * this.sizeX);
			j = (int) (Math.random() * this.sizeY);
		}

		ArrayList<Coords> toCheck = this.getNeighbours(new Coords(i, j));
		for (int k = 0; k < toCheck.size(); k++) {
			if ((this.matrix[toCheck.get(k).x][toCheck.get(k).y].getType() == "SEA")
					|| (this.matrix[toCheck.get(k).x][toCheck.get(k).y]
							.getType() == "LAKE")) {
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
				this.matrix[p.x][p.y].setType(Hexagon.type.SEA);
			}
		} else { // add land / remove sea
			System.out.println("Must remove " + (waterNo - realWaterNo)
					+ " sea cells");
			for (int i = 0; i < (waterNo - realWaterNo); i++) {
				Coords p = null;
				while (p == null)
					p = this.findSeaNextToLand();
				this.matrix[p.x][p.y].setType(Hexagon.type.LAND);
			}
		}

		seaNo = this.getSeaNo();
		landNo = this.getLandNo();
		lakeNo = this.getLakeNo();
		mountainNo = this.getMountainNo();
		waterNo = seaNo + lakeNo;
		groundNo = landNo + mountainNo;

		System.out.println("waterNo = " + waterNo + " groundNo = " + groundNo
				+ " realWaterNo = " + realWaterNo + " realGroundNo = "
				+ realGroundNo);
	}

	// --------CLEAN FUNCTIONS

	public void cleanUp() {
		for (int i = 0; i < this.sizeX; i++) {
			for (int l = 0; l < (int) (Math.max(1, sizeY / 50)); l++) {
				this.matrix[i][l].setType(Hexagon.type.SEA);
				this.matrix[i][this.sizeY - (l + 1)].setType(Hexagon.type.SEA);
			}
		}
		for (int i = 0; i < this.sizeY; i++) {
			for (int l = 0; l < (int) (Math.max(1, sizeX / 50)); l++) {
				this.matrix[l][i].setType(Hexagon.type.SEA);
				this.matrix[this.sizeX - (l + 1)][i].setType(Hexagon.type.SEA);
			}
		}
		if (this.sizeX < this.sizeY) {

		}
	}

	public void cleanIsland() {
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				if ((this.matrix[i][j].getType() == "SEA")
						|| (this.matrix[i][j].getType() == "LAKE")) {
					int allLand = 0;
					ArrayList<Coords> toCheck = this.getNeighbours(new Coords(
							i, j));
					for (int k = 0; k < toCheck.size(); k++) {
						if ((this.matrix[toCheck.get(k).x][toCheck.get(k).y]
								.getType() == "SEA")
								|| (this.matrix[toCheck.get(k).x][toCheck
										.get(k).y].getType() == "LAKE"))
							allLand++;
					}
					if (allLand < 2) // only one or none neighboring sea cells
						this.matrix[i][j].setType(Hexagon.type.LAND);
				}
			}
		}
	}

	public void cleanSea() {
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				if ((this.matrix[i][j].getType() == "LAND")
						|| (this.matrix[i][j].getType() == "MOUNTAIN")) {
					int allLand = 0;
					ArrayList<Coords> toCheck = this.getNeighbours(new Coords(
							i, j));
					for (int k = 0; k < toCheck.size(); k++) {
						if ((this.matrix[toCheck.get(k).x][toCheck.get(k).y]
								.getType() == "LAND")
								|| (this.matrix[toCheck.get(k).x][toCheck
										.get(k).y].getType() == "MOUNTAIN"))
							allLand++;
					}
					if (allLand < 2) // only one or none neighboring land cells
						this.matrix[i][j].setType(Hexagon.type.SEA);
				}
			}
		}
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
		return this.matrix[i][j].getType();
	}
}
