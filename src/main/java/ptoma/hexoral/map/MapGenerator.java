package ptoma.hexoral.map;

import java.util.Random;

public class MapGenerator {
	public void generateMap(Map myMap, int groundPercentage) {

		Biome bio = new Biome("test.bio", myMap);
		Random rand = new Random();

		PerlinNoiseParameters perlinParameters = new PerlinNoiseParameters(0.5,
				0.1, 1, 7, rand.nextInt());
		PerlinNoiseGenerator myNoise = new PerlinNoiseGenerator(
				perlinParameters);

		int countPos = 0, countNeg = 0;
		for (int i = 0; i < myMap.sizeX; i++) {
			for (int j = 0; j < myMap.sizeY; j++) {
				double m = myNoise.get(i, j);
				myMap.matrix[i][j] = new Hexagon(bio.getType(m,
						myMap.distFromBorder(i, j)), i, j);
			}
		}
		System.out
				.println("countPos = " + countPos + " countNeg = " + countNeg);

		/*
		 * //-----------------USING PERLIN NOISE------------------------
		 * 
		 * //Decide number of ground hexagons //int hexagonNo = myMap.sizeX *
		 * myMap.sizeY, countL=0, countS=0; int countL=0, countS=0;
		 * 
		 * 
		 * //long seed = (long) (Math.random()*1000000000); //int seed = (int)
		 * (Math.random());
		 * 
		 * float groundPer = (float) (1.6 * (groundPercentage/100));
		 * 
		 * //PerlinNoise perlinTest = new PerlinNoise(seed,
		 * ((myMap.sizeX+myMap.sizeY)/2)/10 );
		 * 
		 * PerlinNoise perlinTest = new PerlinNoise();
		 * 
		 * //PerlinNoise perlinTest = new PerlinNoise(seed, 100);
		 * 
		 * //float x = myMap.sizeX/100; //float y = myMap.sizeY/100; //float
		 * tempGen2 = perlinTest.noise2(x,y); //System.out.println(tempGen2);
		 * 
		 * 
		 * 
		 * float total = 0.0f; float temp = 0.0f; float hgrid = myMap.sizeX;
		 * float frequency = 1.0f/(float)hgrid; float persistence = (float)
		 * 0.65; float amplitude = persistence; int octaves = 1;
		 * 
		 * for (int i = 0; i < myMap.sizeX; i++) { for (int j = 0; j <
		 * myMap.sizeY; j++) {
		 * 
		 * 
		 * 
		 * 
		 * 
		 * /* VALUE NOISE total = 0.0f;
		 * 
		 * for (i = 0; i < octaves; ++i) { temp = perlinTest.noise2((float)i *
		 * frequency, (float)j * frequency) * amplitude;
		 * System.out.println(temp); //total += perlinTest.noise2((float)i *
		 * frequency, (float)j * frequency) * amplitude; frequency *= 2;
		 * amplitude *= persistence; }
		 * 
		 * 
		 * if ( (total > 0.5) || (total < -0.5) ) System.out.println(total);
		 */

		// System.out.println(tempGen);

		/*
		 * FIRST IMPLEMENTATION float tempGen =
		 * perlinTest.noise2((float)(i+Math.random()),(float)(j+Math.random()));
		 * if (tempGen > 0.0) { myMap.matrix[i][j] = new
		 * Hexagon(Hexagon.type.SEA); countL++; } else { myMap.matrix[i][j] =
		 * new Hexagon(Hexagon.type.LAND); countS++; }
		 */
		/*
		 * } }
		 * 
		 * //myMap.printMap();
		 * 
		 * System.out.println("ENDED  Lands=" + countL + " Seas=" + countS);
		 */
	}

	public void constructRiver(Map myMap) {

	}
}
