package ptoma.hexoral.game.action;

import ptoma.hexoral.user.Player;

public abstract class Action implements Comparable<Action> {

	/**
	 * The player who created this move.
	 */
	protected Player actor;

	protected int timeSlot;

	

	public int compareTo(Action arg0) {
		return this.timeSlot - arg0.timeSlot;
	}

	public int getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(int timeSlot) {
		this.timeSlot = timeSlot;
	}

	public Action(Player actor) {
		this.actor = actor;
	}

	abstract boolean validate();

	/**
	 * Executes the action defined.
	 * 
	 * @return the feasibility of the extion
	 */
	abstract public boolean exec();
	
	abstract protected void print();

}