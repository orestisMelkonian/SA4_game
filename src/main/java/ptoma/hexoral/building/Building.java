
/*
 * author : Orestis
*/

package ptoma.hexoral.building;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import ptoma.hexoral.IAttackable;
import ptoma.hexoral.units.Unit;
import ptoma.hexoral.user.Player;


public abstract class Building implements IAttackable {
	
	List<Unit> storedUnits = new ArrayList<Unit>();
	/**
	 * Reference to the owner of the HQ.
	 */
	private Player owner;
	/**
	 * The position of the HQ.
	 */
	private Point position;
	
	/**
	 * The maximum number of units that can be stored.
	 */
	private static int baseUnitCapacity = 10;
	
	/**
	 * Extra defense provided from stored units.
	 */
	private int defenseBonus;
	
	/**
	 * The health of the HQ.
	 */
	protected static long baseHealth = 1000;
	
	/**
	 * true:destroyed 	false:not destroyed
	 */
	private boolean buildingDestroyed;
		
	
	

	/*
	 *  Building Constructor
	 */
	
	public Building(Player owner, Point p) {
		this.storedUnits = null;
		this.owner = owner;
		this.position = p;
		this.defenseBonus = 0;
		this.buildingDestroyed = false;
	}
	

	/*
	 * getter and setter for fields
	 */
	/**
	 * Returns a reference of the owner of the unit.
	 * 
	 * @return Player the owner.
	 */
	public final Player getOwner() {
		return owner;
	}
	
	/**
	 *	Returns the position of the building on the map.
	 * @return
	 */
	public Point getPosition() {
		return position;
	}

	public int getUnitCapacity() {
		return baseUnitCapacity;
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
		if (storedUnits.size() >  baseUnitCapacity) {
			System.err.println("The building does not have place for the new unit!!");
			return false;
		}
		else{
				storedUnits.add(e);
				return true;
				
		}
		
	}
	
	
	public int attack(){
		return 0;
	}
	
	
	
	public void defend(int damage){
		baseHealth = baseHealth - (damage);
		if (baseHealth < 0){
			buildingDestroyed = true;
	}
	
}
}
		 
		
		
	
		
	
	
	
	

