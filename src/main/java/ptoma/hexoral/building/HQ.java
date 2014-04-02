package ptoma.hexoral.building;

import java.awt.Point;

import ptoma.hexoral.user.Player;


public class HQ extends CreationBuilding{
	/**
	 * HQ Constructor
	 * @param owner	The player, which has this HQ
	 * @param x	vertical coordinate of the Building
	 * @param y	horizontal coordinate of the Building
	 */
	public HQ(final Player owner, Point p){
		super(owner, p);
		this.baseUnitCapacity = 3;
	}
	@Override
	public String toString()	{
		return (this.getClass().getSimpleName() + " in (" + getPosition().x + ", " + getPosition().y +
				")     Health:" + getHealth() + "/" + baseHealth + "    Owner: " + this.getOwner().getName());
	}
}
