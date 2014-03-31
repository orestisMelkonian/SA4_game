package ptoma.hexoral;

import java.awt.Point;

public interface IAttackable {

	/**
	 * Return the attack force of a unit.
	 * @return int the attack force.
	 */
	public int attack();
	
	/**
	 * Calculates the damage and changes the appropriate values.
	 */
	public void defend(int damage);
	
	/**
	 * @return the health of the IAttackable.
	 */
	public long getHealth();
	
	/**
	 * @return the position of the IAttackable.
	 */
	public Point getPosition();
}
