package ptoma.hexoral.game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


import ptoma.hexoral.building.Building;
import ptoma.hexoral.exception.AttackException;


import ptoma.hexoral.exception.AttackException;

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
	 * Every building of every player.
	 */
	private HashMap<Point, Building> buildings;
	
	/**
	 * Constructor.
	 * @param width of the game
	 * @param height height of the game
	 */
	public Game(int width,int height) {
		//The creation of the map
		this.island = new WorldMap(width, height);
		//Creating the generator
		MapGenerator generator = new MapGenerator(island, "test.bio");
		//TODO add parameters for water and land percentage etcetera
		//initialization of players and their units
		this.players = new HashMap<String, Player>();
		this.units = new HashMap<Point, Unit>();
		this.buildings = new HashMap<Point, Building>();
		
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
		List<Point> unitPositions = new ArrayList<Point>();
		for (Unit u : this.units.values()) {
			if(u.owner().equals(p)) {
				unitPositions.add(u.getPosition());
			}
		}
		//Remove all the units.
		for(Point pos : unitPositions) {
			this.units.remove(pos);
		}
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
	public void createUnit(Player player, Unit unit) {
		this.units.put(unit.getPosition(), unit);
	}
	
	/**
	 * Creates a new building for the player.
	 * @param player the player who creates the building.
	 * @param unit the building to be created.
	 */
	public void createBuilding(Player player, Building building) {
		this.buildings.put(building.getPosition(), building);
	}
	
	/**
	 * Return the units of the player as an ArrayList.
	 * @param player to list his units
	 * @return ArrayList<Unit> of the units of the player.
	 */
	public final List<Unit> getPlayerUnits(Player player) {
		final List<Unit> ret = new ArrayList<Unit>();
		for (Unit unit : this.units.values()) {
			if (player == null || unit.owner().equals(player)) {
				ret.add(unit);
			}
		}
		return ret;
	}
	
	/**
	 * Retrieves the unit in position where.
	 * @param where the position of the unit
	 * @return the unit itself.
	 */
	public Unit getUnit(Point where) {
		return this.units.get(where);
	}
	
	
	/**
	 * Moves the units from where to toWhere.
	 * @param where from where
	 * @param toWhere to where
	 */
	public void moveUnit(Point where, Point toWhere) {
		Unit u = this.units.get(where);
		this.units.remove(where);
		this.units.put(toWhere, u);
	}

	/**
	 * Destroys unit in position where.
	 * @param where the unit is located.
	 */
	public void destroyUnit(Point where) {
		this.units.remove(where);
	}
	
	/**
	 * Destroys building in position where.
	 * @param where the building is located.
	 */
	public void destroyBuilding(Point where) {
		this.buildings.remove(where);
	}
	
	/**
	 * Executes the turn actions.
	 * @throws AttackException 
	 */
	public void executeTurn() throws AttackException {
		ArrayList<Action> allActions = new ArrayList<Action>();
		for(Player p : players.values()) {
			for(Action e : p.getSchedule().toArray()) {
				allActions.add(e);
			}
		}
		Collections.sort(allActions);
		for(Action e : allActions) {
			try {
			e.exec();
			} catch (AttackException attack) {
				attack.getAction().exec();
			} 
		}
		//TODO apply resources updates
	}
	
	/**
	 * Updates the units positions and kills dead units.
	 */
	private final void updateUnits() {
		
		//Kill dead units.
		List<Unit> deadUnits = new ArrayList<Unit>();
		for(Unit u : this.units.values()) {
			if(u.getHealth() <= 0) {
				deadUnits.add(u);
			}
		}
		//update the hash map
		for(Unit u : deadUnits) {
			this.units.remove(u.getPreviousPosition());
		}
		
		//For moved units;
		List<Unit> movedUnits = new ArrayList<Unit>();
		for(Unit u : this.units.values()) {
			if(!u.getPreviousPosition().equals(u.getPosition())) {
				movedUnits.add(u);
			}
		}
		//Update the hash map
		for(Unit u : movedUnits) {
			this.units.remove(u.getPreviousPosition());
			this.units.put(u.getPosition(), u);
		}
		
		
	}
}
