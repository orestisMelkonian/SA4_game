
/*
 * author : Hanieh
*/

package ptoma.hexoral.building;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import ptoma.hexoral.IAttackable;
import ptoma.hexoral.units.Unit;
import ptoma.hexoral.user.Player;


public abstract class Building implements IAttackable {
	
	/**
	 * The units that the building contains.
	 */
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
	protected static int baseUnitCapacity = 2;
	
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
	public boolean buildingDestroyed;
	
	
		
	
	

	/*
	 *  Building Constructor
	 */
	
	public Building(Player owner, Point p) {
		this.storedUnits = new ArrayList<Unit>();
		this.owner = owner;
		this.position = p;
		this.defenseBonus = 50;
		this.buildingDestroyed = false;
	}
	

	/*
	 * getter and setter for fields
	 */
	
	public long getHealth(){
		return baseHealth;
	}
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
		
	public int getNumberOfStoredUnits()	{
		return (this.storedUnits.size());
	}
	
	public int getDefenseBonus() {
		return defenseBonus;
	}
	
	
	
	public void setDefenseBonus(int defenseBonus) {
		this.defenseBonus = defenseBonus;
	}


	/**
	 * 
	 * @param unit	The unit to be stored.
	 * @return	True if the unit was stored, false if the building is full.
	 */
	public boolean storeUnit(Unit e) {
		if(buildingDestroyed ){
			return false;
		
		}
		if (storedUnits.size() == baseUnitCapacity) {
			System.err.println("The building does not have place for the new unit");
			return true;
		}
		else{
			storedUnits.add(e);
			this.defenseBonus += 5;
			return true;			
		}
	}
	
	/**
	 * Extracts a unit from the building.
	 * @param 	position	the index of the unit in the List.
	 * @return	the extracted unit.
	 */
	public Unit extractUnit(int position)	{
		Unit ret = null;
		if (storedUnits.size() >= position)	{
			ret = storedUnits.get(position);
			storedUnits.remove(position);
		}
		return ret;
	}
	
	/**
	 * Return the attack force of a unit.
	 * @return int the attack force.
	 */
	public int attack(){
		return 0;
	}
	
	
	
	public void defend(int damage){
		if (storedUnits.size() > 0){
			//the building does not take damage, since there are units in it.
			for(int i = 0; i < storedUnits.size(); i++){
				storedUnits.get(i).defend(damage);
			}
		}else {
			//the building itself takes damage, since there is no units inside
			baseHealth = baseHealth - (damage);
			if(baseHealth < 0){
				buildingDestroyed = true; 
				
			}
		}
	}
	
	
	/**
	 * 
	 * @return	the list of units which are stored inside.
	 */
	
	public List<Unit> getUnitsInsideBuilding(){
		return this.storedUnits;
	}
	
}
		 
		
		
	
		
	
	
	
	

