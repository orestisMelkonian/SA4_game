package hexoral.user;



import static org.junit.Assert.*;

import org.junit.Test;

import ptoma.hexoral.user.*;

public class User {

	@Test
	public void test() {
		Player p1 = new Player("Player1");
		ptoma.hexoral.user.User p2 = new ptoma.hexoral.user.User("Player1");
		Player p3 = new Player("AnotherPLayer");
		assertEquals(p1, p2);
		assertNotSame(p1, p3);
	}

}
