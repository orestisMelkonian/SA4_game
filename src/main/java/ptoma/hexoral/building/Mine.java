
/*
 * @author : Hanieh
 */

package ptoma.hexoral.building;

import java.util.ArrayList;

import ptoma.hexoral.units.Unit;



public class Mine extends Building{
	
	
	private int currentConstStatus;
	
	public Mine(ArrayList<Unit> units, int unitCapacity, int defenceBonus,
			int BaseunitCapacity) {
		super(units, unitCapacity, defenceBonus, BaseunitCapacity);
		// TODO Auto-generated constructor stub
	}


	
	
/*
 * getter for currentConststatus
 */
	
	public int getCurrentConstStatus() {
		return currentConstStatus;
	}

	
	public void update(){
		// if the building is finished then inform the listener about the additional gain(action points and energy) 
		// otherwise finish the building or construct the building further.
	}

}
