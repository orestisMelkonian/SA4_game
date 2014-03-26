package ptoma.hexoral.game.action;

import java.awt.Point;

import ptoma.hexoral.units.Unit;
import ptoma.hexoral.user.Player;

public class MoveAction extends Action {

	private Point where;
	private Unit unit;
	private Point fromWhere;

	public MoveAction(Player actor, Unit unit, Point where) {
		super(actor);
		this.fromWhere = unit.getPosition();
		this.where = where;
		this.unit = unit;
	}

	@Override
	boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean exec() {
		// TODO Auto-generated method stub
		if (this.validate()) {
			this.update();
			unit.move(this.where);
			this.print();
		} else {
			return false;
		}
		return true;
	}

	protected void print() {
		System.err.printf("Player %s commanded unit #%d to move to [%d,%d]\n",
				this.actor.getName(), this.unit.hashCode(),this.where.x, this.where.y);
	}

	@Override
	protected void update() {
		this.getGame().moveUnit(fromWhere, where);
	}

}
