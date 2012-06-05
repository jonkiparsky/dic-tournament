package tourney;

import java.util.HashMap;
import java.util.ArrayList;

/**
*	Encapsulates per-game results for a Player
*/

public class PlayerResultSet
{
	public enum Outcome {WIN, LOSE, DRAW}

	private Player me;				// identity of this player
	private ArrayList<Player> opponents;
	private Outcome outcome;		// win, lose, or draw
	private int turnsTaken;			// turns taken by this Player in this game
	private int initialIndex;		// starting position
	private int score;				// free int field: score, finishing position,
											//	whatever the game requires


		// more stats can be added by extension

	/**
	*	Constructor takes information known at start of game.
	*/
	public PlayerResultSet(Player thisPlayer, ArrayList<Player> opponents, 
				int initialIndex)
	{
		me = thisPlayer;
		this.opponents = opponents;
		this.initialIndex=initialIndex;
		freeStats = new HashMap<String, String>();
	}


	/**
	*	Put a call to this in the move() method and make sure implementers call
	*	super()
	*/
	public void tickMove()
	{
		turnsTaken++;
	}

	/**
	*	Use endGame to wrap up the stats: everything that is only known at the end
	*	of the game gets written now
	*	If no score, score is zero. 
	*/
	public void endGame(Outcome outcome)
	{
		endGame(outcome, 0);
	}

	/**
	*	Wrap-up method
	*/
	public void endGame(Outcome outcome, int score)
	{
		this.outcome=outcome;
		this.score = score;
	}


	/**
	*	This is dubious. extract() in this implementation would ask this resultset
	*	to report itself back to the Statistics class (which would have the
	*	appropriate methods to receive this mess and muddle)
	**/

	public void extract(Statistics stats)
	{
		
		for (Player opponent: opponents)
			stats.putOutcome(me, opponent, outcome);
		for (Player opponent: opponents)
			stats.putScore(me, opponent, score);
		stats.aggregateOutcomeByInitialIndex(outcome,initialIndex);
		
		
	}	
	
}
