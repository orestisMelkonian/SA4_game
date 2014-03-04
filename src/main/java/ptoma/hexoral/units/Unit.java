package ptoma.hexoral.units;

import ptoma.hexoral.map.Coords;

public interface Unit {
	//boolean isDestroyed;
	//Soldiers soldierCount;
	public Coords move(Coords moveCoord);
	public void attack(Coords attackCoord);
	public Coords follow(Coords followCoord);
	public boolean destroyUnit(Soldiers soldier);
	

}
