#Hexarchy
![](https://raw.github.com/orestisMelkonian/SA4_game/master/Ideas/Logo_w_space.png)

--------------
##Categories
|Categories|Impementator|
|------|------|
| Map generation | Stefanos 
| Unit generation | Vitor
| UI | Hanieh
| Combat Mechanics | Lokesh
| Server side | Stefanos & Hanieh
| Game Logic | Orestis
| Multiplayer | Lokesh
| RPG Elements | Orestis 

##Core Requirements
- Map representation 
- Units
- Currency
- Turn based Logic
- Combat
- Asynchronous Multiplayer Aspect
- Server
- Ethical Constraints
- Legislative Issues

---------------
##Core Requirements in Detail

1. Map Existence
	* Type : Functional.
	* Summary : The game environment is represented onto a map.
	* Description : When you start the game, a map composed of hexagons, is generated on the screen and it changes as the game advances
	
2. Units 
 	* Type : Functional.
 	* Summary : The ability of the player to create and handle units.
 	* Description : As a player begins with a certain ammount of currency and resources, the player will be able to create and handle units.
  
 	
3. Currency
 	* Type : Functional.
 	* Summary : Each player has his own economic development.
 	* Description : Each player is allocated with an initial amount of currency which allows, in addition with soldiers, to create a unit of any type.
 
4. Turn based logic.
	* Type : Functional.
	* Summary : The player will play in turns.
	* Description : Each player has a restricted amount of time to perform his actions. 

5. Asynchronous Multiplayer Aspects.
	* Type : Functional.
	* Summary : The game supports many players playing together and no artificial intelligence is involved.
	* Description : Every player has a limited amount of time to coordinate his moves, once every player has completed their moves, the turn is performed, with the exception of a player exceeding the available time.

6. Combat.
	* Type : Functional.
	* Summary : Players can attack other players.
	* Description : By using

7. Server.
	* Type : Functional.
	* Summary : The server implements the decisions made by the players.
	* Description : The server validates the actions of the users, performs all the actions and informs the outcome of every turn to all the players.

8. Goal of the game.
	* Type : Non-functional.
	* Summary : The goal of the game is to destroy the enemy's HQ.
	* Description : In order to motivate players a primary goal is provided; which is to dominate or destroy the enemy's headquarters.

9. Server Response Time.
	* Type : Non-functional (Performance).
	* Summary : After players submit their moves, server should implement the action in maximum of 30 seconds.
	* Description : When the actions are submitted the server must make all computations (moves,change of values,attacks and battle outcomes) within a logical time. e.g. (per say 30 seconds)

10. Ethical Constraints
	* Type : Non-functional
	* Summary : The game should comply with the current ethical standards of the society.
	* Description : The game must not conflict with any ethical constraints of today's society (e.g. PEGI content rating system so as children can be users).

11. Legislative Issues
	* Type : Non-functional
	* Summary : Avoid all law violations.
	* Description : The game should not violate any law, such as copyrights etc.


##Map Generation  _Stefanos Gatsios_
1. Develop Basic templates for map, different sizes 
2. Random generated maps based on templates //Complex
3. Algorithm for valid moves, accessability from each hexagon ,units cannot pass thru sea for example
4. Range based searching on based based on points.

##Server Side _Stefanos Gatsios & Hanieh_
1. Notification on users.
2. Logging of actions, online record
3. Account management, friends
4. Resolutions for inactive players and network errors







##Orestis
--------------------------------------------------------

###Game Logic _Orestis_
--------------------------------------------------------


####Basic Requirements

- Decision Making
	* Type : Non-functional
	* Summary : Decisions must be provided to the player
	* Description : In order for meaningful strategy to exist, multiple choices must be present.It provides motivation and,above all, fun.
	
- Understandable game mechanics
	* Type: Non-functional
	* Summary: Reducing game mechanics complexity
	* Description: If the game mechanics are difficult to comprehend players will immediately get bored. This involves a limited amount of unit types(three), clear combat mechanics, etc. 
	
- Offense-Defense Aspect
	* Type: Non-functional
	* Summary: Clear differentiation of attack and defense.
	* Description: When a combat occurs, one player is attacking and the other one is defending. This has impact on the damage factors, soldiers at stake. This will be decided by the previous movement (that brought the units to the point of battle) and the position on the map (according to headquarters location).
	
- Punishment for non-strategic play
	*Type: Non-functional
	*Summary: Strategy is mandatory!
	*Description: When a thoughtless move is made by a player, bad consequences will arise. For example, if a player just produce tanks with no forethought and sends them to slaughter, the other player will just produce a few anti-tank units and win.

####Advanced Requirements

- Risk/Reward Aspect
	* Type: Non-functional
	* Summary: Every decision has its price
	* Description: When a player is brought to the point of a choice (e.g. selecting between two equally priced units), this decision will help him only in a few situations, but prove a drawback in others.
	
- Strategy VS luck
	* Type: Functional 
	* Summary: Tradeoff between predefined and random elements.
	* Description: As we gain luck, using some random generation in the mechanics' computations, we lose the element of strategy and vice versa. Of course, randomness means more fun to the game. A perfect balance must be implemented for the game.
	
- Less variables, more choices
	* Type: Functional
	* Summary: Not too many entities in addition with many possible moves.
	* Description: Having a great number of different units, confusion will arise. Emphasis on multiple choices within those limited units is mandatory.
	 
- Drawbacks coupled with benefits
	* Type: Functional
	* Summary: Every choice should have different impact on different situations.
	* Description: When a player is required to select one of many choices, each one will be providing positive statistical feedback on different aspects of the game (e.g. anti-ship tank VS anti-plane tank).
	
- Restricted player knowledge
	* Type: Functional	 
	* Summary: Concealing implementations of the system and certain elements of the environment.
	* Description: For interest to be continuously present in the game flow, information should appear in different time positions (e.g. fog of war to conceal map / reveal when units provide visibility ).

####Complex Requirements

- Diversity VS Homogeny
	* Type: Functional / Non-functional
	* Summary: Stable/unstable element in the available paths.
	* Description: Supported by the rpg elements of the game, each player will be able to create a unique path that will have a huge roll on team-based battles (e.g. a team  of a navy admiral and an explosives expert would benefit from an economy expert and achieve great balance). There will be stable paths that can withstand any diverse situations and unstable ones, which will provide the player freedom to decide how he will adapt to different situations.

- Long-term Goals
	* Type: Functional / Non-functional
	* Summary: Development elements outside of one single battle
	* Description: There should be a constant improvement of a player as long as he plays the game, which will be mainly supported be the rpg system outside a battle. Of course, the previous greatly influence functional things inside the game (additional units, different variables' percentages). 

####Optional Requirements

- Multiple roads to victory
	* Type: Functional
	* Summary: Multiple strategy methods may lead to victory
	* Description: There shouldn't be a single set of moves that are guaranteed to succeed. Instead, many possible moves will generate a different strategy context.

- No obvious choices
	* Type: Functional
	* Summary: Omission of totally determined paramenters
	* Description: If a player has a single option for one thing, it shouldn't be an option, but implemented by the system.

- Clear feedback of choices
	* Type: Functional
	* Summary: Positive/negative feedback to player choices
	* Description: A choice must provide feedback in short time, so players will be able to develop a concrete,a assured strategy.
	
###RPG Elements _Orestis_

####Basic Requirements

- Unit experience
	* Type: Functional
	* Summary: Upgrade units after certain achievements
	* Description: By winning combats and discovering environment entities, units will gain levels, providing additional bonuses to their properties (soldier capacity, movement points, statistical specific variables, etc...).
	
- Player overall development system
	* Type: Functional
	* Summary: Character experience system
	* Description: Apart from the battles themselves, a constant entity representing the player will gain leves, which will give him access to new units, give him certain percentages for multiple variables and build a unique path for the player.

- Randomness in combat
	* Type: Functional
	* Summary: Selection of a range of quantities(w/ randomness), instead of predefined ones.
	* Description: A lot of outcomes in the game will depend on a random element (rolling a dice), so as not all choices are possible to calculate. That is mandatory for a game of one-day turns, as so many hours will give the  opportunity to calculate every parameter, if all information is revealed.

####Advanced Requirements

####Complex Requirements

- Unique player paths
	* Type: Funcional
	* Summary: Different talent trees
	* Description: When a player levels up, talent points will be awarded that can be spent for evolution in a different tree. That will provide many possible talent trees with different pros and cons. It also produce great interest in team-based games where the choice a which players should cooperate will provide many meaningful aspects to analyze.

- Player-Character Identification
	* Type: Non-functional
	* Summary: Make the user play the role of his character
	* Description: The player should identify himself with his character, so as more sentimental connection is present in the game. Gaining levels will be equivalent to improving oneself's personality (restricted by ethical constraints)

- Unique hero units
 	* Type: Functional	
	* Summary: Existence of very strong single units with special abilities
	* Description: Single and unique strong units can be produced that can execute very special actions and give a nice variety on the gameplay (e.g. a diplomat who can use "Ceasefire" , making battles impossible for X turns).  
	

####Optional Requirements

- Unique appearance
	* Type: Non-functional
	* Summary: Player can parametrize the visual aspect of the game
	* Description: The player is able to decide the appearance of his units,building,etc... completely independent from the gameplay. This is an additional patch to the requirement 'Player-Character Identification'
