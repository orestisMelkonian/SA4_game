package ptoma.hexoral.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import ptoma.hexoral.map.Hexagon;
import ptoma.hexoral.map.Map;

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
	protected Map pMap;

	/**
	 * Basic biome class.
	 * 
	 * @param filename
	 *            the name of the file containing information about the biome
	 *            system
	 * 
	 */
	public Biome(String filename, Map map) {
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

	}

	/**
	 * Math magic involved Do not write this at home!
	 * 
	 * @param p
	 *            the point
	 * @return the maximum distance from the closest border
	 */
	protected int maxFromBorders(Coords p) {
		Coords center = new Coords(this.pMap.sizeX / 2, this.pMap.sizeY / 2);
		char closestBorder = this.pMap.distFromBorderNESW(p.x, p.y);
		int ret;
		
		/*
		Coords R = new Coords(center.x , p.y);
		int C_R = center.y - p.y;
		int C_Rt = center.y ;
		int C_p = this.pMap.distFromCenter(p.x, p.y);
		ret = (C_Rt * C_p)/C_R; 
		*/
		
		switch (closestBorder) {
		case 'N':
		case 'S':
			if (Math.abs(p.y - center.y) == 0) {
				ret = this.pMap.sizeY;
				break;
			}
			int dist = this.pMap.distFromCenter(p.x, p.y);
			ret = (Math.abs(center.y * dist))
					/ (Math.abs(p.y - center.y));
			break;
		case 'E':
		case 'W':
			if (Math.abs(p.x - center.x) == 0) {
				ret = this.pMap.sizeX;
				break;
			}
			ret = (Math.abs(center.x * this.pMap.distFromCenter(p.x, p.y)))
					/ (Math.abs(p.x - center.x));
			break;
		default:
			ret = 0;
			break;
		}
		System.out.println(closestBorder + " - " +ret + " point " + p.toString());
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
		int maxSideMap = this.maxFromBorders(new Coords(x, y));
		//int maxSideMap = this.pMap.distFromCenter(0, 0);
		int axisX = map(distance, 0, maxSideMap, 0, this.size - 1); // Subtraction
																	// because
																	// it's zero
																	// based
		int axisY = map(perlinNoise, -1, 1, 0, this.size - 1);
		// int complexItMore = Biome.map(Math.random(),0,1,-2,2);
		// System.out.println("Rand " + complexItMore + " axisX " + axisX);
		// System.out.printf("Distance %d and noise %f maps to [%d][%d]\n",
		// distance,perlinNoise,axisX,axisY);

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
