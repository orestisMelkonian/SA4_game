package ptoma.hexoral.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import ptoma.hexoral.map.Hexagon;
import ptoma.hexoral.map.Map;

/**
 * A class for representing the biome of the world. i.e. the properties of the
 * world, where land can be reated etce...
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
	 * Map a value from a range to a range, actually copied from arduino library
	 * @param x value to be mapped
	 * @param in_min from Range of numbers lower bound
	 * @param in_max from range of numbers upper bound
	 * @param out_min to range of numbers lower bound
	 * @param out_max to range of numbesr upper bound
	 * @return
	 */
	private int map(double x, int in_min, int in_max, int out_min, int out_max) {
		return (int) ((x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min);
	}
	
	/**
	 * 
	 * @param perlinNoise the value of the noise for the current cell
	 * @param distance max from the sides of the table
	 * @return Hexagon.type the type of the cell
	 */
	public Hexagon.type getType(double perlinNoise, int distance) {
		int maxSideMap = Math.max(this.pMap.sizeX, this.pMap.sizeY);
		int axisX = map(distance,0,maxSideMap/2,0,this.size-1); //Subtruction because it's zero based
		int axisY = map(perlinNoise,-1,1,0,this.size-1);
		if(axisY<0 || axisY < 0) {
			System.out.print("Hello");
		}
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
		default:
			ret = null;
			break;
		}
		return ret;
	}
	
}
