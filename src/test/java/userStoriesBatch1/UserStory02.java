/**
 * 
 */
package userStoriesBatch1;

import static org.junit.Assert.*;

import java.awt.Point;

import org.apache.tools.ant.taskdefs.Sleep;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ptoma.hexoral.GameUISettings;
import ptoma.hexoral.building.HQ;
import ptoma.hexoral.exception.GameException;
import ptoma.hexoral.exception.InvalidPointException;
import ptoma.hexoral.game.Game;
import ptoma.hexoral.game.action.AttackAction;
import ptoma.hexoral.game.action.CreateAction;
import ptoma.hexoral.game.action.MoveAction;
import ptoma.hexoral.map.MapGenerator;
import ptoma.hexoral.units.Soldier;
import ptoma.hexoral.units.Unit;
import ptoma.hexoral.user.Player;

/**
 * @author usi
 * 
 */
public class UserStory02 {

	Game game;
	Thread gui;

	@Before
	public void test() {
		game = new Game(20, 20);
		MapGenerator gen = new MapGenerator(game.island, "test.bio");
		gen.applyParameters(50, 0, false, false, 10);
	}

	@Test
	public void seeMap() {
		Player p = new Player("Steve",game);
		game.addPlayer(p);
		Point hqp = game.putHQ(p);
		HQ hq = (HQ) game.getBuilding(hqp);
		game.createBuilding(p, game.getBuilding(hqp));
		System.out.println("Player AP: " + p.getActionPoints());
		System.out.println("Player EP: " + p.getEnergyPoints());
		try {
			p.getSchedule().addAction(new CreateAction(p, hq, "Soldier"));
		} catch (InvalidPointException e1) {
			System.err.println(e1.getMessage());
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			p.getSchedule().addAction(new CreateAction(p, hq, "Soldier"));
		} catch (InvalidPointException e1) {
			System.err.println(e1.getMessage());
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			p.getSchedule().addAction(new CreateAction(p, hq, "Soldier"));
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
