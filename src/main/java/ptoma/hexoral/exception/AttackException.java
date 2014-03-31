package ptoma.hexoral.exception;

import ptoma.hexoral.game.action.Action;

public class AttackException extends GameException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2108027064812865974L;
	
	private Action what;

	public AttackException(Action attack) {
		this.what = attack;
	}
	
	@Override
	public String getMessage() {
		return "Action from " + what.getPlayer().getName() + " could not be created";
	}

	/**
	 * The attack that was triggered.
	 * @return the Action
	 */
	public Action getAction() {
		return this.what;
	}
	
	
}
