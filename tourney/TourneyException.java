package tourney;

/**
 * <code>TourneyException</code> is an exception related to one of the classes
 * needed to allow Tournament to run properly. An exception of this kind comes
 * from the code in the tourney package or its sub-packages.
 */
public class TourneyException extends Exception
{
	
	/** Serial Version 0.1 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The message that will be returned by <code>getMessage</code>. Provided so
	 * it can be constructed outside of first line <code>super()</code> calls.
	 */
	protected String message;
	
	/**
	 * The default constructor allowing for no message, or more likely and
	 * preferably, for the message to be constructed in the a child class.
	 */
	public TourneyException() {
		message = "";
	}
	
	/**
	 * The String constructor, allowing the message to be set at instantiation.
	 * 
	 * @param msg
	 *            The message that will be returned by <code>getMessage</code>.
	 */
	public TourneyException(String msg) {
		this.message = msg;
	}
	
	public String getMessage() {
		return message;
	}
}