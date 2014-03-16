package ptoma.hexoral.map;

import java.util.Random;

public class MapGenerator {
	public void generateMap(WorldMap myMap, int groundPercentage) {

		Biome bio = new Biome("test.bio", myMap);
		Random rand = new Random();

		PerlinNoiseParameters perlinParameters = new PerlinNoiseParameters(
				0.65, 0.1, 1, 2, rand.nextInt());
		PerlinNoiseGenerator myNoise = new PerlinNoiseGenerator(
				perlinParameters);

		for (int i = 0; i < myMap.sizeX; i++) {
			for (int j = 0; j < myMap.sizeY; j++) {
				double m = myNoise.get(i, j);
				myMap.matrix[i][j] = new Hexagon(bio.getType(m,
						myMap.distFromCenterEdges(i, j), i, j), i, j);
			}
		}
	}

}