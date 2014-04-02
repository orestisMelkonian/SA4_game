/*
 * @author : Hanieh
 */

package ptoma.hexoral.building;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import ptoma.hexoral.exception.InvalidPointException;
import ptoma.hexoral.units.Unit;
import ptoma.hexoral.user.Player;

public class ResourceBuilding extends Building {
	@Override
	public String toString()	{
		return (this.getClass().getSimpleName() + " in (" + getPosition().x + ", " + getPosition().y +
				")     Health:" + getHealth() + "/" + baseHealth + "    Owner: " + this.getOwner().getName());
	}
	/**
	 * The action points required to create it.
	 */
	private static int createAP = 50;

	/**
	 * The energy points required to create it.
	 */
	private static int createEP = 20;

	/**
	 * The amount of energy it produces every turn.
	 */
	private int energyPerTurn;

	/**
	 * The base amount of energy it gets from each neighboring resource cell.
	 */
	static private int baseEnergyPerTurn = 25;

	public ResourceBuilding(Player owner, Point p) {
		super(owner, p);
		baseUnitCapacity = 1;
		energyPerTurn = 25;
		try {
			if (this.getOwner().getGame().island.getHexagon(p.x, p.y)
					.isResource()) {
				energyPerTurn += baseEnergyPerTurn;
			}
		}  catch (InvalidPointException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Point> toCheck = this.getOwner().getGame().island.getNeighbours(p);
		for (Point i : toCheck) {
			try {
				if (this.getOwner().getGame().island.getType(i.x, i.y).equals(
						"RESOURCE"))
					energyPerTurn += baseEnergyPerTurn;
			} catch (InvalidPointException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Gets the energyPerTurn.
	 * 
	 */

	public int getEnergyPerTurn() {
		// if the building is still alive,it returns the value of energy it
		// produces, otherwise it will return 0
		if (buildingDestroyed)
			return 0;
		return energyPerTurn;
	}

	/**
	 * sets the energyPerTurn.
	 * 
	 */
	public void setEnergyPerTurn(int energyPerTurn) {
		this.energyPerTurn = energyPerTurn;
	}

	/**
	 * Return the attack force of a unit.
	 * 
	 * @return the attack force.
	 */

	public int attack() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void defend(int damage) {
		// TODO Auto-generated method stub

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