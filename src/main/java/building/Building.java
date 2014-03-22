
/*
 * author : Hanieh
*/

package building;
import java.util.ArrayList;

import ptoma.hexoral.units.Unit;


public class Building {
	
	ArrayList<Unit> unit = new ArrayList<Unit>();
	private int BaseunitCapacity;
	private int defenseBonus;
	private int health;
	private boolean buildingDestroyed;
		
	
	

	/*
	 *  Building Constructor
	 */
	
	public Building(ArrayList<Unit> unit, int unitCapacity,int defenceBonus, int BaseunitCapacity ) {
		
		this.unit = unit;
		this.BaseunitCapacity = BaseunitCapacity;
		this.defenseBonus = defenceBonus;
		
	}
	

/*
 * getter and setter for fields
 */
	
	public int getUnitCapacity() {
		return BaseunitCapacity;
	}
	
	public void setUnitCapacity(int unitCapacity) {
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
	public boolean EnterUnit(Unit e) {
		if(buildingDestroyed ){
			return false;
		
		}
		if (unit.size() >  BaseunitCapacity) {
			System.out.println("The building does not have place for the new unit!!");
			return false;
		}
		else{ 
//			 I should check the unit type later
//			if (e.getType() == "SOLDIER" || ..........){
//				unit.add(e);
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
	
	public void Attack(int damage){
		
		if (unit.size() == 0 ){
			Takedamage(damage);
		}
		else{
			damageUnits(damage);
		}
	}
	
	
/*
 *  it damages building and check if it is collapsed.
 */
	
	private void Takedamage(int damage){
		// *** if the building has some armor, this might reduced damage.
		// int realDamage = damage - armor;
		// if(realDamage >= 0){
		// 		health = health - (damage - armor);
		//	}
		
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
		for(Unit u : unit){
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
	
