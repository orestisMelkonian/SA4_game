package ptoma.hexoral.game.scheduler;

import java.util.ArrayList;

import ptoma.hexoral.MyMain;
import ptoma.hexoral.exception.InvalidPointException;
import ptoma.hexoral.game.Game;
import ptoma.hexoral.game.action.Action;
import ptoma.hexoral.game.action.AttackAction;
import ptoma.hexoral.game.action.MoveAction;

public class Scheduler {

	
	/**
	 * The container of actions.
	 */
	protected ArrayList<Action> schedule;
	private Game game;
	
	public Scheduler(final Game game) {
		this.game = game;
		this.schedule = new ArrayList<Action>();
	}
	
	public void addAction(Action e) {
		e.setGame(this.game);
		this.schedule.add(e);
		if(e.getClass() == new AttackAction(null,null,null).getClass()){
			MyMain.addToScheduleList("Attack Action - ",  e);
			System.out.println("BUKAKAKA");
		} else
			try {
				if(e.getClass() == new MoveAction(null,null,null).getClass()){
					MyMain.addToScheduleList("Move Action - ",  e);
					System.out.println("BUKAKAKA   222222");
				}
			} catch (InvalidPointException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		
		
	}
	
	public Action peekAction(int index) {
		return this.schedule.get(index);
	}
	
	public void removeAction(int index) {
		this.schedule.remove(index);
	}
	
	public int size() {
		return this.schedule.size();
	}
	
	/**
	 * Organizes the actions to an array with the time slots set.
	 * @return the time slotted defined array.
	 */
	public ArrayList<Action> toArray() {
		int timeCurrent = 0;
		ArrayList<Action> ret = this.schedule; 
		for (Action action : ret) {
			timeCurrent += action.getTimeSlot();
			action.setTimeSlot(timeCurrent);
		}
		return ret;
	}
	
}
