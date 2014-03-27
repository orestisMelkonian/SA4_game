/**
 * @author Orestis
 */
package ptoma.hexoral.building;

import java.awt.Point;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import ptoma.hexoral.units.Soldier;
import ptoma.hexoral.units.Unit;
import ptoma.hexoral.user.Player;


public class CreationBuilding extends Building {
	
	/**
	 * The units that can be produced in the HQ.
	 */
	private List<String> availableUnits;
	
	/**
	 * @param storedUnits
	 * @param unitCapacity
	 * @param defenceBonus
	 * @param BaseunitCapacity
	 */
	public CreationBuilding(Player owner, Point p) {
		super(owner, p);
		List<String> availableUnits = new ArrayList<String>();
		//availableUnits.add(Soldier.class.getSimpleName());
		availableUnits.add(Soldier.class.getCanonicalName());
	}

	public boolean isAvailable(String className)	{
		for (String s : availableUnits)	{
			if (s.equals(s))
				return true;
		}
		return false;
	}
}
