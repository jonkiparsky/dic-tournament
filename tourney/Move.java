package tourney;

/**
*	A Move encapsulates a Player's turn. Since different games have widely
*	divergent sorts of moves, this interface specifies little of what a Move
*	actually consists of.  
*/
public interface Move
{
  /**
	 * A vague method allowing for the Move to specify a source (whether a
	 * location on the Board, a letter in a Scrabble game, or an index of a card
	 * in your hand in a poker game.) to be moved, or otherwise set to the
	 * destination.
	 * 
	 * @return A series of 0 or more integers that can represent a coordinate on
	 *         a multidimensional board, or some other predetermined value.
	 */
	int[] getSource();

	// choosing int[] so we are not limited to 4 byte return values. You can
	// change if you foresee a problem.

	/**
	 * A destination (usually a location) for the source to act upon. Can be a
	 * symbolic value, or otherwise.
	 * 
	 * @return A series of 0 or more integers that can represent a coordinate on
	 *         a multidimensional board, or some other predetermined value.
	 */
	int[] getDestination();

	/*
	 * I rather think we can use this for Card games too now. Source simply has
	 * to mention an index of a Card on the field, and Destination simply has to
	 * say to which pile of cards. It's quite flexible, and the implementations
	 * can use constants to make things less confusing.
	 */
}
