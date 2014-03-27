/*
 * @author : Hanieh
 */

package ptoma.hexoral.building;

import java.awt.Point;
import java.util.ArrayList;

import ptoma.hexoral.units.Unit;
import ptoma.hexoral.user.Player;



public class ResourceBuilding extends Building{
	
	
	public ResourceBuilding (Player owner, Point p) {
		super(owner, p);
		// TODO Auto-generated constructor stub
	}




	private int energyPerTurn = 500;
	


	
	
/*
 * getter for energyPerturn
 */
	

	public int getEnergyPerTurn() {
		return energyPerTurn;
	}




	public void setEnergyPerTurn(int energyPerTurn) {
		this.energyPerTurn = energyPerTurn;
	}




	public int attack() {
		// TODO Auto-generated method stub
		return 0;
	}




	public void defend(int damage) {
		// TODO Auto-generated method stub
		
	}

	
	
}