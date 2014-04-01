package ptoma.hexoral.game.action;

import java.awt.Point;

import ptoma.hexoral.building.Building;
import ptoma.hexoral.building.CreationBuilding;
import ptoma.hexoral.building.ResourceBuilding;
import ptoma.hexoral.exception.GameException;
import ptoma.hexoral.exception.InvalidPointException;
import ptoma.hexoral.user.Player;

public class BuildAction extends Action {
	@Override
	public String toString() {
		String ret = super.toString();
		ret += " of type Build " + this.buildingToBeBuilt.getPosition();
		return ret;
	}

	private String className;
	private Building buildingToBeBuilt;

	public BuildAction(Player actor, String className, Point creationPoint)
			throws GameException {
		super(actor);
		this.className = className;
		if (className.equals("CreationBuilding")) {
			CreationBuilding cb = new CreationBuilding(actor, creationPoint);
			this.APCost = cb.getCreateAP();
			this.EPCost = cb.getCreateEP();
			buildingToBeBuilt = cb;
		} else if (className.equals("ResourceBuilding")) {
			ResourceBuilding rb = new ResourceBuilding(actor, creationPoint);
			this.APCost = rb.getCreateAP();
			this.EPCost = rb.getCreateEP();
			buildingToBeBuilt = rb;
		}
	}

	@Override
	boolean validate() {
		try {
			if (actor.getGame().getBuilding(buildingToBeBuilt.getPosition()) != null) {
				System.out.println("Player " + this.actor.getName()
						+ " cannot build a " + this.className
						+ " here.[Already a building here]");
				return false;
			}
			if (!(actor.getGame().island.getType(
					buildingToBeBuilt.getPosition().x,
					buildingToBeBuilt.getPosition().y).equals("LAND"))) {
				System.out.println("Player " + this.actor.getName()
						+ " cannot build a " + this.className
						+ " here.[No land cell here]");
				return false;
			}
		} catch (InvalidPointException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!((actor.getActionPoints() >= APCost) && (actor.getEnergyPoints() >= EPCost))) {
			System.out.println("Player " + this.actor.getName()
					+ " cannot afford to create " + this.className + ".");
			return false;
		}
		return true;
	}

	@Override
	public boolean exec() {
		if (this.validate()) {
			this.update();
			this.print();
			return true;
		}
		return false;
	}

	@Override
	public void print() {
		System.out.printf(
				"Player %s created Building of type %s in (" + buildingToBeBuilt.getPosition().x + ", "+ buildingToBeBuilt.getPosition().y +")" + "and ID = #%d \n",
				actor.getName(), buildingToBeBuilt.getClass().getSimpleName(),
				buildingToBeBuilt.hashCode());
	}

	@Override
	protected void update() {
		this.getGame().createBuilding(actor, buildingToBeBuilt);
		actor.setActionPoints(actor.getActionPoints() - APCost);
		actor.setEnergyPoints(actor.getEnergyPoints() - EPCost);
	}
}
