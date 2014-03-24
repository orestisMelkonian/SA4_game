package ptoma.hexoral.units;

import ptoma.hexoral.user.Player;

public class Soldier extends Unit {

	protected static int BASE_ATTACK = 100;
	protected static int BASE_HEALTH = 200;
	protected static int BASE_DEFENCE = 200;

	public Soldier(Player owner, int x, int y) {
		super(owner, x, y);
		this.health = Soldier.BASE_HEALTH;
		this.attackPower = Soldier.BASE_ATTACK;
		this.defencePower = Soldier.BASE_DEFENCE;
	}

	public int attack() {
		return this.attackPower;
	}

	public void defend(int damage) {
		this.health -= damage;

	}

}
