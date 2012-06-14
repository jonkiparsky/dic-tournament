package tourny;

/**
 * A Player could be used to implement a human player for interactive contexts.
 * (It might even be sensible to prepare such a HumanPlayer class for
 * interactive testing!) but it is intended to support a machine player for CPU
 * vs CPU competition.
 */
public interface Player {
	/**
	 * Demand a move from the player based on the given state. (Chess-by-mail
	 * implementations can be made higher up the ladder, in my opinion)
	 */
	Move getMove(GameState state);


	/**
	*	A Player must have a unique identifier. This will allow stats to make
	*	coherent reports. 
	*	Tentative suggestion: ID should be composed of a game code, player's DIC
	*	member name, and a serial number (to allow for multiple entries)
	*/
	String getID();
}
