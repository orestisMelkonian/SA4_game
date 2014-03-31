package ptoma.hexoral.exception;

import ptoma.hexoral.game.action.Action;

//TODO every action should throw a GameException for better readability 
public class GameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4067556363893966509L;
	
	Action what;
	
	public Action getAction() {
		return this.what;
	}

}
