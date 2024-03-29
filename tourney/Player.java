package tourney;

/**
 * The <code>Player</code> interface is designed to allow artificial
 * intelligence implementations to be designed for various games. This interface
 * is flexible and can be used to develop players for all sorts of games. Its
 * main purpose is to allow for machine implementations, but it can be used to
 * allow for simple interactive implementations.
 * <p>
 * The contract for a <code>Player</code> is simple. Each player must keep its
 * own version of the state of the game. The <code>update</code> method allows
 * for the game to keep the player's state up to date. Then, when the
 * <code>getMove</code> method is called by <code>Game</code>, the player simply
 * evaluates the current game position and returns his intentions for his action
 * encapsulated in a Move object.
 * 
 * @see Move
 */
public abstract class Player
{
	
	private static int instanceCounter = 0;

	/** The unique human-readable identifier for this player. */
	private String id;

	public Player()
	{
		String className = getClass().getName().replace(
				getClass().getPackage().getName() + ".", "");

		id = createInstanceID(className + " # ");
	}

	/**
	 * Appends this player's id with a unique instance number and returns the
	 * new id. This allows the tournament to tell different instances of the
	 * same class apart from one another.
	 * 
	 * @param name
	 *            The id to which to append an instance number.
	 * @return The new id for this player.
	 */
	protected String createInstanceID(String name)
	{
		instanceCounter++;
		id = name + instanceCounter;
		return id;
	}

	public void init()
	{

	}

	/**
	 * Gets this player's identifier. A Player must have a unique identifier so
	 * its statistics can be output and referenced. This will allow
	 * <code>DataReader</code>s to make coherent reports. The the API handles
	 * generating IDs by using the name of the class.
	 * <p>
	 * Therefore, class names (IDs) should take the form of:
	 * <p>
	 * <code>GameName_UserName_SubmissionNumber</code>
	 * <p>
	 * <b>GameName</b> - The name of the Game that this player has been designed
	 * for.<br>
	 * <b>UserName</b> - The name of the user submitting this player.<br>
	 * <b>SubmissionNumber</b> - If the user is submitting more than one player,
	 * append a submission number to the end of the class name. <br>
	 * 
	 * @return This player's unique identifier.
	 */
	public String getID()
	{
		return id;
	}

	public String toString()
	{
		return getID();
	}

	/**
	 * Demands a move from this player. This player must evaluate the game
	 * situation that it has been keeping, and return a legal <code>Move</code>
	 * object for the game.
	 * 
	 * @return The actions this player wishes to perform, encapsulated in a Move
	 *         object.
	 */
	public abstract Move getMove();

	/**
	 * Sends an update to the player in the form of the necessary
	 * <code>Move</code> object. The player should update their version of the
	 * game state in this method and <i>only</i> in this method. The player
	 * implementation should not try to update the state outside of this method,
	 * nor should they call this method themselves. The game is responsible for
	 * calling this method, even if it is with the player's own move.
	 * 
	 * @param move
	 *            An encapsulation of the actions to take in order to change the
	 *            state of the game appropriately.
	 */
	public abstract void update(Move move);
}
