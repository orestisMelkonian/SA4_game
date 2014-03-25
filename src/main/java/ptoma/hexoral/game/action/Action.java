package ptoma.hexoral.game.action;

import java.util.Comparator;

import ptoma.hexoral.user.Player;

public abstract class Action implements Comparator<Action> {

	/**
	 * The player who created this move.
	 */
	protected Player actor;

	protected int timeSlot;

	public int compare(Action o1, Action o2) {
		return o1.timeSlot - o2.timeSlot;
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

}