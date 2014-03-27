package ptoma.hexoral.units;

import ptoma.hexoral.user.Player;

/**
 * The basic land soldier in the game.
 * 
 * @author steve
 * 
 */
public class Soldier extends Unit {

	/**
	 * Starting attack power.
	 */
	private static int baseAttack= 100;
	/**
	 * Starting health points.
	 */
	private static int baseHealth = 200;

	/**
	 * Starting defence points.
	 */
	private static int baseDefence = 200;

	/**
	 * Base move range.
	 */
	private static int baseMoveRange = 2;
	/**
	 * Soldier Unit Class.
	 * 
	 * @param owner
	 *            of the unit.
	 * @param x
	 *            coord of the unit.
	 * @param y
	 *            coord of the unit.
	 */
	public Soldier(final Player owner, final int x, final int y) {
		super(owner, x, y);
		this.health = Soldier.baseHealth;
		this.attackPower = Soldier.baseAttack;
		this.defencePower = Soldier.baseDefence;
		this.moveRange = Soldier.baseMoveRange;
	}

	/**
	 * Calculates the damage to be dane.
	 * 
	 * @return int the damage to be done.
	 */
	public final int attack() {
		return this.attackPower;
	}

	/**
	 * Calculates the damage received and updates health.
	 * 
	 * @param damage
	 *            The damage to be taken.
	 */
	public final void defend(final int damage) {
		this.health -= damage;

	}

	@Override
	public final int getMoveAP(final int moves) {
		return moves * this.moveAP;
	}

}
