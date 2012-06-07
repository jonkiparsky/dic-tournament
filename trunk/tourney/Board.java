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
	 *	accepts a move submitted, validates that the move is from the correct
	 *	player and that that player has not yet moved		
	 */


	void submit(Move move, Player player);


	/**
	 *	Applies the selected move to the Board. Legality checks done here, as part
	 *	of applying the move. If the move results in an illegal state or a
	 *	won/drawn position, this will be detected by the Game (via a flag)
	 */	
	void apply(Move move, Player player);


	/**
	 * The current state of the game: ILLEGAL, GAME_OVER, or GAME_CONTINUE
	 */
	public int state();


	/**
	 * Resets the board to empty, so that a new game can be played on it from
	 * the beginning.
	 */
	void reset();
}
