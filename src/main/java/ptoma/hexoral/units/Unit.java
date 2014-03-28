package ptoma.hexoral.units;

import java.awt.Point;

import ptoma.hexoral.IAttackable;
import ptoma.hexoral.user.Player;
import ptoma.hexoral.user.User;

/**
 * Abstract class unit, base class for every other movable unit in the game.
 * 
 * @author steve
 * 
 */
public abstract class Unit implements IAttackable {

	/**
	 * Action points needed to move.
	 */
	protected int moveAP;

	/**
	 * Action points needed to create.
	 */
	protected int createAP;

	/**
	 * Energy points needed to create.
	 */
	protected int createEP;

	/**
	 * The health of the unit.
	 */
	protected int health;

	/**
	 * Attack force of a unit.
	 */
	protected int attackPower;

	/**
	 * Defence force of a unit.
	 */
	protected int defencePower;

	/**
	 * Move range of a unit.
	 */
	protected int moveRange;

	/**
	 * The position of the unit.
	 */
	private Point position;

	/**
	 * Refernce to the owner of the unit.
	 */
	private Player owner;

	/**
	 * Previous position of the unit.
	 */
	private Point previousPosition;
	/**
	 * Constructor for abstract class Unit.
	 * 
	 * @param owner
	 *            the owner of the unit.
	 * @param x
	 *            the position x of the unit.
	 * @param y
	 *            the position y of the unit.
	 */
	public Unit(final Player owner, final int x, final int y) {
		this.owner = owner;
		this.position = new Point(x, y);
		this.previousPosition = this.position;
		this.moveAP = 0;
		this.createAP = 0;
		this.createEP = 0;

	}

	/**
	 * Returns a reference of the owner of the unit.
	 * 
	 * @return Player the owner.
	 */
	public final Player getOwner() {
		return this.owner;
	}

	/**
	 * Returns the position of the unit.
	 * 
	 * @return Point the position.
	 */
	public final Point getPosition() {
		return this.position;
	}

	/**
	 * Gets the owner of a unit.
	 * 
	 * @return User the owner of the unit.
	 */
	public final User owner() {
		return this.owner;
	}

	/**
	 * Returns the health of a unit.
	 * 
	 * @return int health of the unit
	 */
	public final int getHealth() {
		return this.health;
	}

	/**
	 * Moves the unit to Point w.
	 * 
	 * @param w
	 *            the new position of the unit.
	 */
	public final void move(final Point w) {
		this.previousPosition = this.position;
		this.position = w;
	}

	/**
	 * Returns the Action Points required to make moves moves.
	 * 
	 * @param moves
	 *            the moves to be made.
	 * @return int Action Points required.
	 */
	public abstract int getMoveAP(int moves);

	/**
	 * Get the Action Points Required to create this type of unit.
	 * 
	 * @return int the Action Points required for creation.
	 */
	public final int getCreateAP() {
		return this.createAP;
	}

	/**
	 * Get the Energy Points Required to create this type of unit.
	 * 
	 * @return int the Energy Points required
	 */
	public final int getCreateEP() {
		return this.createEP;
	}

	/**
	 * @return the previousPosition.
	 */
	public Point getPreviousPosition() {
		return previousPosition;
	}
	
	/**
	 * 
	 * @return the move range of the unit.
	 */
	public int getMoveRange() {
		return this.moveRange;
	}
	
	/**
	 * 
	 * @param type the type to be checked
	 * @return boolean if unit can cross this type of land
	 */
	public boolean isValidMove(String type) {
		return true;
	}

}
