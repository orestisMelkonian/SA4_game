package ptoma.hexoral;

public interface Unit {
	
	//Soldiers soldierCount;
	public Coords move(Coords moveCoord);
	public void attack(Coords attackCoord);
	public Coords follow(Coords followCoord);
	
	

}
