package prisoner;

import tourney.Player;
import tourney.DataReader;
import tourney.TournamentResult;
import tourney.GameResult;
import tourney.MatchResult;
import java.util.ArrayList;
import java.util.List;


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
		

	}
	public void run()	
	{
		System.out.println("1) Show match moves in detail");
		showMatchDetail(1);
	}
	public String report()	
	{
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
					
					int timeServedPlayer0 = 0;
					int timeServedPlayer1 = 0;
					
					List<GameResult> games = match.getResults();
					for(GameResult game : games) {
						IPD_GameResult ipd_game = (IPD_GameResult)game;
						timeServedPlayer0 += ipd_game.getTotalTimeServed(match.getPlayers().get(0));
						timeServedPlayer1 += ipd_game.getTotalTimeServed(match.getPlayers().get(1));
					}
					
					s+= match.getPlayers().get(0);
					s+= (" served ");
					s+= Integer.toString(timeServedPlayer0);
					s+= (" total months in prison based on his decisions in the match.\n");
					
					s+= match.getPlayers().get(1);
					s+= (" served ");
					s+= Integer.toString(timeServedPlayer1);
					s+= (" total months in prison based on his decisions in the match.\n\n");
			}

		}
		return s;
	}


	private void showMatchDetail(int matchNumber)
	{
		MatchResult matchResult =  result.getResults().get(matchNumber);
		System.out.println("Results for match #" +matchNumber);
		for  (GameResult gameUncast: matchResult.getResults())
		{		
			IPD_GameResult game = (IPD_GameResult) gameUncast;
			System.out.println(game.details());
		
			
		}
				
		
	}
}
