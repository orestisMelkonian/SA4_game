package ptoma.hexoral.units;

import java.awt.Point;


public interface Unit {
	//boolean isDestroyed;
	//Soldiers soldierCount;
	public Point move(Point moveCoord);
	public void attack(Point attackCoord);
	public Point follow(Point followCoord);
	public boolean destroyUnit(Soldiers soldier);
	

}
