package tourney;

/**
*	A Player could be used to implement a human player for interactive contexts.
*	(It might even be sensible to prepare such a HumanPlayer class for
*	interactive testing!) but it is intended to support a machine player for CPU
*	vs CPU competition. 
*/

public interface Player
{
	public Move getMove(Board b)
}
