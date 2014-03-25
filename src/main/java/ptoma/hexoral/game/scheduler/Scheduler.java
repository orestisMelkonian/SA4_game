package ptoma.hexoral.game.scheduler;

import java.util.ArrayList;
import ptoma.hexoral.game.action.Action;

public class Scheduler {

	
	/**
	 * The container of actions.
	 */
	protected ArrayList<Action> schedule;
	
	public Scheduler() {
		this.schedule = new ArrayList<Action>();
	}
	
	public void addAction(Action e) {
		this.schedule.add(e);
	}
	
	public Action peekAction(int index) {
		return this.schedule.get(index);
	}
	
	public void removeAction(int index) {
		this.schedule.remove(index);
	}
	
	/**
	 * Organizes the actions to an array with the time slots set.
	 * @return the time slotted defined array.
	 */
	public Action[] toArray() {
		int timeCurrent = 0;
		Action[] ret = (Action[]) this.schedule.toArray();
		for (Action action : ret) {
			timeCurrent += action.getTimeSlot();
			action.setTimeSlot(timeCurrent);
		}
		return ret;
	}
	
}
