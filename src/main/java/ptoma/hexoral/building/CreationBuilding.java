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
	private String availableUnit;
	//private ArrayList<String> availableUnits;

	/**
	 * @param storedUnits
	 * @param unitCapacity
	 * @param defenceBonus
	 * @param BaseunitCapacity
	 */
	public CreationBuilding(Player owner, Point p) {
		super(owner, p);
		//List<String> availableUnits = new ArrayList<String>();
		//availableUnits.add(Soldier.class.getSimpleName());
		availableUnit = "Soldier";
	}

	public boolean isAvailable(String className) {
		//for (String s : availableUnits) {
			if (className.equals(availableUnit))
				return true;
		//}
		return false;
	}

	public boolean canAfford(String className) {
		Soldier temp = new Soldier(this.getOwner(), new Point(0, 0));
		int energyRequired = 0;
		int actionPointsRequired = 0;
		if (className.equals("Soldier")) {
			energyRequired = temp.getCreateEP();
			actionPointsRequired = temp.getCreateAP();
		}
		if ((isAvailable(className))
				&& ((energyRequired <= this.getOwner().getEnergyPoints()) && (actionPointsRequired <= this
						.getOwner().getActionPoints()))) {
			System.out.println("Setting EP and AP");
			this.getOwner().setEnergyPoints(this.getOwner().getEnergyPoints() - energyRequired);
			this.getOwner().setActionPoints(this.getOwner().getActionPoints() - actionPointsRequired);
			return true;
		}
		return false;
	}
}
