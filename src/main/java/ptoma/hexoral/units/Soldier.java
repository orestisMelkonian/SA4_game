package ptoma.hexoral.units;

import java.util.Arrays;

import ptoma.hexoral.user.Player;

import java.awt.Point;
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
	 * The valid types a soldier can walk on.
	 */
	private static String[] validTypes = {"LAND"};
	
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
	public Soldier(final Player owner, Point p) {
		super(owner, p);
		this.health = Soldier.baseHealth;
		this.attackPower = Soldier.baseAttack;
		this.defencePower = Soldier.baseDefence;
		this.moveRange = Soldier.baseMoveRange;
	}	

	@Override
	public final int getMoveAP(final int moves) {
		return moves * this.moveAP;
	}

	/* (non-Javadoc)
	 * @see ptoma.hexoral.units.Unit#isValidMove(java.lang.String)
	 */
	@Override
	public boolean isValidMove(String type) {
		return Arrays.asList(this.validTypes).contains(type);
	}
	
	   

}
