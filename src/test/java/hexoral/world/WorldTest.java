package hexoral.world;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.BeforeClass;
import org.junit.Test;

import ptoma.hexoral.game.Game;
import ptoma.hexoral.map.WorldMap;
import ptoma.hexoral.units.MediumAirUnit;
import ptoma.hexoral.units.Unit;
import ptoma.hexoral.user.Player;

public class WorldTest {

	static Game world;
	
	@BeforeClass
	public static void testSetup() {
		world = new Game(500, 500);
		assertEquals("The map is not of expected size X", world.island.getSizeX(), 500);
		assertEquals("The map is not of expected size Y", world.island.getSizeY(), 500);
	}
	
	@Test
	public void test() {
		Player p1 = new Player("Steve");
		world.addPlayer(p1);
		world.createUnit(p1,(Unit) new MediumAirUnit(),new Point(1,2));
		world.createUnit(p1,(Unit) new MediumAirUnit(),new Point(2,3));
		assertEquals("Player couldn't create 2 units", 2, world.getPlayerUnits(p1).size());
	}

}
