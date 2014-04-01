package ptoma.hexoral.game.action;

import ptoma.hexoral.IAttackable;
import ptoma.hexoral.game.Combat;
import ptoma.hexoral.units.Unit;
import ptoma.hexoral.user.Player;

public class AttackAction extends Action {

	@Override
	public String toString() {
		String ret = super.toString(); 
		ret += " of type Attack from unit in" + this.attacker.getPosition() + " to " + this.defender.getPosition();
		return ret;
	}

	private Unit attacker;
	private IAttackable defender;

	public AttackAction(Player actor, Unit attacker, IAttackable defender) {
		super(actor);
		this.attacker = attacker;
		this.defender = defender;
	}

	@Override
	boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean exec()	{
		if (this.validate()) {
			Combat battle = new Combat(attacker, defender);
			battle.exec(); // TODO fix this
			this.update();
			this.print();
		} else {
			return false;
		}
		return true;
	}

	protected void print() {
		System.err.printf("Player %s commanded unit #%d to attack unit #%d\n",
				this.actor.getName(), this.attacker.hashCode(),
				this.defender.hashCode());
	}

	@Override
	protected void update() {
		if(this.defender.getHealth() <= 0) {
			if (this.defender.getClass().getName().contains("building"))
				this.getGame().destroyBuilding(this.defender.getPosition());
			else
				this.getGame().destroyUnit(this.defender.getPosition());
		}
		if(this.attacker.getHealth() <= 0) {
			this.getGame().destroyUnit(this.attacker.getPosition());
		}
	}
	
}
