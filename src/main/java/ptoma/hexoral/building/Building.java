
/*
 * author : Hanieh
*/

package ptoma.hexoral.building;
import java.util.ArrayList;

import ptoma.hexoral.IAttackable;
import ptoma.hexoral.units.Unit;


public class Building implements IAttackable {
	
	ArrayList<Unit> units = new ArrayList<Unit>();
	private int BaseunitCapacity;
	private int defenseBonus;
	private int health;
	private boolean buildingDestroyed;
		
	
	

	/*
	 *  Building Constructor
	 */
	
	public Building(ArrayList<Unit> units, int unitCapacity,int defenceBonus, int BaseunitCapacity ) {
		
		this.units = units;
		this.BaseunitCapacity = BaseunitCapacity;
		this.defenseBonus = defenceBonus;
		
	}
	

/*
 * getter and setter for fields
 */
	
	public int getUnitsCapacity() {
		return BaseunitCapacity;
	}
	
	public void setUnitsCapacity(int unitCapacity) {
		this.BaseunitCapacity = unitCapacity;
	}
	
	public void setDefenseBonus(int defenseBonus) {
		this.defenseBonus = defenseBonus;
	}
	
	public int getDefenseBonus() {
		return defenseBonus;
	}
	
	
/*
 * it checks if the building has the capacity for the new unit inside.
 */
	public boolean enteredUnits(Unit e) {
		if(buildingDestroyed ){
			return false;
		
		}
		if (units.size() >  BaseunitCapacity) {
			System.err.println("The building does not have place for the new unit!!");
			return false;
		}
		else{ 
//			 I should check the unit type later
//			if (e.getType() == "SOLDIER" || ..........){
//				units.add(e);
//				e.increaseDefence(defenseBonus);
//				return true;
//			}
//			System.out.println("Unit was refused accsess,!");
			return false;
//			}
			
		}

	}
	

/*
 * It handles enemy attack.
 * if the building is under attack it takes damage if it is empty otherwise it relays the damage to units.   
 */
	public int attack(){
		return 0;
		//TODO Maybe turrets in the  building
		
	}
	
	
/*
 *  it damages building and check if it is collapsed.
 */
	
	public void defend(int damage){
		// *** if the building has some armor, this might reduced damage.
		// int realDamage = damage - armor;
		// if(realDamage >= 0){
		// 		health = health - (damage - armor);
		//	}
		//TODO take into account armour and units inside the building
		health = health - (damage);
		if (health < 0){
			buildingDestroyed = true;
		}
	}
	
	
/*
 * it distributes damage over unit. 
 */
	
	private void damageUnits(int damage){
		// unit should decrease their health 
		for(Unit u : units){
			//u.attack(damage);     *************** need to have function that tells the unit to take damage *************
			
		}
	}
	
	
/*
 *  allows units to leave the building
 */
	
	public void leaveUnits(Unit u){
		// if unit is inside and reduced the units armor.
	}



	
}
	
