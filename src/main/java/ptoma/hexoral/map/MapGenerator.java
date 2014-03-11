package ptoma.hexoral.map;


public class MapGenerator {
	public void generateMap (Map myMap, double groundPercentage) {
		
		//-----------------USING PERLIN NOISE------------------------
		
		//Decide number of ground hexagons
		int hexagonNo = myMap.sizeX * myMap.sizeY,countL=0,countS=0;
		//System.out.println("Number of total hexagons is " + hexagonNo);
		//int groundHexNo = (int) ((groundPercentage) * (hexagonNo/100));
		//System.out.println("Number of ground hexagons is " + groundHexNo);
		long seed = (long) (Math.random()*1000000000);
		
		//PerlinNoise perlinTest = new PerlinNoise(seed, ((myMap.sizeX+myMap.sizeY)/2)/10 );
		PerlinNoise perlinTest = new PerlinNoise(seed, 7);

		for (int i = 0; i < myMap.sizeX; i++) {
			for (int j = 0; j < myMap.sizeY; j++) {
				double tempGen = perlinTest.getNoiseLevelAt(i,j);
				if (tempGen < groundPercentage) {
					myMap.matrix[i][j] = new Hexagon(Hexagon.type.LAND);
				 	countL++;
				}
				else {
					myMap.matrix[i][j] = new Hexagon(Hexagon.type.SEA);
					countS++;
				}
			}
		}
		
		myMap.printMap();
		
		System.out.println("ENDED  Lands=" + countL + " Seas=" + countS);
	}
	
	
	
	private void createIsland(Map myMap) {
		
	}
}
