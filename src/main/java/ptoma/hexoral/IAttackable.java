package ptoma.hexoral;

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
}
