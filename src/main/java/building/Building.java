
/*
 * author : Hanieh
*/

package building;
import java.util.ArrayList;

import ptoma.hexoral.units.Unit;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


import ptoma.hexoral.user.Player;

public class Building {
	
	ArrayList<Unit> unit = new ArrayList<Unit>();
	public int BaseunitCapacity;
	public int defenseBonus;
	

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
		if (BaseunitCapacity > 0) {
			System.out.println("The building does not have place for the new unit!!");
			return false;
		}
		else{
			BaseunitCapacity = BaseunitCapacity - 1;
			unit.add(e);
			
			
			return true;
			
		
	}
		
		
		
		
}
}
	

