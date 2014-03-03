package ptoma.hexoral;

import java.util.ArrayList;

public class Map {
	private ArrayList<Hexagon> list = new ArrayList<Hexagon>();
	private int sizeX;
	private int sizeY;
	
	/**
	 * @author Orestis
	 * Default constructor 
	 */
	public Map () {
		this.sizeX = 30;
		this.sizeY = 30;
		
		/*
		for (int i=0; i<=this.sizeX; i++) {
			for (int j=0; j<=this.sizeY; j++) {
				this.list.add(new Hexagon(Hexagon.type.LAND)); 
			}
		}
		*/
	}
	
	/**
	 * Constructor with parameters
	 * @param x  horizontal dimension
	 * @param y  vertical dimension
	 */
	public Map (int x , int y) {
		this.sizeX = x;
		this.sizeY = y;
		
		/*
		for (int i=0; i<=this.sizeX; i++) {
			for (int j=0; j<=this.sizeY; j++) {
				this.list.add(new Hexagon(Hexagon.type.LAND)); 
			}
		}
		*/
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
	 * sizeX setter
	 * @author Orestis
	 * @param the new sizeX value
	 */
	public void setSizeX(int x) {
		this.sizeX = x;
	}
	
	/**
	 * sizeY getter
	 *	@author Orestis
	 * @return int sizeY
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
	
	
	
}
