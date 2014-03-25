package ptoma.hexoral.game.action;

import ptoma.hexoral.game.Combat;
import ptoma.hexoral.units.Unit;
import ptoma.hexoral.user.Player;

public class AttackAction extends Action {

	private Unit attacker;
	private Unit defender;

	public AttackAction(Player actor, Unit attacker, Unit defender) {
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
	public boolean exec() {
		if (this.validate()) {
			Combat battle = new Combat(attacker, defender);
			battle.combatInitialization(); // TODO fix this
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

}
