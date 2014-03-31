package hexoral.world;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ptoma.hexoral.exception.AttackException;
import ptoma.hexoral.exception.InvalidPointException;
import ptoma.hexoral.game.Game;
import ptoma.hexoral.game.action.AttackAction;
import ptoma.hexoral.game.action.MoveAction;
import ptoma.hexoral.map.Hexagon;
import ptoma.hexoral.map.MapGenerator;
import ptoma.hexoral.units.Soldier;
import ptoma.hexoral.units.Unit;
import ptoma.hexoral.user.Player;

public class WorldTest {

	static Game world;

	@BeforeClass
	public static void testSetup() {
		world = new Game(2, 2);
		assertEquals("The map is not of expected size X",
				world.island.getSizeX(), 2);
		assertEquals("The map is not of expected size Y",
				world.island.getSizeY(), 2);
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
<<<<<<< HEAD
<<<<<<< HEAD
		Unit u1 = new Soldier(p1, new Point(2, 5));
		Unit u2 = new Soldier(p1, new Point(2, 5));
=======
=======
>>>>>>> 06656e5af19ba8c8e311a2f85b8c00a67bf7b55f
		world.addPlayer(p2);
		Unit u1 = new Soldier(p1, 0, 0);
		Unit u2 = new Soldier(p1, 0, 1);
		Unit u3 = new Soldier(p2, 1, 0);
		Unit u4 = new Soldier(p2, 1, 1);
<<<<<<< HEAD
>>>>>>> 57a4b2a... Update on actions for attackexceptions
=======
>>>>>>> 06656e5af19ba8c8e311a2f85b8c00a67bf7b55f
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
		} catch (AttackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Unit u : world.getPlayerUnits(p1)) {
			System.err.println(u.toString());
		}
		world.removePlayer(p1);
		assertEquals("The unit was not deleted.", 0, world.getPlayerUnits(p1)
				.size());
	}

}
