package ptoma.hexoral.exception;

import ptoma.hexoral.game.action.Action;

public class CreateException extends GameException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3533647616518761850L;

	public CreateException(Action what) {
		this.what = what;
	}

	@Override
	public String getMessage() {
		return "Create from player " + what.getPlayer().getName() + " failed";
	}
	
	
}
