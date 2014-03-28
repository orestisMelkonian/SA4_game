package ptoma.hexoral.exception;

import java.awt.Point;

import ptoma.hexoral.user.Player;

public class InvalidPointException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8622656278750843999L;
	
	private Point where;
	
	public InvalidPointException(Point where) {
		this.where = where;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return "Invalid point " + "[" + this.where + "]";
	}

	

}
