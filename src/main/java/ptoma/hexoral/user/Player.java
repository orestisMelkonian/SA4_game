package ptoma.hexoral.user;

import ptoma.hexoral.game.scheduler.Scheduler;

public class Player extends User {

	/**
	 * The scheduled action of the user.
	 */
	private Scheduler schedule;
	
	private long energyPoints;
	static private long BASE_ENERGY = 100;
	static private long BASE_ACTION = 100;
	private long actionPoints;
	
	public Player(String username) {
		super(username);
		this.actionPoints = Player.BASE_ACTION;
		this.energyPoints = Player.BASE_ENERGY;
		this.schedule = new Scheduler();
	}

	public Scheduler getSchedule() {
		return schedule;
	}

	public long getEnergyPoints() {
		return energyPoints;
	}

	public void setEnergyPoints(long energyPoints) {
		this.energyPoints = energyPoints;
	}

	public long getActionPoints() {
		return actionPoints;
	}

	public void setActionPoints(long actionPoints) {
		this.actionPoints = actionPoints;
	}
	

}
