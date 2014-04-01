/**
 * 
 */
package hexoral;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import ptoma.hexoral.game.Game;
import ptoma.hexoral.user.Player;

/**
 * @author usi
 *
 */
public class HQPlacement {

	@Test
	public void test() {
		Game g = new Game (10 , 10);
		Player p1 = new Player("Orestis", g);
		Player p2 = new Player("Stefanos", g);
		Point hq1 = g.putHQ(p1);
		Point hq2 = g.putHQ(p2);
		
		System.out.println(g.getBuilding(hq1).getClass().getSimpleName());
		System.out.println(g.getBuilding(hq2).getClass().getSimpleName());
	}

}
