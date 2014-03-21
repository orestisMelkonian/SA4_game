
/*
 * author : Hanieh
*/

package building;
import java.util.ArrayList;

import ptoma.hexoral.units.Unit;

public class Building {
	
	ArrayList<Unit> unit = new ArrayList<Unit>();
	public int BaseunitCapacity;
	public int defenseBonus;
	

	/*
	 *  Building Constructor
	 */
	
	public Building(ArrayList<Unit> unit, int unitCapacity,int defenceBonus ) {
		
		this.unit = unit;
		this.BaseunitCapacity =unitCapacity;
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
 * to increase the capacity of unit 
 */
	public void DecreaseBaseCapacity(int unitCapacity) {
		
		
		
		
		
		
		
	}
	
}
