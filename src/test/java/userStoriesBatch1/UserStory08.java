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
import ptoma.hexoral.units.Soldier;
import ptoma.hexoral.user.Player;

public class UserStory08 {

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
		p.setActionPoints(100);
		p.setEnergyPoints(100);
		Soldier sol1 = new Soldier(p, new Point(10, 10));
		game.createUnit(p, sol1);
		
		Soldier sol2 = new Soldier(p, new Point(11, 11));
		game.createUnit(p, sol2);
		
		try {
			p.getSchedule().addAction(new MoveAction(p, sol1, new Point(11, 0)));
		} catch (InvalidPointException e1) {
			System.err.println(e1.getMessage());
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			p.getSchedule().addAction(new MoveAction(p, sol1, new Point(9, 10)));
		} catch (InvalidPointException e1) {
			System.err.println(e1.getMessage());
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			p.getSchedule().addAction(new MoveAction(p, sol1, new Point(11, 11)));
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
