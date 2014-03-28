package ptoma.hexoral.game.action;

<<<<<<< HEAD
import java.util.ArrayList;

import ptoma.hexoral.GameUISettings;
import ptoma.hexoral.MyMain;
=======
import ptoma.hexoral.exception.AttackException;
>>>>>>> 57a4b2a... Update on actions for attackexceptions
import ptoma.hexoral.game.Game;
import ptoma.hexoral.user.Player;

public abstract class Action implements Comparable<Action> {

	/**
	 * The player who created this move.
	 */
	protected Player actor;

	protected int timeSlot;
	
	private Game game;
	
	

	

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
	}

	abstract boolean validate();

	/**
	 * Executes the action defined.
	 * 
	 * @return the feasibility of the action
	 */
	abstract public boolean exec() throws AttackException;
	
	abstract protected void print();
	
	abstract protected void update();
	
	public void drawActions(){
				if(this.getClass() == new AttackAction(null,null,null).getClass()){
					MyMain.addToScheduleList("Attack Action - ",  this);
					System.out.println("BUKAKAKA");
				}else if(this.getClass() == new MoveAction(null,null,null).getClass()){
					MyMain.addToScheduleList("Move Action - ",  this);
					System.out.println("BUKAKAKA   222222");
				}
		
		
	}

}