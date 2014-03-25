package ptoma.hexoral.game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import ptoma.hexoral.game.action.Action;
import ptoma.hexoral.map.MapGenerator;
import ptoma.hexoral.map.WorldMap;
import ptoma.hexoral.user.Player;
import ptoma.hexoral.units.*;

/**
 * The container of all the elements for the game.
 * 
 * @author Stefanos Gatsios
 * 
 */
public class Game {

	/**
	 * The hexagon map of the island.
	 */
	public WorldMap island;

	/**
	 * The list of players.
	 */
	private HashMap<String, Player> players;
	
	/**
	 * Every unit of every player.
	 */
	private HashMap<Point,Unit> units;
	
	
	/**
	 * Constructor.
	 * @param width of the game
	 * @param height height of the game
	 */
	public Game(int width,int height) {
		//The creation of the map
		island = new WorldMap(width, height);
		//Creating the generator
		MapGenerator generator = new MapGenerator(island, "test.bio");
		//TODO add parameters for water and land percentage etcetera
		//initialization of players and their units
		players = new HashMap<String, Player>();
		units = new HashMap<Point, Unit>();
	}

	/**
	 * Adds a new player to the game.
	 * The gamer is stored according to his username.
	 * @param p object of the Player
	 */
	public void addPlayer(Player p) {
		this.players.put(p.getName(), p);
	}
	
	/**
	 * Removes a player from the game.
	 * @param p the player to be removed
	 */
	public void removePlayer(Player p) {
		this.players.remove(p.getName());
		for (Unit u : this.units.values()) {
			if(u.owner() == p) {
				this.units.remove(u);
			}
		}
		this.units.remove(p);
	}
	
	
	/**
	 * Get the object player according to his username.
	 * @param username the username of the player.
	 * @return Player object.
	 */
	public Player getPlayer(String username) {
		return this.players.get(username);
	}
	
	/**
	 * Creates a new unit for the player.
	 * @param player the player who creates the unit.
	 * @param unit the unit to be created.
	 */
	public void createUnit(Player player, Unit unit, Point p) {
		this.units.put(p, unit);
	}
	
	/**
	 * Return the units of the player as an ArrayList
	 * @param player to list his units
	 * @return ArrayList<Unit> of the units of the player.
	 */
	public ArrayList<Unit> getPlayerUnits(Player player) {
		ArrayList<Unit> ret = new ArrayList<Unit>();
		for (Unit unit : this.units.values()) {
			if(unit.owner() == player) {
				ret.add(unit);
			}
		}
		return ret;
	}
	
	public void executeTurn() {
		ArrayList<Action> allActions = new ArrayList<Action>();
		for(Player p : players.values()) {
			for(Action e : p.getSchedule().toArray()) {
				allActions.add(e);
			}
		}
		Collections.sort(allActions);
		for(Action e : allActions) {
			e.exec();
		}
	}
}
