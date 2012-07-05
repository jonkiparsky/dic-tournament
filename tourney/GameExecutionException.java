package tourney;

/**
 * <code>GameExecutionException</code> can be thrown in a situation that a
 * <code>Game</code> implementation's execution cannot recover from.
 */
public class GameExecutionException extends RuntimeException
{
	
	/** Serial Version 0.1 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The constructor taking a message parameter as a String.
	 * 
	 * @param msg
	 *            The reason why the game threw this exception.
	 */
	public GameExecutionException(String msg)
	{
		super(msg);
	}
}