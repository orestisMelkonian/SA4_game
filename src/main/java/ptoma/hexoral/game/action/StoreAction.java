package ptoma.hexoral.game.action;

import java.awt.Point;

import ptoma.hexoral.building.Building;
import ptoma.hexoral.building.CreationBuilding;
import ptoma.hexoral.building.ResourceBuilding;
import ptoma.hexoral.exception.GameException;
import ptoma.hexoral.exception.InvalidPointException;
import ptoma.hexoral.units.Unit;
import ptoma.hexoral.user.Player;

public class StoreAction extends Action {
	@Override
	public String toString() {
		String ret = super.toString();
		ret += " of type Store in " + this.storeBuilding.getPosition();
		return ret;
	}

	private Building storeBuilding;
	private Unit unitToBeStored;

	public StoreAction(Player actor, Building storeB, Unit unit)
			throws GameException {
		super(actor);
		storeBuilding = storeB;
		unitToBeStored = unit;
	}

	@Override
	boolean validate() {
		if (storeBuilding.getUnitCapacity() == storeBuilding
				.getNumberOfStoredUnits()) {
			System.out.println("Building in (" + storeBuilding.getPosition().x
					+ ", " + storeBuilding.getPosition().y + ") "
					+ " cannot store another unit. ");
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
		System.out
				.printf("Unit of type %s was stored in a Building of type %s and ID = #%d in (%d, %d).\n",
						unitToBeStored.getClass().getSimpleName(),
						storeBuilding.getClass().getSimpleName(),
						storeBuilding.hashCode(),
						storeBuilding.getPosition().x,
						storeBuilding.getPosition().y);
	}

	@Override
	protected void update() {
		storeBuilding.storeUnit(unitToBeStored);
		actor.getGame().destroyUnit(unitToBeStored.getPosition());
		//System.out.println("unit is " + )
	}
}
