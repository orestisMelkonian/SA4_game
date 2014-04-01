/**
 * 
 */
package userStoriesBatch1;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ptoma.hexoral.GameUISettings;
import ptoma.hexoral.building.CreationBuilding;
import ptoma.hexoral.building.HQ;
import ptoma.hexoral.building.ResourceBuilding;
import ptoma.hexoral.exception.GameException;
import ptoma.hexoral.exception.InvalidPointException;
import ptoma.hexoral.game.Game;
import ptoma.hexoral.game.action.CreateAction;
import ptoma.hexoral.game.action.MoveAction;
import ptoma.hexoral.map.MapGenerator;
import ptoma.hexoral.units.Soldier;
import ptoma.hexoral.units.Unit;
import ptoma.hexoral.user.Player;

public class UserStory07 {

	Game game;
	Thread gui;

	@Before
	public void test() {
		game = new Game(20, 20);
		game.initializeIsland(50, 0, false, false, 90);
	}

	@Test
	public void seeMap() {
		Player p = new Player("Orestis", game);
		game.addPlayer(p);
		p.setActionPoints(100);
		p.setEnergyPoints(100);
		ResourceBuilding rb1 = new ResourceBuilding(p, new Point(10, 10));

		ResourceBuilding rb2 = new ResourceBuilding(p, new Point(11, 11));

		ResourceBuilding rb3 = new ResourceBuilding(p, new Point(11, 10));

		ResourceBuilding rb4 = new ResourceBuilding(p, new Point(11, 10));

		game.createBuilding(p, rb1);
		game.createBuilding(p, rb2);
		game.createBuilding(p, rb3);
		game.createBuilding(p, rb4);
		System.out.println("Player EP = " + p.getEnergyPoints());
		System.out.println("Player AP = " + p.getActionPoints());

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
