package userStoriesBatch1;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import ptoma.hexoral.GameUISettings;
import ptoma.hexoral.building.HQ;
import ptoma.hexoral.exception.GameException;
import ptoma.hexoral.exception.InvalidPointException;
import ptoma.hexoral.game.Game;
import ptoma.hexoral.game.action.BuildAction;
import ptoma.hexoral.game.action.CreateAction;
import ptoma.hexoral.game.action.StoreAction;
import ptoma.hexoral.units.Soldier;
import ptoma.hexoral.user.Player;

public class UserStory12a {

	Game game;
	Thread gui;

	@Before
	public void test() {
		game = new Game(20, 20);
		game.initializeIsland(50, 0, false, false, 40);
	}

	@Test
	public void seeMap() {
		Player p = new Player("Orestis", game);
		game.addPlayer(p);
		p.setActionPoints(1500);
		p.setEnergyPoints(1500);

		Point hqp = game.putHQ(p);
		HQ hq = (HQ) game.getBuilding(hqp);
		game.createBuilding(p, game.getBuilding(hqp));
		game.createUnit(p, new Soldier(p, new Point(0, 0)));
		game.createUnit(p, new Soldier(p, new Point(1, 0)));
		game.createUnit(p, new Soldier(p, new Point(2, 0)));
		game.createUnit(p, new Soldier(p, new Point(3, 0)));
		try {
			p.getSchedule().addAction(
					new StoreAction(p, hq, game.getUnit(new Point(0, 0))));
		} catch (InvalidPointException e1) {
			System.err.println(e1.getMessage());
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			p.getSchedule().addAction(
					new StoreAction(p, hq, game.getUnit(new Point(1, 0))));
		} catch (InvalidPointException e1) {
			System.err.println(e1.getMessage());
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			p.getSchedule().addAction(
					new StoreAction(p, hq, game.getUnit(new Point(2, 0))));
		} catch (InvalidPointException e1) {
			System.err.println(e1.getMessage());
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			p.getSchedule().addAction(
					new StoreAction(p, hq, game.getUnit(new Point(3, 0))));
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
