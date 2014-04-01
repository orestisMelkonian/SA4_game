package hexoral.world;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ptoma.hexoral.exception.GameException;
import ptoma.hexoral.exception.InvalidPointException;
import ptoma.hexoral.game.Game;
import ptoma.hexoral.game.action.AttackAction;
import ptoma.hexoral.game.action.MoveAction;
import ptoma.hexoral.map.Hexagon;
import ptoma.hexoral.units.Soldier;
import ptoma.hexoral.units.Unit;
import ptoma.hexoral.user.Player;

public class WorldTest {

	static Game world;

	@BeforeClass
	public static void testSetup() {
		world = new Game(4, 4);
	}

	@Before
	public void gererateTest() {
		world.island.setHexagon(0, 0, Hexagon.type.LAND);
		world.island.setHexagon(0, 1, Hexagon.type.LAND);
		world.island.setHexagon(1, 0, Hexagon.type.LAND);
		world.island.setHexagon(1, 1, Hexagon.type.LAND);
		Point x;
	}

	@Test
	public void test() {
		Player p1 = new Player("Steve", world);
		Player p2 = new Player("Orestis",world);
		world.addPlayer(p1);
		world.addPlayer(p2);
		Unit u1 = new Soldier(p1, new Point(0,0));
		Unit u2 = new Soldier(p1, new Point(0,1));
		Unit u3 = new Soldier(p2, new Point(1,0));
		Unit u4 = new Soldier(p2, new Point(1,1));
		world.createUnit(p1, u1);
		world.createUnit(p1, u2);
		world.createUnit(p2, u3);
		world.createUnit(p2, u4);

		try {
			p1.getSchedule().addAction(new MoveAction(p1, u1, new Point(1, 0)));
		} catch (InvalidPointException e) {
			System.err.println(e.getMessage());
		}
		p1.getSchedule().addAction(new AttackAction(p1, u1, u2));
		
		try {
			world.executeTurn();
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("Actions were not reseted after execution", 0, p1.getSchedule().size());

		for (Unit u : world.getPlayerUnits(p1)) {
			System.err.println(u.toString());
		}
		world.removePlayer(p1);
		assertEquals("The unit was not deleted.", 0, world.getPlayerUnits(p1)
				.size());
	}
	
	@Test
	public void testNeighhbours() {
		List<Point> even = world.island.getNeighbours(new Point(1,1));
		List<Point> evenCorrect = new ArrayList<Point>();
		List<Point> odd = world.island.getNeighbours(new Point(2,1));
		List<Point> oddCorrect = new ArrayList<Point>();
		evenCorrect.add(new Point(0,1));
		evenCorrect.add(new Point(0,2));
		evenCorrect.add(new Point(1,2));
		evenCorrect.add(new Point(2,2));
		evenCorrect.add(new Point(2,1));
		evenCorrect.add(new Point(1,0));
		//---------------------------
		oddCorrect.add(new Point(1,0));
		oddCorrect.add(new Point(1,1));
		oddCorrect.add(new Point(2,2));
		oddCorrect.add(new Point(3,1));
		oddCorrect.add(new Point(3,0));
		oddCorrect.add(new Point(2,0));
		assertTrue("Odd neighbours error",oddCorrect.containsAll(odd));
		assertTrue("Even neighbours error",evenCorrect.containsAll(even));		
	}

}
