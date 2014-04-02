package userStoriesBatch1;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import ptoma.hexoral.GameUISettings;
import ptoma.hexoral.building.HQ;
import ptoma.hexoral.exception.GameException;
import ptoma.hexoral.exception.InvalidPointException;
import ptoma.hexoral.game.Game;
import ptoma.hexoral.game.action.CreateAction;
import ptoma.hexoral.game.action.MoveAction;
import ptoma.hexoral.map.Hexagon;
import ptoma.hexoral.units.Soldier;
import ptoma.hexoral.user.Player;

public class UserStory10b {

	Game game;
	Thread gui;

	@Before
	public void test() {
		game = new Game(20, 20);
		game.initializeIsland(50, 0, false, false, 10);
	}

	@Test
	public void seeMap() {
		Player p = new Player("Orestis", game);
		game.addPlayer(p);
		Player p2 = new Player("Steve", game);
		game.addPlayer(p2);
		Soldier sol1 = new Soldier(p2, new Point(10, 10));
		game.createUnit(p2, sol1);
		
		HQ hq = new HQ(p, new Point(11, 11));
		game.createBuilding(p, hq);
		this.game.island.setHexagon(11, 11, Hexagon.type.LAND);
		
		try {
			p2.getSchedule().addAction(new MoveAction(p2, sol1, new Point(11, 11)));
		} catch (InvalidPointException e1) {
			System.err.println(e1.getMessage());
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		gui = new Thread(new Runnable() {
			public void run() {
				new GameUISettings(game);
			}
		});
		gui.start();
		System.err.println("GUI Started");
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}

}

