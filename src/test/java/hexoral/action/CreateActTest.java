/**
 * 
 */
package hexoral.action;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import ptoma.hexoral.building.CreationBuilding;
import ptoma.hexoral.game.Game;
import ptoma.hexoral.game.action.CreateAction;
import ptoma.hexoral.units.Soldier;
import ptoma.hexoral.user.Player;

/**
 * @author Orestis
 *
 */
public class CreateActTest {  //DONE!

	@Test
	public void test() {
		Game g = new Game(10, 10);
		Player p = new Player("Orestis", g);
		g.addPlayer(p);
		System.out.println("EP = " + p.getEnergyPoints() + " AP = " + p.getActionPoints());
		CreationBuilding cb = new CreationBuilding(p, new Point(5,5));
		g.createBuilding(p, cb);
		CreateAction c = new CreateAction(p, cb, Soldier.class.getSimpleName());
		CreateAction c2 = new CreateAction(p, cb, Soldier.class.getSimpleName());
		if (c.exec()){
			System.out.println("EP = " + p.getEnergyPoints() + " AP = " + p.getActionPoints());
			System.out.println("Unit in (4, 5)  -----> " + g.getUnit(new Point(4, 5)));
			System.out.println("Unit in (4, 6)  -----> " + g.getUnit(new Point(4, 6)));
			System.out.println("Unit in (6, 5)  -----> " + g.getUnit(new Point(6, 5)));
			System.out.println("Unit in (6, 6)  -----> " + g.getUnit(new Point(6, 6)));
			System.out.println("Unit in (5, 6)  -----> " + g.getUnit(new Point(5, 6)));
			System.out.println("Unit in (5, 4)  -----> " + g.getUnit(new Point(5, 4)));
			System.out.printf("Executed! \n");			
		}
		else
			System.out.printf("Didn't execute! \n");
		if (c2.exec()){
			System.out.println("EP = " + p.getEnergyPoints() + " AP = " + p.getActionPoints());
			System.out.println("Unit in (4, 5)  -----> " + g.getUnit(new Point(4, 5)));
			System.out.println("Unit in (4, 6)  -----> " + g.getUnit(new Point(4, 6)));
			System.out.println("Unit in (6, 5)  -----> " + g.getUnit(new Point(6, 5)));
			System.out.println("Unit in (6, 6)  -----> " + g.getUnit(new Point(6, 6)));
			System.out.println("Unit in (5, 6)  -----> " + g.getUnit(new Point(5, 6)));
			System.out.println("Unit in (5, 4)  -----> " + g.getUnit(new Point(5, 4)));
			System.out.printf("Executed! \n");			
		}
		else
			System.out.printf("Didn't execute! \n");
	}

}
