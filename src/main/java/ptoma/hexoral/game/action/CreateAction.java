package ptoma.hexoral.game.action;

import java.awt.Point;
import java.util.List;

import ptoma.hexoral.units.Unit;
import ptoma.hexoral.units.Soldier;
import ptoma.hexoral.user.Player;
import ptoma.hexoral.building.CreationBuilding;
import ptoma.hexoral.exception.GameException;
import ptoma.hexoral.exception.InvalidPointException;

public class CreateAction extends Action {

	@Override
	public String toString() {
		String ret = super.toString();
		ret += " of type Create from building "
				+ this.creationBuilding.getPosition();
		return ret;
	}

	private CreationBuilding creationBuilding;
	private Unit unitCreated;
	private String className;
	private Point creationPoint;

	public CreateAction(Player actor, CreationBuilding creationBuilding,
			String className) throws GameException {
		super(actor);
		this.className = className;
		this.creationBuilding = creationBuilding;
		if (className.equals("Soldier")) {
			unitCreated = new Soldier(actor, new Point(0, 0));
			this.APCost = unitCreated.getCreateAP();
			this.EPCost = unitCreated.getCreateEP();
		}
	}

	@Override
	boolean validate() {
		List<Point> toCheck = this.getGame().island
				.getNeighbours(this.creationBuilding.getPosition());
		if (!(this.creationBuilding.isAvailable(this.className))) {
			System.out.println("This type of unit cannot be created here.");
			return false;
		}
		if ((actor.getActionPoints() < APCost)
				|| (actor.getEnergyPoints() < EPCost)) {
			System.out.println("Player " + this.actor.getName()
					+ " cannot afford to create " + this.className + ".");
			return false;
		}

		for (Point p : toCheck) {
			try {
				if ((this.actor.getGame().getUnit(p) == null)
						&& unitCreated.isValidMove((this.actor.getGame().island
								.getHexagon(p.x, p.y)).getType())) {
					this.creationPoint = p;
					return true;
				}
			} catch (InvalidPointException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("No room to create Unit from Building in ("
				+ this.creationBuilding.getPosition().x + ", "
				+ this.creationBuilding.getPosition().y + ").");
		return false;
	}

	@Override
	public boolean exec() {
		if (this.validate()) {
			unitCreated = new Soldier(actor, this.creationPoint);
			this.update();
			this.print();
			return true;
		}
		return false;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.printf("Player %s created Unit of type %s in (" + creationPoint.x + ", "+ creationPoint.y +")" + " and ID = #%d \n",
				actor.getName(), unitCreated.getClass().getSimpleName(),
				unitCreated.hashCode());
	}

	@Override
	protected void update() {
		this.getGame().createUnit(actor, this.unitCreated);
		actor.setActionPoints(actor.getActionPoints() - APCost);
		actor.setEnergyPoints(actor.getEnergyPoints() - EPCost);
	}
}
