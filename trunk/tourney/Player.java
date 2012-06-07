package tourney;

/**
*	A Player could be used to implement a human player for interactive contexts.
*	(It might even be sensible to prepare such a HumanPlayer class for
*	interactive testing!) but it is intended to support a machine player for CPU
*	vs CPU competition. 
*/

public abstract class Player
{
	private PlayerResultSet results;
	
	/**
	 *	Asks the Player to make a move based on this Board. A Move is made by
	 *	calling Board.submit(Move move, Player player), where player is a
	 *	reference to this player.
	*/

	public abstract void makeMove(Board b);
}
