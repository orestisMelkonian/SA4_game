package ptoma.hexoral.game.scheduler;

import java.util.ArrayList;
import java.util.Collections;

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
		this.setActionPointsLeft(actor.getActionPoints());
		this.setEnergyPointsLeft(actor.getEnergyPoints());
		
	}

	public void addAction(Action e) {
		this.setActionPointsLeft(this.getActionPointsLeft() - e.getAPCost());
		this.setEnergyPointsLeft(this.getEnergyPointsLeft() - e.getEPCost());
		this.schedule.add(e);

	}

	public Action peekAction(int index) {
		return this.schedule.get(index);
	}

	public void removeAction(int index) {
		Action e = this.schedule.remove(index);
		this.setActionPointsLeft(this.getActionPointsLeft() + e.getAPCost());
		this.setEnergyPointsLeft(this.getEnergyPointsLeft() + e.getEPCost());
	}

	public void removeAction(Action action) {
		if(this.schedule.remove(action)) {
			this.setActionPointsLeft(this.getActionPointsLeft() + action.getAPCost());
			this.setEnergyPointsLeft(this.getEnergyPointsLeft() + action.getEPCost());	
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
		Collections.swap(schedule, e1, e2);
	}

	public long getActionPointsLeft() {
		return actionPointsLeft;
	}

	public void setActionPointsLeft(long actionPointsLeft) {
		this.actionPointsLeft = actionPointsLeft;
	}

	public long getEnergyPointsLeft() {
		return energyPointsLeft;
	}

	public void setEnergyPointsLeft(long energyPointsLeft) {
		this.energyPointsLeft = energyPointsLeft;
	}

	public void reset() {
		this.setActionPointsLeft(actor.getActionPoints());
		this.setEnergyPointsLeft(actor.getEnergyPoints());
	}

}
