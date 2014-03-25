package ptoma.hexoral.game.action;

import java.awt.Point;

import ptoma.hexoral.units.Unit;
import ptoma.hexoral.user.Player;

public class MoveAction extends Action {

	private Point where;
	private Unit unit;

	public MoveAction(Player actor, Unit unit, Point where) {
		super(actor);
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
			unit.move(this.where);
			return true;
		} else {
			return false;
		}
	}

}
