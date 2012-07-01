package prisoner;


import tourney.SimultaneousGame;
import tourney.Move;
import tourney.Player;
import java.util.HashMap;

/**
*	Represents a series of encounters at Prisoner's Dilemma
*	Each "turn" is one "game": each player decides to default or to cooperate,
*	based on the results of the previous round, or on their whim.
*/


public class IteratedPrisonersDilemma extends SimultaneousGame
{
	/**
	"	iterations" is the number of encounters played in one 'Game'
	*/
	private int iterations = 10;
	private Move previousMove = null;



	protected void processMove()
	{
		((IPD_Move)move).setPrevious((IPD_Move)previousMove);
		previousMove=move;
		
	}


	protected void init() 
	{
		iterations = 10;
		gameResult = new IPD_GameResult();
	}


	
	protected boolean isLegal(Move move)
	{
		if (move instanceof IPD_Move)
			return true;
		else return false;
	
	}


	protected boolean keepGoing()
	{
		return iterations-- > 0;
	}

	public String getName()
	{
		return "Iterated Prisoner's Dilemma";
	}
	public String getAuthor()
	{
		return "jpk";
	}

	public int playersPerGame()
	{
		return 2;
	}

	public Player getHumanPlayer()
	{
		return null;
	}
	
	
	public Player getDefaultAIPlayer()
	{
		return new IPD_Random();
	}


}




