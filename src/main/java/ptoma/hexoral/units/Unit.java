package ptoma.hexoral.units;

import java.awt.Point;

import ptoma.hexoral.IAttackable;
import ptoma.hexoral.user.Player;
import ptoma.hexoral.user.User;

public abstract class Unit implements IAttackable {

	/**
	 * Refernce to the owner of the unit
	 */
	private Player owner;

	/**
	 * The position of the unit
	 */
	private Point position;
	
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

	public Unit(Player owner, int x, int y) {
		this.owner = owner;
		this.position = new Point(x, y);

	}

	/**
	 * Returns a reference of the owner of the unit.
	 * 
	 * @return Player the owner.
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * Returns the position of the unit.
	 * 
	 * @return Point the position.
	 */
	public Point getPosition() {
		return position;
	}


	// boolean isDestroyed;
	// Soldiers soldierCount;
	// public Point move(Point moveCoord);
	// public void attack(Point attackCoord);
	// public Point follow(Point followCoord);
	// public boolean destroyUnit(Soldiers soldier);
	public User owner() {
		return this.owner;
	}

}
