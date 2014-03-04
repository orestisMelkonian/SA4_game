package ptoma.hexoral;

import java.io.*;
import java.util.Scanner;

public class Map {
	private int sizeX;
	private int sizeY;
	private Hexagon [][] matrix;
	
	/**
	 * @author Orestis
	 * Default constructor 
	 */
	public Map () {
		this.sizeX = 30;
		this.sizeY = 30;
		
		matrix = new Hexagon [sizeX][sizeY];
	}
	
	/**
	 * Constructor with parameters
	 * @param x  horizontal dimension
	 * @param y  vertical dimension
	 */
	public Map (int x , int y) {
		this.sizeX = x;
		this.sizeY = y;
		
		matrix = new Hexagon [sizeX][sizeY];
		for (int i=0; i<this.sizeX; i++) {
			for (int j=0; j<this.sizeY; j++) {
				matrix[i][j] = new Hexagon(Hexagon.type.LAND);
			}
		}
	}
	
	/**
	 * sizeX getter
	 * @author Orestis
	 * @return int sizeX
	 */
	public int getSizeX() {
		return (this.sizeX);
	}
	
	/**
	 * sizeX setter.
	 * @author Orestis.
	 * @param the new sizeX value.
	 */
	public void setSizeX(int x) {
		this.sizeX = x;
	}
	
	/**
	 * sizeY getter.
	 *	@author Orestis.
	 * @return int sizeY.
	 */
	public int getSizeY() {
		return (this.sizeY);
	}
	
	/**
	 * sizeY setter
	 * @author Orestis
	 * @param the new sizeY value
	 */
	public void setSizeY(int y) {
		this.sizeY = y;
	}
	
	
	/**
	 *	@author Orestis
	 * @return String Map dimension
	 */
	public String getDimension() {
		return (this.sizeX+"x"+this.sizeY);
	}
	
	/**
	 * prints type of terrain.
	 * @param x horizontal coordinate.
	 * @param y vertical coordinate.
	 */
	public void getTerrainType(int x,int y) {
		System.out.println(matrix[x][y].getType());
	}
	
	public void fillInMap() {
		
        File text = new File("/Users/usi/Desktop/MAP_01.txt");

        Scanner scnr = null;
		try {
			scnr = new Scanner(text);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        int lineNumber = 0;
        while(lineNumber < this.sizeY){
            String line = scnr.nextLine();
            for (int i = 0; i < this.sizeX; i++) {
            	if (line.charAt(i) == 'S') {
            		matrix[i][lineNumber] = new Hexagon(Hexagon.type.SEA);
            	}
            	else if (line.charAt(i) == 'L') {
            		matrix[i][lineNumber] = new Hexagon(Hexagon.type.LAND);
            	}
            }
            lineNumber++;
        }	
	}
}
