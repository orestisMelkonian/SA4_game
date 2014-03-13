package ptoma.hexoral.map;

import ptoma.hexoral.map.Hexagon.type;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

//import java.util.ArrayList;
//import SHIT
public class Map {
	protected int sizeX;
	protected int sizeY;
	protected Hexagon[][] matrix;

	/**
	 * @author Orestis Default constructor
	 */
	public Map() {
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
	public Map(int x, int y) {
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
	 * @param x	horizontal coordinate.
	 * @param y	vertical coordinate.
	 * @return	number of cells between point and border
	 */
	public int distFromBorder (int x, int y) {
		int distN=0, distE=0, distS=0, distW=0;

		distN = x;
		distE = this.sizeY - 1 - y;
		distS = this.sizeX - 1 - x;
		distW = y;
		
		return Math.min(distW, Math.min(distE, Math.min(distN, distS)));
	}
	
	public int distFromEdges (int x, int y) 	{
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
	
	public int distFromCenter(int x, int y)	{
		int a = (int) ( Math.pow( Math.abs(y - (this.sizeY-1)/2 ), 2) );
		int b = (int) ( Math.pow( Math.abs(x - (this.sizeX-1)/2 ), 2) );
		return (int) ( Math.sqrt(a + b) );
		
	}
	
	
	
	/**
	 * 
	 * @param i is the X coordinate 
	 * @param j is the Y coordinate
	 * @return a strinf of the type of the hexagon
	 */
	public String getType(int i, int j) {
		return this.matrix[i][j].getType();
	}
}
