/**
 * 
 */
package userStoriesBatch1;

import java.awt.Point;
import org.junit.Before;
import org.junit.Test;

import ptoma.hexoral.GameUISettings;
import ptoma.hexoral.exception.GameException;
import ptoma.hexoral.exception.InvalidPointException;
import ptoma.hexoral.game.Game;
import ptoma.hexoral.game.action.BuildAction;
import ptoma.hexoral.user.Player;

public class UserStory07 {

	Game game;
	Thread gui;

	@Before
	public void test() {
		game = new Game(20, 20);
		game.initializeIsland(50, 0, false, false, 70);
	}

	@Test
	public void seeMap() {
		Player p = new Player("Orestis", game);
		game.addPlayer(p);
		p.setActionPoints(150);
		p.setEnergyPoints(150);
		
		System.out.println("Player EP = " + p.getEnergyPoints());
		System.out.println("Player AP = " + p.getActionPoints());
		try {
			p.getSchedule().addAction(
					new BuildAction(p, "ResourceBuilding", new Point(11, 11)));
		} catch (InvalidPointException e1) {
			System.err.println(e1.getMessage());
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			p.getSchedule().addAction(
					new BuildAction(p, "ResourceBuilding", new Point(10, 10)));
		} catch (InvalidPointException e1) {
			System.err.println(e1.getMessage());
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			p.getSchedule().addAction(
					new BuildAction(p, "ResourceBuilding", new Point(10, 11)));
		} catch (InvalidPointException e1) {
			System.err.println(e1.getMessage());
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			p.getSchedule().addAction(
					new BuildAction(p, "ResourceBuilding", new Point(11, 10)));
		} catch (InvalidPointException e1) {
			System.err.println(e1.getMessage());
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			p.getSchedule().addAction(
					new BuildAction(p, "ResourceBuilding", new Point(12, 12)));
		} catch (InvalidPointException e1) {
			System.err.println(e1.getMessage());
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			p.getSchedule().addAction(
					new BuildAction(p, "ResourceBuilding", new Point(12, 11)));
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
