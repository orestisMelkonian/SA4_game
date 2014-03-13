package ptoma.hexoral.map;

import java.util.Random;

public class MapGenerator {
	public void generateMap(Map myMap, int groundPercentage) {

		Biome bio = new Biome("test.bio", myMap);
		Random rand = new Random();

		PerlinNoiseParameters perlinParameters = new PerlinNoiseParameters(0.5,
				0.1, 1, 6, rand.nextInt());
		PerlinNoiseGenerator myNoise = new PerlinNoiseGenerator(
				perlinParameters);

		
		for (int i = 0; i < myMap.sizeX; i++) {
			for (int j = 0; j < myMap.sizeY; j++) {
				double m = myNoise.get(i, j);
				myMap.matrix[i][j] = new Hexagon(bio.getType(m,
						myMap.distFromEdges(i, j)), i, j);
			}
		}
	}
	
}
