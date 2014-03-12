package ptoma.hexoral.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.apache.tools.ant.types.resources.First;

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
	 * Basic biome class.
	 * 
	 * @param filename
	 *            the name of the file containing information about the biome
	 *            system
	 * 
	 */
	public Biome(String filename) {
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
			System.err.println("Didn't found Biome File in " + this.PATH);
			e.printStackTrace();
		}

	}
	
	/**
	 * Quantizes a float to the depth of the quantization according
	 * to to the min and max value and the size of the biome.
	 * @param value to be mapped
	 * @param min range of values
	 * @param max range of values 
	 * @return
	 */
	private int quantize(double value, double min, double max) {
		return 0;
	}
}
