package Building;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.List;

import org.junit.Test;

import ptoma.hexoral.building.ResourceBuilding;
import ptoma.hexoral.game.Game;
import ptoma.hexoral.units.Soldier;
import ptoma.hexoral.units.Unit;
import ptoma.hexoral.user.Player;


public class ResourceBuildingTest {
	
	static Game world;

	
	@Test
	public void test() {
		Player p1 = new Player("Hanieh", world);
		Player p2 = new Player("lokesh",world);
		world.addPlayer(p1);
		world.addPlayer(p2);
		Unit u1 = new Soldier(p1, new Point(0,0));
		Unit u2 = new Soldier(p1, new Point(0,1));
		world.createUnit(p1, u1);
		world.createUnit(p1, u2);
		//world.createUnit(p2, u3);
		//world.createUnit(p2, u4);

		ResourceBuilding b2 = new ResourceBuilding(p1, new Point(1,1) );
		
		world.createBuilding(p1, b2);
		Unit u5 = new Soldier(p2, new Point(1,1));
		Unit u6 = new Soldier(p2, new Point(3,4));
		b2.storeUnit(u5);
		b2.storeUnit(u6);
		boolean b = false;
		if(b2.getOwner() == p2)  b = true;
		List<Unit>  unitsInside = b2.getUnitsInsideBuilding() ;
		boolean u1i = false;
		boolean u2i = false;
		for(int i= 0; i < unitsInside.size(); i++){
			Unit u = unitsInside.get(i);
			if(u5 == u){
				u1i = true;
			}
			if(u6 == u){
				u2i = true;
			}
			
		}
		
		assertEquals("Unit is not inside the building", true,  u1i);
		assertEquals("Unit is inside the building", false,  u2i);
		
		
		

	}
}
