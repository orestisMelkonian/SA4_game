package ptoma.hexoral.units;

import java.awt.Point;

import ptoma.hexoral.IAttackable;
import ptoma.hexoral.user.Player;
import ptoma.hexoral.user.User;

public abstract class Unit implements IAttackable {

	/**
	 * Refernce to the owner of the unit.
	 */
	private Player owner;
	
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
	 * The position of the unit.
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
	
	/**
	 * Move range of a unit.
	 */
	protected int moveRane;

	public Unit(Player owner, int x, int y) {
		this.owner = owner;
		this.position = new Point(x, y);
		this.moveAP = 0;
		this.createAP = 0;
		this.createEP = 0;

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

	public User owner() {
		return this.owner;
	}

	public int getHealth() {
		return health;
	}
	
	public void move(Point w) {
		this.position = w;
	}
	
	public int getMoveAP(int moves){
		return this.moveAP * moves;
	}
	
	public int getCreateAP(){
		return this.createAP;
	}
	
	public int getCreateEP(){
		return this.createEP;
	}

}
