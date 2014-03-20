package ptoma.hexoral.map;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import ptoma.hexoral.map.Hexagon;
import ptoma.hexoral.map.WorldMap;

/**
 * A class for representing the biome of the world. i.e. the properties of the
 * world, where land can be created etc...
 * 
 * @author steve_000
 * 
 */
public class Biome {

	/**
	 * The path of the biome resources.
	 */
	protected static String PATH = "src/main/resources/biomes/";

	/**
	 * File were the biome table will be loaded.
	 */
	protected File inBiome;

	/**
	 * Percentage of land.
	 */
	protected int landPercentage;

	/**
	 * Size of the table (square).
	 */
	protected int size;

	/**
	 * The matrix holding the biome elements represented as chars.
	 */
	private char matrix[][];

	/**
	 * A pointer for the map
	 */
	protected WorldMap pMap;

	protected int angleSize;
	protected int[] angleMatrix;

	/**
	 * Basic biome class.
	 * 
	 * @param filename
	 *            the name of the file containing information about the biome
	 *            system
	 * 
	 */
	public Biome(String filename, WorldMap map) {
		this.angleSize = Math.max(map.sizeX, map.sizeY);
		this.angleMatrix = new int[this.angleSize];
		this.pMap = map;
		inBiome = new File(Biome.PATH + filename);
		try {
			BufferedReader scan = new BufferedReader(new FileReader(inBiome));
			this.size = Integer.parseInt(scan.readLine());
			this.matrix = new char[this.size][this.size];
			for (int i = 0; i < this.size; i++) {
				for (int j = 0; j < this.size; j++) {
					this.matrix[i][j] = (char) scan.read();
				}
				scan.readLine(); // To skip newline sequences
			}
			scan.close();
		} catch (IOException e) {
			System.err.println("Didn't found Biome File in " + Biome.PATH);
			e.printStackTrace();
		}
		for (int i = 0; i < this.angleSize; i++) {
			this.angleMatrix[i] = Biome.map(Math.random(), 0, 1,
					-this.size / 4, this.size / 4);
		}

	}

	public double similarTriangle(Point center, Point point,
			Point reflection, Point edge) {
		/*
		 * double cp = Map.distance(center, point); double cr =
		 * Map.distance(center, reflection); return (cp*ce)/cr
		 */
		double ce = WorldMap.distance(center, edge);
		double angle = Math.cos(WorldMap
				.angleBetween(center, reflection, point));
		return ce / Math.abs(angle);
	}

	/**
	 * Math magic involved Do not write this at home!
	 * 
	 * @param p
	 *            the point
	 * @return the maximum distance from the closest border
	 */
	protected int maxFromBorders(Point p) {
		Point center = new Point(this.pMap.sizeX / 2, this.pMap.sizeY / 2);
		//Coords reflection;
		Point edge;
		char closestBorder = this.pMap.distFromBorderNESW(p.x, p.y);
		int ret;

		switch (closestBorder) {
		case 'N': // done
			//reflection = new Coords(center.x, p.y);
			edge = new Point(center.x, 0);
			break;
		case 'S': // done
			//reflection = new Coords(center.x, p.y);
			edge = new Point(center.x, this.pMap.sizeY - 1);
			break;
		case 'E': // done
			//reflection = new Coords(p.x, center.y);
			edge = new Point(this.pMap.sizeX - 1, center.y);
			break;
		case 'W': // done
			//reflection = new Coords(p.x, center.y);
			edge = new Point(0, center.y);
			break;
		default:
			return 0;
		}

		ret = (int) WorldMap.distance(center, edge);

		// System.out.println(closestBorder + " - " +ret + " point " +
		// p.toString());
		return ret;
	}

	/**
	 * Map a value from a range to a range, actually copied from arduino library
	 * 
	 * @param x
	 *            value to be mapped
	 * @param in_min
	 *            from Range of numbers lower bound
	 * @param in_max
	 *            from range of numbers upper bound
	 * @param out_min
	 *            to range of numbers lower bound
	 * @param out_max
	 *            to range of numbers upper bound
	 * @return
	 */
	static int map(double x, int in_min, int in_max, int out_min, int out_max) {
		int ret = (int) ((x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min);
		ret = (ret > out_max) ? out_max : ret;
		ret = (ret < out_min) ? out_min : ret;
		return ret;
	}

	/**
	 * 
	 * @param perlinNoise
	 *            the value of the noise for the current cell
	 * @param distance
	 *            max from the sides of the table
	 * @return Hexagon.type the type of the cell
	 */
	public Hexagon.type getType(double perlinNoise, int distance, int x, int y) {
		int maxSideMap = this.maxFromBorders(new Point(x, y));
		Point center = new Point(this.pMap.sizeX / 2, this.pMap.sizeY / 2);
		double angle = WorldMap.angleBetween(center, new Point(0, 0),
				new Point(x, y));
		int complexItMore = map(angle, -360, 360, 0, this.angleSize - 1);
		int axisX = map(distance + this.angleMatrix[complexItMore], 0,
				maxSideMap, 0, this.size - 1);

		int axisY = map(perlinNoise, -1, 1, 0, this.size - 1);

		// System.out.println("Angle " + angle + " random " + complexItMore);
		/*
		 if(perlinNoise < 0) { return Hexagon.type.LAND; } else if(
		 perlinNoise >= 0) { return Hexagon.type.SEA; }
		 */
		Hexagon.type ret;
		switch (this.matrix[axisX][axisY]) {
		case 'S':
			ret = Hexagon.type.SEA;
			break;
		case 'L':
			ret = Hexagon.type.LAND;
			break;
		case 'M':
			ret = Hexagon.type.MOUNTAIN;
			break;
		case 'W':
			ret = Hexagon.type.LAKE;
			break;
		default:
			ret = null;
			break;
		}
		return ret;
	}

}
