package ptoma.hexoral.user;

public class User {

	/**
	 * The username of the player.
	 */
	protected String username;
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
            return false;
        if (obj == this)
            return true;
        if(this.hashCode() == obj.hashCode())
        	return true;
        else 
        	return false;
	}

	@Override
	public int hashCode() {
		return this.username.hashCode();
	}

	public User(String username) {
		this.username = username;
	}
	
	public String getName() {
		return this.username;
	}
}
