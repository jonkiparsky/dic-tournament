package prisoner;

import tourney.Move;

public class IPD_Move extends Move
{
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


}
