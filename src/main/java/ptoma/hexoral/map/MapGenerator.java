package ptoma.hexoral.map;


public class MapGenerator {
	public void generateMap (Map myMap, int groundPercentage) {
		
		//-----------------USING PERLIN NOISE------------------------
		
		//Decide number of ground hexagons
		int hexagonNo = myMap.sizeX * myMap.sizeY, countL=0, countS=0;
		//System.out.println("Number of total hexagons is " + hexagonNo);
		//int groundHexNo = (int) ((groundPercentage) * (hexagonNo/100));
		//System.out.println("Number of ground hexagons is " + groundHexNo);
		
		//long seed = (long) (Math.random()*1000000000);
		//int seed = (int) (Math.random());
		float groundPer = (float) (1.6 * (groundPercentage/100));
		//PerlinNoise perlinTest = new PerlinNoise(seed, ((myMap.sizeX+myMap.sizeY)/2)/10 );
		PerlinNoise perlinTest = new PerlinNoise();
		//PerlinNoise perlinTest = new PerlinNoise(seed, 100);
		
		//float x = myMap.sizeX/100;
		//float y = myMap.sizeY/100;
		//float tempGen2 = perlinTest.noise2(x,y);
		//System.out.println(tempGen2);
		
		for (int i = 0; i < myMap.sizeX; i++) {
			for (int j = 0; j < myMap.sizeY; j++) {
				
				float tempGen = perlinTest.turbulence2((float)(i+Math.random()),(float)(j+Math.random()),(float) 0.09);
				
				//System.out.println(tempGen);
				if (tempGen > 0.0) {
					myMap.matrix[i][j] = new Hexagon(Hexagon.type.SEA);
				 	countL++;
				}
				else {
					myMap.matrix[i][j] = new Hexagon(Hexagon.type.LAND);
					countS++;
				}
			}
		}
		
		//myMap.printMap();
		
		//System.out.println("ENDED  Lands=" + countL + " Seas=" + countS);
	}
	
	
	
	public void constructRiver(Map myMap) {
		
	}
}
