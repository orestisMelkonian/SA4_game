package ptoma.hexoral;

public class Hexagon {
	static public enum directions {TOP_LEFT,TOP_RIGHT,RIGHT,BOTTOM_RIGHT,BOTTOM_LEFT,LEFT};
	static public enum type {SEA,LAND};
	/**
	 * Type of terrain
	 */
	private type terrainType;
	
	
	public Hexagon(type terrainType) {
		this.terrainType = terrainType;
	}
	
	@Override
	public String toString() {
		return "Hello world from Hexagon";
	}
	
	/**
	 * @author Stefanos
	 * Return the type of the Hexagon like SEA or LAND etc...
	 * @return String The type of the Hexagon
	 */
	public String getType() {
		return terrainType.toString();

	}
	
	/**
	 * Setter for type of hexagon
	 * @author Stefanos
	 * @param terrainType of type Hexagon.type
	 */
	public void setType(type terrainType) {
		this.terrainType = terrainType;
	}
}
