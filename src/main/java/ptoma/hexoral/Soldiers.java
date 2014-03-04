package ptoma.hexoral;

/**
 * Use of Soldiers as currency
 * @author Lokesh
 *
 */

public class Soldiers {
	
	private int amountOfSoldiers;
	
	
	/**
	 * Constructor for Soldiers which gives an initial amount of soldier to each player.
	 * @param amountOfSoldiers is the initial amount given to each player.
	 *
	 */
	public Soldiers(int amountOfSoldiers){
		this.amountOfSoldiers = amountOfSoldiers;
	}
	
	
	/**
	 * Getter for amount of Soldiers.
	 * @return amountOfSoldiers.
	 */
	public int getAmountOfSoldiers(){
		return amountOfSoldiers;
	}
	
	
	/**
	 * Setter for amount of Soldiers.
	 * @param amount by which the number of soldiers should increase.
	 */
	public void setAmountOfSoldiers(int amount){
		amountOfSoldiers = amount;
	}
	
	
	/**
	 * Method to increase the number of Soldiers.
	 * @param increase by which the number of soldiers should increase.
	 */
	public int increaseOfSoldiers(int increase){
		amountOfSoldiers = amountOfSoldiers + increase;
		return amountOfSoldiers;
	}
	
	
	/**
	 * Method to decrease the number of Soldiers.
	 * @param decrease by which the number of soldiers should decrease.
	 */
	public int decreaseOfSoldiers(int decrease){
		amountOfSoldiers = amountOfSoldiers - decrease;
		return amountOfSoldiers;
	}
	

}
