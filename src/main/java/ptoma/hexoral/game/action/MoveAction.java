package ptoma.hexoral.game.action;

import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import ptoma.hexoral.exception.AttackException;
import ptoma.hexoral.exception.InvalidPointException;
import ptoma.hexoral.units.Unit;
import ptoma.hexoral.user.Player;

public class MoveAction extends Action {

	private Point where;
	private Unit unit;
	private Point fromWhere;

	public MoveAction(Player actor, Unit unit, Point where)
			throws InvalidPointException {
		super(actor);
		this.fromWhere = unit.getPosition();
		this.where = where;
		this.unit = unit;
		this.getGame().island.getHexagon(where.x, where.y);
	}

	@Override
	boolean validate() throws AttackException {
		return this.floodFillValidate();
	}

	@Override
	public boolean exec() throws AttackException {
		// TODO Auto-generated method stub
		if (this.validate()) {
			this.update();
			unit.move(this.where);
			this.print();
		} else {
			System.err.printf("NOT VALID MOVE\n");
			return false;
		}
		return true;
	}

	protected void print() {
		System.err.printf("Player %s commanded unit #%d to move to [%d,%d]\n",
				this.actor.getName(), this.unit.hashCode(), this.where.x,
				this.where.y);
	}

	@Override
	protected void update() {
		this.getGame().moveUnit(fromWhere, where);
	}

	private boolean floodFillValidate() throws AttackException {
		Map<Point, Integer> available = new HashMap<Point, Integer>();
		Queue<Point> adjacent = new LinkedList<Point>();
		// Initial cell
		available.put(unit.getPosition(), unit.getMoveRange());
		adjacent.add(unit.getPosition());
		Point check;
		while ((check = adjacent.poll()) != null) {
			int move = available.get(check);
			List<Point> neighbours = this.getGame().island.getNeighbours(check);
			for (Point p : neighbours) {
				// If we havent visited the cell and we can reach it, add it
				String type = null;
				try {
					type = this.getGame().island.getHexagon(p.x, p.y).getType();
				} catch (InvalidPointException e) {
					System.err.println("Flood fill went out of bounds");
					e.printStackTrace();
				} finally {
					if (available.get(p) == null && move > 0
							&& this.unit.isValidMove(type)) {
						available.put(p, move - 1);
						adjacent.add(p);
					} else if (this.unit.isValidMove(type) && move > 0
							&& available.get(p) < move - 1) {
						// If we have visited with a shorter path update the
						// move
						available.put(p, Math.max(available.get(p), move - 1));
					}
				}
			}
		}
		if (available.get(this.where) != null) {
			Unit destUnit = this.getGame().getUnit(this.where);
			if (destUnit == null) {
				return true;
			} else if (!this.unit.owner().equals(destUnit.owner())) {
				// If the destination unit is an oponent unit then attack
				throw new AttackException(new AttackAction(this.actor,
						this.unit, destUnit));
			}
			return true;
		} else {
			return false;
		}
	}

}
