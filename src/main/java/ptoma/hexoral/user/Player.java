package ptoma.hexoral.user;

import ptoma.hexoral.game.Game;
import ptoma.hexoral.game.scheduler.Scheduler;

public class Player extends User {

	/**
	 * The scheduled action of the user.
	 */
	private Scheduler schedule;

	/**
	 * 
	 */
	private Game gamePlaying;

	private long energyPoints;
	static private long BASE_ENERGY = 100;
	static private long BASE_ACTION = 100;
	private long actionPoints;

	public Player(String username, final Game gamePLaying) {
		super(username);
		this.gamePlaying = gamePLaying;
		this.actionPoints = Player.BASE_ACTION;
		this.energyPoints = Player.BASE_ENERGY;
		this.schedule = new Scheduler(this.gamePlaying, this);
	}

	/**
	 * @return the gamePlaying
	 */
	public Game getGame() {
		return gamePlaying;
	}

	/**
	 * @param gamePlaying
	 *            the gamePlaying to set
	 */
	public void setGame(Game gamePlaying) {
		this.gamePlaying = gamePlaying;
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

	public long addActionPoints(long actionPoints) {
		this.actionPoints += actionPoints;
		return this.actionPoints;
	}

	public long addEnergyPoints(long energyPoints) {
		this.energyPoints += energyPoints;
		return this.energyPoints;
	}

}
