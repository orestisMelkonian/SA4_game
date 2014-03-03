package ptoma.hexoral;

public class Hexagon {
	static public enum directions {TOP_LEFT,TOP_RIGHT,RIGHT,BOTTOM_RIGHT,BOTTOM_LEFT,LEFT};
	static public enum type {SEA,LAND};
	private type terrainType;
	
	@Override
	public String toString() {
		return "Hello world from Hexagon";
	}
}
