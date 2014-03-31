package ptoma.hexoral.game.action;

import ptoma.hexoral.exception.AttackException;
import ptoma.hexoral.exception.GameException;
import ptoma.hexoral.game.Game;
import ptoma.hexoral.user.Player;

public abstract class Action implements Comparable<Action> {

	/**
	 * The player who created this move.
	 */
	protected Player actor;
	
	protected int APCost;

	protected int EPCost;
	
	protected int timeSlot;
	
	private Game game;
	
	

	
	/**
	 * @return	the action points required for the action.
	 */
	public int getAPCost()	{
		return APCost;
	}
	/**
	 * @return	the energy points required for the action.
	 */
	public int getEPCost()	{
		return EPCost;
	}
	
	public int compareTo(Action arg0) {
		return this.timeSlot - arg0.timeSlot;
	}

	public int getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(int timeSlot) {
		this.timeSlot = timeSlot;
	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	public Action(Player actor) {
		this.actor = actor;
		this.game = actor.getGame();
		this.APCost = 0;
		this.EPCost = 0;
	}

	/**
	 * 
	 * @return the player who created that action
	 */
	public Player getPlayer() {
		return this.actor;
	}
	abstract boolean validate();

	/**
	 * Executes the action defined.
	 * 
	 * @return the feasibility of the action
	 */
	abstract public boolean exec() throws GameException;
	
	abstract protected void print();
	
	abstract protected void update();

	

}