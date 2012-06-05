package tourney;


/**
*	A data blob for passing information of statistical interest back to the
*	Tournament class. Created as a placeholder; this class may disappear if it
*	does not find a purpose. 
*/

public abstract class Statistics
{
  // just keeping track of what we need
  
  private int duration; // in # of moves
  
  // the index of the player in the passed List. could be -1 for draw?
  // how do we handle a draw between 2 people in a 3 man game.
  private int winner;
  
  private int whoWentFirst; // Which player went first?

	

	// stubs for callbacks from PlayerResultSet
	// not abstract, because these should be one-time implementations
	// I just don't know if they go here yet

	public void putOutcome(Player player, Player opponent,
		PlayerResultSet.Outcome outcome)
	{}
	
	public void putScore(Player player, Player opponent, int score)
	{}
	
	public void aggregateOutcomeByInitialIndex(PlayerResultSet.Outcome outcome, 
		int initialIndex)
	{}
}
