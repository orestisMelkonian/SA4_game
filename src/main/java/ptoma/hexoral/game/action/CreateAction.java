package ptoma.hexoral.game.action;

import java.awt.Point;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import ptoma.hexoral.units.Unit;
import ptoma.hexoral.units.Soldier;
import ptoma.hexoral.user.Player;
import ptoma.hexoral.building.CreationBuilding;

public class CreateAction extends Action {

	private CreationBuilding creationBuilding;
	private Unit unitCreated;
	private String className;
	private Point creationPoint;

	public CreateAction(Player actor, CreationBuilding creationBuilding, String className) {
		super(actor);
		this.className = className;
		this.creationBuilding = creationBuilding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ptoma.hexoral.game.action.Action#validate()
	 */
	@Override
	boolean validate() {
		List<Point> toCheck = this.getGame().island.getNeighbours(this.creationBuilding.getPosition());
		//Point p = new Point(this.creationBuilding.getPosition().x + 1, this.creationBuilding.getPosition().y + 1);
		if (!(this.creationBuilding.isAvailable(this.className)))
			return false;
		if (!(this.creationBuilding.canAfford(this.className)))
			return false;
		for(Point p : toCheck) {
			if (this.actor.getGame().getUnit(p) == null)	{
				this.creationPoint = p;
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ptoma.hexoral.game.action.Action#exec()
	 */
	@Override
	public boolean exec() {
		if (this.validate())	{
			this.unitCreated = new Soldier(actor, this.creationPoint);
			System.out.println("creation Point is " + this.creationPoint.x + ", " + this.creationPoint.y);
			this.update();
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ptoma.hexoral.game.action.Action#print()
	 */
	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.printf("Player %s created Unit of type %s and ID = #%d \n",
				actor.getName(), unitCreated.getClass().getSimpleName(),
				unitCreated.hashCode());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ptoma.hexoral.game.action.Action#update()
	 */
	@Override
	protected void update() {
		this.getGame().createUnit(actor, this.unitCreated);
	}
}
