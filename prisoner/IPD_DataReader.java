package prisoner;

import tourney.Player;
import tourney.DataReader;
import tourney.TournamentResult;
import tourney.GameResult;
import tourney.MatchResult;
import java.util.ArrayList;


public class IPD_DataReader 
		implements DataReader
{

	private TournamentResult result;

	public IPD_DataReader (TournamentResult result)
	{
		this.result = result;
	}

	public void write()	
	{
		//filling this class in for the compiler
	}
	public void run()	
	{
		//filling this class in for the compiler
	}
	public String report()	
	{
		//filling this class in for the compiler

		String s = "";
		for (Player p:result.getPlayers())
		{
			for (MatchResult match: result.getAllMatchesWithPlayer(p))
			{
					s+= ("Results of Iterated Prisoner's Dilemma Match between ");
					s+= match.getPlayers().get(0);
					s+= (" and ");
					s+= match.getPlayers().get(1);
					s+= ("\n");
					
			}

		}
		return s;
	}

}
