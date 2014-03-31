/**
 * 
 */
package Building;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import ptoma.hexoral.building.CreationBuilding;
import ptoma.hexoral.game.Game;
import ptoma.hexoral.units.Soldier;
import ptoma.hexoral.user.Player;

/**
 * @author usi
 *
 */
public class buildingTest {

	@Test
	public void test() {
		Game g = new Game (10 , 10);
		Player p = new Player("Orestis", g);
		CreationBuilding cb= new CreationBuilding(p, new Point(3,3));
		g.createBuilding(p, cb);
		Soldier sol1 = new Soldier(p, new Point(5,5));
		g.createUnit(p, sol1);
		Soldier sol2 = new Soldier(p, new Point(5,5));
		g.createUnit(p, sol2);
		Soldier sol3 = new Soldier(p, new Point(5,5));
		g.createUnit(p, sol3);
		Soldier sol4 = new Soldier(p, new Point(5,5));
		g.createUnit(p, sol4);
		
		cb.storeUnit(sol1);
		cb.storeUnit(sol4);
		
		System.out.print(cb.getNumberOfStoredUnits());
	}

}
