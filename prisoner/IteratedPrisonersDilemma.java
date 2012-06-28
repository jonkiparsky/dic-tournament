package prisoner;


import tourney.Game;
import tourney.Move;
import tourney.Player;
import java.util.HashMap;

/**
*	Represents a series of encounters at Prisoner's Dilemma
*	Each "turn" is one "game": each player decides to default or to cooperate,
*	based on the results of the previous round, or on their whim.
*/


public class IteratedPrisonersDilemma extends Game
{
	/**
	"	iterations" is the number of encounters played in one 'Game'
	*/
	private int iterations;
	private Move previousMove = null;
	private HashMap<IPD_Player, IPD_Move> playersToMovesMap;


	/**
	*	The default game plays some number, currently 10, of iterations, and a
	*	standard reward matrix.
	*/

	public IteratedPrisonersDilemma()
	{
		this(10);
		
	}

	

	/**
	* Accepts an int, number of iterations to play	
	*/
	public IteratedPrisonersDilemma(int iterations)
	{
		this.iterations = iterations;
		this.playersToMovesMap = new HashMap<IPD_Player, IPD_Move>();
	}

	public void init()
	{
		move = null;
	}

	protected void processMove()
	{
		((IPD_Move)move).setPrevious((IPD_Move)previousMove);
		previousMove=move;
		
	}


	protected boolean isLegal(Move move)
	{
		return true;
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
