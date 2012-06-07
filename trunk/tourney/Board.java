package tourney;

/**
*	Possibly mis-named, a Board represents the state of play at a moment. A Board
*	object is the mechanism by which a Game communicates to a Player the
*	situation to which that Player must respond, and it is that Board which the
*	Game must use to evaluate the Player's response. 
*	The exact representation of the Board is of course dependent on the game
*	being represented and the whims of the implementers; therefore the Board
*	interface specifies very little. 
*	It may be wise to create sub-interfaces for more particular forms, for
*	example grid-based games.
*/


public interface Board
{
	/**
	 * Pre: The move is a legal move for this board. Changes the inner state of
	 * the Board, based on the passed move.
	 * 
	 * Sheph: Should this method check for legality? Or should that be the
	 * Game's responsibility before calling process? I'm going for Game's
	 * responsbility for now
	 */
	void process(Move move);

	/**
	 * Resets the board to empty, so that a new game can be played on it from
	 * the beginning.
	 */
	void reset();
}
