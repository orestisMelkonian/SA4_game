package ptoma.hexoral.game.scheduler;

import java.util.ArrayList;

import ptoma.hexoral.game.Game;
import ptoma.hexoral.game.action.Action;
import ptoma.hexoral.user.Player;

public class Scheduler {

	/**
	 * The container of actions.
	 */
	protected ArrayList<Action> schedule;
	private Game game;
	
	private long actionPointsLeft;
	private long energyPointsLeft;
	private Player actor;

	public Scheduler(final Game game, final Player actor) {
		this.game = game;
		this.actor = actor;
		this.schedule = new ArrayList<Action>();
		this.actionPointsLeft = actor.getActionPoints();
		this.energyPointsLeft = actor.getEnergyPoints();
		
	}

	public void addAction(Action e) {
		this.actionPointsLeft -= e.getAPCost();
		this.energyPointsLeft -= e.getEPCost();
		this.schedule.add(e);

	}

	public Action peekAction(int index) {
		return this.schedule.get(index);
	}

	public void removeAction(int index) {
		Action e = this.schedule.remove(index);
		this.actionPointsLeft += e.getAPCost();
		this.energyPointsLeft += e.getEPCost();
	}

	public void removeAction(Action action) {
		if(this.schedule.remove(action)) {
			this.actionPointsLeft += action.getAPCost();
			this.energyPointsLeft += action.getEPCost();	
		}
	}
	
	public void clear() {
		this.schedule.clear();
	}
	
	public int size() {
		return this.schedule.size();
	}

	/**
	 * Organizes the actions to an array with the time slots set.
	 * 
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

	/**
	 * swap actions .
	 */

	public void swapAction(int e1, int e2) {
		Action a1 = this.schedule.get(e1);
		this.schedule.add(e1, this.schedule.get(e2));
		this.schedule.add(e2, a1);
	}

}
