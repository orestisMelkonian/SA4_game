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
import ptoma.hexoral.exception.InvalidPointException;
import ptoma.hexoral.game.Game;
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
		Unit u1 = new Soldier(p, new Point(10,10));
		game.createUnit(p, u1);
		try {
			p.getSchedule().addAction(new MoveAction(p, u1, new Point(11,11)));
		} catch (InvalidPointException e1) {
			System.err.println(e1.getMessage());
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
