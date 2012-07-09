package prisoner;

import tourney.Move;

public class IPD_Move extends Move
{
	private static final int[] rewardMatrix = 
			{
				IteratedPrisonersDilemma.BOTH_DEFECT,
				IteratedPrisonersDilemma.ONE_COOP,
				IteratedPrisonersDilemma.ONE_DEFECT,
				IteratedPrisonersDilemma.BOTH_COOP
			};
	

	


	/**
	*	The reward assigned to this move for the round. 
	*	This will be assigned when this round is passed to the game record.
	*/
	private int score;  


	/**
	* Is this an aggressive move? "Aggressive" moves are first-move defections
	*/
	private boolean aggressive;


	/**
	*	The does the player cooperate?
	*/
	private boolean cooperate;	
	
	/**
	*	Was this the first Move in an iteration?
	*/
	private boolean firstMove;
	
	/**
	*	A record of the previous player's Move, useful both to the Player and to
	*	posterity
	*/
	private Move previousMove;
	

	public IPD_Move(IPD_Move previousMove, boolean cooperate)
	{
		this.cooperate = cooperate;
		this.previousMove = previousMove;
	}
	

	public IPD_Move(boolean cooperate)
	{
		this.cooperate = cooperate;	
		this.previousMove = null;
	}

	public void setPrevious(IPD_Move previousMove)
	{
		this.previousMove = previousMove;
	}

	/**
	*	Get the decision represented by this Move: cooperate, or no?
	*/
	public boolean getPlay()
	{
		return cooperate;
	}


	/**
	*	Return true iff there was no previous Move
	*/
	public boolean wasFirstMove()
	{
		return previousMove == null;
	}	

	public int scoreMove(IPD_Move other)
	{	
		score = 0;
		if	(this.getPlay()) score +=1;
		if (other.getPlay()) score +=2;

		score = rewardMatrix[score];
	
		if (! cooperate && this.wasFirstMove())
		{	
			aggressive = true;
		}	

				

		((IPD_Player)this.getPlayer()).updateScore(score);	
		return score;
	}		
	
	public int getScore()
	{
		return score;
	}


}
