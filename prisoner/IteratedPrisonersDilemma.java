package prisoner;


import tourney.SimultaneousGame;
import tourney.Move;
import tourney.Tournament;
import tourney.Player;
import tourney.TournamentResult;
import tourney.DataReader;
import java.util.HashMap;

/**
*	Represents a series of encounters at Prisoner's Dilemma
*	Each "turn" is one "game": each player decides to default or to cooperate,
*	based on the results of the previous round, or on their whim.
*/


public class IteratedPrisonersDilemma extends SimultaneousGame
{
	public static final int BOTH_COOP = 1;
	public static final int BOTH_DEFECT = 6;
	public static final int ONE_COOP = 12;
	public static final int ONE_DEFECT = 0;
	/**
	"	iterations" is the number of encounters played in one 'Game'
	*/
	private int iterations = 10;
	private Move previousMove = null;



	protected void process()
	{
		
	}


	protected void init() 
	{
		iterations = 10;
		gameResult = new IPD_GameResult(activePlayers);
		for (Player p : activePlayers)
		{
			p.init();
		}
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
		return new IPD_Human();

	}
	
	
	public Player getDefaultAIPlayer()
	{
		return new IPD_Random();
	}



	public DataReader getDataReader(TournamentResult result)
	{
		return new IPD_DataReader(result);
	}
}
