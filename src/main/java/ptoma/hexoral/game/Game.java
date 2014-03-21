package ptoma.hexoral.game;

import java.util.ArrayList;
import java.util.HashMap;

import ptoma.hexoral.map.MapGenerator;
import ptoma.hexoral.map.WorldMap;
import ptoma.hexoral.user.Player;
import ptoma.hexoral.units.*;

/**
 * The container of all the elements for the game.
 * 
 * @author steve_000
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
	private HashMap<Player,ArrayList<Unit>> units;
	
	
	/**
	 * Constructor.
	 * @param width of the game
	 * @param height height of the game
	 */
	public Game(int width,int height) {
		//The creation of the map
		island = new WorldMap(width, height);
		//Creating the generator
		MapGenerator generator = new MapGenerator();
		//TODO add parameters for water and land percentage etcetera
		generator.generateMap(island, 50, "test.bio");
		//initialization of players and their units
		players = new HashMap<String, Player>(null);
		units = new HashMap<Player, ArrayList<Unit>>(null);
	}

	/**
	 * Adds a new player to the game.
	 * The gamer is stored according to his username.
	 * @param p object of the Player
	 */
	public void addPlayer(Player p) {
		this.players.put(p.getName(), p);
		this.units.put(p, new ArrayList<Unit>(null));
	}
	
	/**
	 * Removes a player from the game.
	 * @param p the player to be removed
	 */
	public void removePlayer(Player p) {
		this.players.remove(p.getName());
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
}
