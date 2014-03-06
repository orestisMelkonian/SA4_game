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
- Asychronous Multiplayer Aspect
- Server

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

5. Asynchronus Multiplayer Aspects.
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
	* Summary : After players submit their moves, server should implemenet the action in maximum of 30 seconds.
	* Description : When the actions are submited the server must make all computations (moves,change of values,attacks and battle outcomes) within a logical time. e.g. (per say 30 seconds)


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







---------------------------------------ORESTIS--------------------------------------------------------------

<><><><><><><><><><><><>GAME LOGIC<><><><><><><><><><><>


------>Basic Requirements

*Decision Making
	-Type:Non-functional
	-Summary:Decisions must be provided to the player
	-Description:In order for meaningful strategy to exist, multiple choices must be present.It provides motivation and,above all, fun.
	
*Understandable game mechanics
	-Type:Non-functional
	-Summary:Reducing game mechanics complexity
	-Description:If the game mechanics are difficult to comprehend players will immediately get bored. This involves a limited amount of unit types(three), clear combat mechanics, etc. 
	
*Offence-Defence Aspect
	-Type:Non-functional
	-Summary:Clear differentiation of attack and defence.
	-Description:When a combat occurs, one player is attacking and the other one is defending. This has impact on the damage factors, soldiers at stake. This will be decided by the previous movement (that brought the units to the point of battle) and the position on the map (according to headquarters location).
	
*Punishment for non-strategic play
	-Type:Non-functional
	-Summary:Strategy is mandatory!
	-Description:When a thoughtless move is made by a player, bad concequancies will arise. For example, if a player just produce tanks with no forethought and sends them to slaughter, the other player will just produce a few anti-tank units and win.
------>Advanced Requirements
*Risk/Reward Aspect
	-Type:Non-functional
	-Summary:Every decision has its price
	-Description:When a player is brought to the point of a choice (e.g. selecting between two equally priced units), this decision will help him solely in a few situations.
*Strategy VS luck
	-Type:
	-Summary:
	-Description:
*Less variables, more choices
	-Type:
	-Summary:
	-Description:
*Drawbacks coupled with benefits
	-Type:
	-Summary:
	-Description:
*Restricted player knowledge
	-Type:
	-Summary:
	-Description:

------>Complex Requirements
*Diversity VS Homogeny
	-Type:
	-Summary:
	-Description:
*Long-term Goals
	-Type:
	-Summary:
	-Description:

------>Optional Requirements
*Multiple roads to victory
	-Type:
	-Summary:
	-Description:
*No obvious choices
	-Type:
	-Summary:
	-Description:
*Clear feedback of choices
	-Type:
	-Summary:
	-Description:
	
<><><><><><><><><><><><><><><><><><><><><><><><><><><><>

<><><><><><><><><><><><>RPG ELEMENTS<><><><><><><><><><>
<><><><><><><><><><><><><><><><><><><><><><><><><><><><>
--------------------------------------EndOfOrestis----------------------------------------------------------
