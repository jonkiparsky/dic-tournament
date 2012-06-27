package tourney;

import java.util.List;

public  class Match
{
	MatchResult results;	
	List<Player> players;
	Game game;
	int numberOfGames;

	public Match(Game game, List <Player> players, int numberOfGames)
	{
		this.players = players;


		System.out.println("Match - # of players = "+players.size());

		this.numberOfGames= numberOfGames;
		this.game = game;
		results = new MatchResult();
	}

	public Match(Game game, List <Player> players)
	{
		this(game, players, 10); 	// play 10 games by default
									// no reason for picking 10!
	}


	public MatchResult playMatch()
	{
		for (int i = 0; i<numberOfGames; i++)
		{
			results.getGameResults().add(game.play(players));
			players.add(players.remove(0));  	// rotate players;
		}	

		return results;
	}


	public String getReport()
	{
		return "I believe " + numberOfGames + 
			" were played. I don't know who won.";	
	}

	public MatchResult getMatchResult()
	{
		return results;
	}

	public List<Player> getPlayers()
	{
		return players;
	}

}
