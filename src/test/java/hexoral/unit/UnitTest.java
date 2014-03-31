package hexoral.unit;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import ptoma.hexoral.IAttackable;
import ptoma.hexoral.units.Soldier;
import ptoma.hexoral.units.Unit;
import ptoma.hexoral.user.Player;

@SuppressWarnings("unused")
public class UnitTest {

	@Test
	public void test() {
		Player x = new Player("Steve",null);
		Unit u11 = new Soldier(x, new Point(1, 2));
		Unit u22 = new Soldier(x, new Point(1, 2));
		IAttackable u1 = u11;
		IAttackable u2 = u22;
		System.out.printf("Defender Unit Health : %d\n", u22.getHealth());
		u2.defend(u1.attack());
		System.out.printf("Defender Unit Health : %d\n", u22.getHealth());
		System.out.printf("This unit is %s\n",u11.getClass().getName());

	}

}
