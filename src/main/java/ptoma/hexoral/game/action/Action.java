package ptoma.hexoral.game.action;

import ptoma.hexoral.user.Player;

public abstract class Action {

	protected Player actor;

	public Action(Player actor) {
		this.actor = actor;
	}
	
	abstract boolean validate();
	
	/**
	 * Executes the action defined.
	 * @return the feasibility of the extion
	 */
	abstract public boolean exec();

}