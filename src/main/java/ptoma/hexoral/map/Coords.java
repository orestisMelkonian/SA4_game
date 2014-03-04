package ptoma.hexoral.map;

public class Coords {
	/**
	 * X coordinate.
	 */
	public int x;
	/**
	 * Y coordinate.
	 */
	public int y;

	/**
	 * @param x
	 *            The x coordinate
	 * @param y
	 *            The y coordinate
	 */
	public Coords(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Default constructor x=y=0.
	 */
	public Coords() {
		this.x = this.y = 0;
	}

	@Override
	public boolean equals(Object obj) {
		Coords c = (Coords) obj;
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (obj == this) {
			return true;
		} else {
			return this.x == c.x && this.y == c.y;
		}
	}

}
