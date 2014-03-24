package ptoma.hexoral.game;

import ptoma.hexoral.IAttackable;

public class Combat {
	
	public IAttackable attacker;
	public IAttackable defender;
	
	public Combat(IAttackable attacker, IAttackable defender){
		this.attacker = attacker;
		this.defender = defender;
	}
	
	public IAttackable getAttacker(){
		return attacker;
	}
	
	public IAttackable getDefender(){
		return defender;
	}
	
}
