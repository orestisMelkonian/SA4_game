package ptoma.hexoral.units;

import java.awt.Point;

import ptoma.hexoral.user.Player;
import ptoma.hexoral.user.User;


public class Unit {
	
	private Player p;
	
	public Unit(Player p){
		this.p = p;
		
	}
	//boolean isDestroyed;
	//Soldiers soldierCount;
//	public Point move(Point moveCoord);
//	public void attack(Point attackCoord);
//	public Point follow(Point followCoord);
//	public boolean destroyUnit(Soldiers soldier);
	public User owner(){
		return p;
	}
	
	

}
