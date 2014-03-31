package ptoma.hexoral.game;

import ptoma.hexoral.IAttackable;
//import ptoma.hexoral.IAttackable;
import ptoma.hexoral.units.Unit;

public class Combat {
	
	public Unit attacker;
	public IAttackable defender;
	
	
	/**
	 * Constructor for Combat.
	 */
	public Combat(Unit attacker, IAttackable defender){
		this.attacker = attacker;
		this.defender = defender;
	}
	
	
	/**
	 * Returns the unit who is attacking.
	 * 
	 * @return Unit the attacker.
	 */
	public Unit getAttacker(){
		return this.attacker;
	}
	
	/**
	 * Returns the unit who is defending.
	 * 
	 * @return Unit the defender.
	 */
	public IAttackable getDefender(){
		return this.defender;
	}
	
	public boolean exec(){
		this.defender.defend(this.attacker.attack());
		return true;
	}
}
