package hexoral.user;



import static org.junit.Assert.*;

import org.junit.Test;

import ptoma.hexoral.exception.GameException;
import ptoma.hexoral.game.Game;
import ptoma.hexoral.user.*;

public class User {

	@Test
	public void test() {
		Player p1 = new Player("Player1",null);
		ptoma.hexoral.user.User p2 = new ptoma.hexoral.user.User("Player1");
		Player p3 = new Player("AnotherPLayer",null);
		assertEquals(p1, p2);
		assertNotSame(p1, p3);
	}
	
	@Test
	public void resources() throws GameException {
		Game game = new Game(10,10);
		Player p1 = new Player("Player1",game);
		game.addPlayer(p1);
		p1.addActionPoints(100);
		p1.addEnergyPoints(100);
		game.executeTurn();
		game.executeTurn();
		
	}

}
