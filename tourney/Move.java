package tourney;

/**
*	A Move encapsulates a Player's turn. Since different games have widely
*	divergent sorts of moves, this interface specifies little of what a Move
*	actually consists of.  
*/
public interface Move
{
	/**
	 * Determines if this Move is legal for the given board, based on type
	 * of board, and the specific board state.
	 */
	boolean isLegal(Board board);
}
