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
	@Override
	public String toString()	{
		return (this.getClass().getSimpleName() + " in (" + getPosition().x + ", " + getPosition().y +
				")     Health:" + getHealth() + "/" + baseHealth + "    Owner: " + this.getOwner().getName());
	}
	/**
	 * The units that can be produced in the HQ.
	 */
	private String availableUnit;

	/**
	 * The action points required to create it.
	 */
	private static int createAP = 50;

	/**
	 * The energy points required to create it.
	 */
	private static int createEP = 50;

	/**
	 * @param storedUnits
	 * @param unitCapacity
	 * @param defenceBonus
	 * @param BaseunitCapacity
	 */
	public CreationBuilding(Player owner, Point p) {
		super(owner, p);
		availableUnit = "Soldier";
	}

	public boolean isAvailable(String className) {
		if (className.equals(availableUnit))
			return true;
		return false;
	}

	/**
	 * @return the action points required to create it.
	 */
	public int getCreateAP() {
		return createAP;
	}

	/**
	 * @return the energy points required to create it.
	 */
	public int getCreateEP() {
		return createEP;
	}
}
