package ptoma.hexoral;

public class Hexagon {
	static public enum directions {TOP_LEFT,TOP_RIGHT,RIGHT,BOTTOM_RIGHT,BOTTOM_LEFT,LEFT};
	static public enum type {SEA,LAND};
	private type terrainType;
	
	public Hexagon(type _terrainType) {
		terrainType = _terrainType;
	}
	
	@Override
	public String toString() {
		return "Hello world from Hexagon";
	}
	
	/**
	 * Return the type of the Hexagon like SEA or LAND etc...
	 * @return String The type of the Hexagon
	 */
	public String getType() {
		return terrainType.toString();
	}
}