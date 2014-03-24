package ptoma.hexoral.game;

//import ptoma.hexoral.IAttackable;
import ptoma.hexoral.units.Unit;

public class Combat {
	
	public Unit attacker;
	public Unit defender;
	
	
	/**
	 * Constructor for Combat.
	 */
	public Combat(Unit attacker, Unit defender){
		this.attacker = attacker;
		this.defender = defender;
	}
	
	
	/**
	 * Returns the unit who is attacking.
	 * 
	 * @return Unit the attacker.
	 */
	public Unit getAttacker(){
		return attacker;
	}
	
	/**
	 * Returns the unit who is defending.
	 * 
	 * @return Unit the defender.
	 */
	public Unit getDefender(){
		return defender;
	}
	
	public boolean combatInitialization(){
		if(attacker.getPosition() == defender.getPosition()){
			System.out.println("Battle initialized");
			return true;
		}
		else{
			return false;
		}
		
	}
	
}
