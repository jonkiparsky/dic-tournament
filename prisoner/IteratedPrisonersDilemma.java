package prisoner;


import tourney.Game;
import tourney.Move;
import tourney.Player;

public class IteratedPrisonersDilemma extends Game
{

	private int turns;

	public IteratedPrisonersDilemma()
	{
		this.turns = 10;
	}

	public IteratedPrisonersDilemma(int turns)
	{
		this.turns = turns;
	}


	protected boolean isLegal(Move move)
	{
		return true;
	}


	protected boolean keepGoing()
	{
		return turns-- > 0;
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
