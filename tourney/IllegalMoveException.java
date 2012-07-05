package tourney;

/**
 * <code>IllegalMoveException</code> is thrown when a player implementation
 * submits an illegal <code>Move</code> as defined by the <code>isLegal</code>
 * method in Game.
 */
public class IllegalMoveException extends TourneyException
{
	
	/** Serial Version 1.0 */
	private static final long serialVersionUID = 1L;
	
	/** A reference to the player responsible for the illegal move. */
	private Player culprit;
	
	/**
	 * Constructor that takes parameters defining who messed up and in what
	 * Game.
	 * 
	 * @param game The game that the illegal move occurred in.
	 * @param culprit The player who committed the illegal move.
	 */
	public IllegalMoveException(Game game, Player culprit) {
		this.culprit = culprit;
		
		StringBuilder sb = new StringBuilder();
		sb.append(culprit.getID());
		sb.append(" has committed an illegal move in ");
		sb.append(game.getName());
		sb.append(".");
		
		message = sb.toString();
	}
	
	/**
	 * Returns a reference to the offending player, so that he may be dealt with
	 * at a higher level.
	 * 
	 * @return The player who committed the illegal move.
	 */
	public Player getCulprit() {
		return culprit;
	}
}