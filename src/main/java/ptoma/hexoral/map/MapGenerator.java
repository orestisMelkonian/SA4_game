package ptoma.hexoral.map;

public class MapGenerator {
	public void generateMap (Map myMap, int groundPercentage) {
		
		/*Decide number of ground hexagons*/
		int hexagonNo = myMap.sizeX * myMap.sizeY;
		int groundHexNo = (groundPercentage) * (hexagonNo/100);
		System.out.println("Number of ground hexagons is " + groundHexNo);
	}
}
