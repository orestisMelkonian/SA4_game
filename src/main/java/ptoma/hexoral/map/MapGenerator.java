package ptoma.hexoral.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class MapGenerator {
	WorldMap myMap;

	public MapGenerator(WorldMap myMap, String biomeFile) {
		this.myMap = myMap;

		Biome bio = new Biome(biomeFile, myMap);
		Random rand = new Random();

		PerlinNoiseParameters perlinParameters = new PerlinNoiseParameters(
				0.05, 0.1, 1, 7, rand.nextInt());
		PerlinNoiseGenerator myNoise = new PerlinNoiseGenerator(
				perlinParameters);

		for (int i = 0; i < myMap.sizeX; i++) {
			for (int j = 0; j < myMap.sizeY; j++) {
				double m = myNoise.get(i, j);
				myMap.setHexagon(i, j,
						bio.getType(m, myMap.distFromCenterEdges(i, j), i, j));
			}
		}
	}

	public void applyParameters(int groundPer, int waterPer, boolean lakes,
			boolean rivers) {

		this.applyGroundPercentage(groundPer);
		this.cleanUp();
		this.applyWaterPercentage(waterPer, lakes, rivers);
		this.cleanIsland();
		this.cleanIsland();
		this.cleanSea();
		this.cleanSea();
		this.cleanUp();
	}

	/**
	 * Finds a sea cell that is next to the coast of the island
	 * 
	 * @author Orestis Melkonian
	 * 
	 * @return the point that is a sea cell next to land
	 */
	private Point findSeaNextToLand() {
		int i = (int) (Math.random() * this.myMap.sizeX);
		int j = (int) (Math.random() * this.myMap.sizeY);

		while ((this.myMap.getHexagon(i, j).getType() != "SEA")
				&& (this.myMap.getHexagon(i, j).getType() != "LAKE")) {
			i = (int) (Math.random() * this.myMap.sizeX);
			j = (int) (Math.random() * this.myMap.sizeY);
		}

		ArrayList<Point> toCheck = this.myMap.getNeighbours(new Point(i, j));
		int countL = 0, countS = 0;
		for (int k = 0; k < toCheck.size(); k++) {
			if ((this.myMap.getHexagon(toCheck.get(k).x, toCheck.get(k).y)
					.getType() == "LAND")
					|| (this.myMap.getHexagon(toCheck.get(k).x,
							toCheck.get(k).y).getType() == "MOUNTAIN")) {
				countL++;
				if ((this.myMap.getHexagon(toCheck.get(k).x, toCheck.get(k).y)
						.getType() == "SEA")
						|| (this.myMap.getHexagon(toCheck.get(k).x,
								toCheck.get(k).y).getType() == "LAKE"))
					countS++;
			}
		}
		if (countL > 0)
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
	private Point findLandNextToSea() {
		int i = (int) (Math.random() * this.myMap.sizeX);
		int j = (int) (Math.random() * this.myMap.sizeY);

		while ((this.myMap.getHexagon(i, j).getType() != "LAND")
				&& (this.myMap.getHexagon(i, j).getType() != "MOUNTAIN")) {
			i = (int) (Math.random() * this.myMap.sizeX);
			j = (int) (Math.random() * this.myMap.sizeY);
		}

		ArrayList<Point> toCheck = this.myMap.getNeighbours(new Point(i, j));
		for (int k = 0; k < toCheck.size(); k++) {
			if ((this.myMap.getHexagon(toCheck.get(k).x, toCheck.get(k).y)
					.getType() == "SEA")) {
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
	private void applyGroundPercentage(int groundPer) {
		// Decide how many must be removed
		int seaNo = this.myMap.getSeaNo();
		int landNo = this.myMap.getLandNo();
		int lakeNo = this.myMap.getLakeNo();
		int mountainNo = this.myMap.getMountainNo();
		int waterNo = seaNo + lakeNo;
		int groundNo = landNo + mountainNo;

		int realWaterNo = ((100 - groundPer) * (this.myMap.sizeX * this.myMap.sizeY)) / 100;
		int realGroundNo = ((groundPer) * (this.myMap.sizeX * this.myMap.sizeY)) / 100;

		if (waterNo < realWaterNo) { // add sea / remove land
			for (int i = 0; i < (groundNo - realGroundNo); i++) {
				Point p = null;
				while (p == null)
					p = this.findLandNextToSea();
				this.myMap.setHexagon(p.x, p.y, Hexagon.type.SEA);
			}
		} else { // add land / remove sea
			for (int i = 0; i < (waterNo - realWaterNo); i++) {
				Point p = null;
				while (p == null)
					p = this.findSeaNextToLand();
				this.myMap.setHexagon(p.x, p.y, Hexagon.type.LAND);
			}
		}
		// -----TESTING------
		/*
		 * seaNo = this.myMap.getSeaNo(); landNo = this.myMap.getLandNo();
		 * lakeNo = this.myMap.getLakeNo(); mountainNo =
		 * this.myMap.getMountainNo(); waterNo = seaNo + lakeNo; groundNo =
		 * landNo + mountainNo; System.out.println("Ground Percentage is " +
		 * ((100*groundNo)/(groundNo+waterNo)));
		 */
	}

	private void applyWaterPercentage(int waterPer, boolean lakes,
			boolean rivers) {
		int groundNo = this.myMap.getLandNo() + this.myMap.getMountainNo();
		int waterInsideNo = (waterPer * groundNo) / 100;

		while (waterInsideNo > 0) {
			if (lakes) {
				waterInsideNo -= this.createLake();
				waterInsideNo -= this.createLake();
			}
			if (rivers) {
				waterInsideNo -= this.createRiver();
			}
			if ((!lakes) && (!rivers))
				break;
		}
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
		int seaNo = 3;
		ArrayList<Point> toCheck = null;
		int i = (int) (Math.random() * this.myMap.sizeX);
		int j = (int) (Math.random() * this.myMap.sizeY);
		while (seaNo > 2) {
			seaNo = 0;

			i = (int) (Math.random() * this.myMap.sizeX);
			j = (int) (Math.random() * this.myMap.sizeY);

			while ((this.myMap.getHexagon(i, j).getType() != "LAND")
					&& (this.myMap.getHexagon(i, j).getType() != "MOUNTAIN")) {
				i = (int) (Math.random() * this.myMap.sizeX);
				j = (int) (Math.random() * this.myMap.sizeY);
			}

			toCheck = this.myMap.getNeighbours(new Point(i, j));
			for (int k = 0; k < toCheck.size(); k++) {
				if (this.myMap.getHexagon(toCheck.get(k).x, toCheck.get(k).y)
						.getType() == "SEA") {
					seaNo++;
				}
			}
		}
		this.myMap.setHexagon(i, j, Hexagon.type.LAKE);
		// Here we have a lake cell in the middle of 6 lands
		ret++;
		for (int k = 0; k < toCheck.size(); k++) {
			if (Math.random() > 0.1) {
				if ((this.myMap.getHexagon(toCheck.get(k).x, toCheck.get(k).y)
						.getType() == "LAND")
						|| (this.myMap.getHexagon(toCheck.get(k).x,
								toCheck.get(k).y).getType() == "MOUNTAIN"))
					ret++;
				this.myMap.setHexagon(toCheck.get(k).x, toCheck.get(k).y,
						Hexagon.type.LAKE);
			}
		}
		return ret;
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
		while (p == null) {
			p = this.findLandNextToSea();
		}
		// Here we have a coastal ground cell
		this.myMap.setHexagon(p.x, p.y, Hexagon.type.SEA);
		ret++;
		boolean iter = true;
		// Pick neighbor land
		while (iter == true) {
			ArrayList<Point> neighbors = this.myMap.getNeighbours(p);
			for (int k = 0; k < neighbors.size(); k++) {
				int x = neighbors.get(k).x, y = neighbors.get(k).y;
				Point n = new Point(x, y);
				if ((this.myMap.getType(x, y) != Hexagon.type.LAND.toString())
						&& (this.myMap.getType(x, y) != Hexagon.type.MOUNTAIN
								.toString())) {
					continue;
				}
				// Check all neighbors
				ArrayList<Point> neigh = this.myMap.getNeighbours(n);
				int countS = 0;
				int l;
				for (l = 0; l < neigh.size(); l++) {
					if (this.myMap.getType(neigh.get(l).x, neigh.get(l).y) == Hexagon.type.SEA
							.toString())
						countS++;
				}
				if (countS > 1)
					continue;
				p.x = x;
				p.y = y;
				if ((this.myMap.getHexagon(p.x, p.y).getType() == Hexagon.type.LAKE
						.toString())
						|| (this.myMap.getHexagon(p.x, p.y).getType() == Hexagon.type.SEA
								.toString())) {
					iter = false;
				}
				if (!((this.myMap.getHexagon(p.x, p.y).getType() == Hexagon.type.LAKE
						.toString()) || (this.myMap.getHexagon(p.x, p.y)
						.getType() == Hexagon.type.SEA.toString())))
					ret++;
				this.myMap.setHexagon(p.x, p.y, Hexagon.type.SEA);
			}
			if (Math.random() > 0.8)
				iter = false;
		}
		return ret;
	}

	public void printPercentage() {
		int totalNo = this.myMap.getSeaNo() + this.myMap.getLandNo()
				+ this.myMap.getMountainNo() + this.myMap.getLakeNo();
		System.out.println("TotalNo = " + totalNo);
		int waterInsideNo = this.myMap.getLakeNo();
		int groundNo = this.myMap.getLandNo() + this.myMap.getMountainNo();
		int islandNo = waterInsideNo + groundNo;
		int waterPercentage = (100 * waterInsideNo) / islandNo;
		int groundPercentage = (100 * groundNo) / totalNo;
		// System.out.println("realWaterNo inside = " + realWaterNo);
		System.out.println("waterPer = " + waterPercentage + " groundPer = "
				+ groundPercentage);
	}

	// --------CLEAN FUNCTIONS

	/**
	 * Sets all the cells next to borders to Sea, in order for the map to be an
	 * island
	 * 
	 * @author Orestis Melkonian
	 */
	private void cleanUp() {
		for (int i = 0; i < this.myMap.sizeX; i++) {
			for (int l = 0; l < (int) (Math.max(1, this.myMap.sizeY / 50)); l++) {
				this.myMap.getHexagon(i, l).setType(Hexagon.type.SEA);
				this.myMap.getHexagon(i, this.myMap.sizeY - (l + 1)).setType(
						Hexagon.type.SEA);
			}
		}
		for (int i = 0; i < this.myMap.sizeY; i++) {
			for (int l = 0; l < (int) (Math.max(1, this.myMap.sizeX / 50)); l++) {
				this.myMap.getHexagon(l, i).setType(Hexagon.type.SEA);
				this.myMap.getHexagon(this.myMap.sizeX - (l + 1), i).setType(
						Hexagon.type.SEA);
			}
		}
		if (this.myMap.sizeX < this.myMap.sizeY) {

		}
	}

	/**
	 * Cleans the Island of any extra water elements
	 * 
	 * @author Orestis Melkonian
	 */
	private void cleanIsland() {
		for (int i = 0; i < this.myMap.sizeX; i++) {
			for (int j = 0; j < this.myMap.sizeY; j++) {
				if ((myMap.getHexagon(i, j).getType() == "SEA")
						|| (this.myMap.getHexagon(i, j).getType() == "LAKE")) {
					int allLand = 0;
					ArrayList<Point> toCheck = this.myMap
							.getNeighbours(new Point(i, j));
					for (int k = 0; k < toCheck.size(); k++) {
						if ((this.myMap.getHexagon(toCheck.get(k).x,
								toCheck.get(k).y).getType() == "SEA")
								|| (this.myMap.getHexagon(toCheck.get(k).x,
										toCheck.get(k).y).getType() == "LAKE"))
							allLand++;
					}
					if (allLand < 3) // only one or none neighboring sea cells
						this.myMap.getHexagon(i, j).setType(Hexagon.type.LAND);
				}
			}
		}
	}

	/**
	 * Cleans the Sea of any extra land elements
	 * 
	 * @author Orestis Melkonian
	 */
	private void cleanSea() {
		for (int i = 0; i < this.myMap.sizeX; i++) {
			for (int j = 0; j < this.myMap.sizeY; j++) {
				if (!(this.myMap.getHexagon(i, j).getType() == "SEA")) {
					int allLand = 0;
					ArrayList<Point> toCheck = this.myMap
							.getNeighbours(new Point(i, j));
					for (int k = 0; k < toCheck.size(); k++) {
						if (!(this.myMap.getHexagon(i, j).getType() == "SEA"))
							allLand++;
					}
					if (allLand < 3) // no neighboring land cells
						this.myMap.setHexagon(i, j, Hexagon.type.SEA);
				}
			}
		}
	}

}
