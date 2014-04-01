
package userStoriesBatch1;

import static org.junit.Assert.*;

import java.io.Console;
import java.io.IOException;

import org.junit.Test;

import ptoma.hexoral.map.MapGenerator;
import ptoma.hexoral.map.WorldMap;


public class UserStory01 {

	@Test
	public void test() {
		System.out.println("---------------USER STORY 01---------------");
		int width = 10;
		int height = 10;
		int groundPer = 50;
		int waterPer = 10;
		int resourcePer = 10;
		boolean lakes = true;
		boolean rivers = true;
		
		WorldMap myMap = new WorldMap(width, height);
		
		myMap.randomizeIsland(groundPer, waterPer, lakes, rivers, resourcePer);
		System.out.println("Map generated with "+ width +" width, " + height + " height, " + groundPer + "% ground, " + waterPer + "% water inside the island, " + resourcePer + "% resources in the island.");
	}

}
