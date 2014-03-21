package ptoma.hexoral.user;

public class Player extends User {

	private long energyPoints;
	
	private long actionPoints;
	
	public Player(String username) {
		super(username);
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
