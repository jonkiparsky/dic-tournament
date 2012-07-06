package tourney;

import java.util.List;


/**
*	Match represents a series of Games between one set of players, where one set
*	is exactly enough for one game. A Match can consist of as many playings of a
*	Game as the tournament director desires. 
*	Match returns a MatchResult, which is an aggregation of GameResults. 
*	These MatchResults are aggregated into a TournamentResult, which is the
*	object read by a DataReader.
*/

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
		System.out.print("Players = ");
		for (Player p : players)
		{
			System.out.print(p + "  ");
		}
		this.numberOfGames= numberOfGames;
		this.game = game;
		results = new MatchResult(players);
	}

	/**
	*	Sets up a Match with a default number of iterations. (currently 10) 
	*/
	public Match(Game game, List <Player> players)
	{
		this(game, players, 10); 	// play 10 games by default
									// no reason for picking 10!
	}


	/**
	*	Plays through the configured Match and returns a Result.
	*/
	public MatchResult playMatch() 
				throws IllegalMoveException, GameExecutionException
	{
		for (int i = 0; i<numberOfGames; i++)
		{
			results.add(game.play(players));
			players.add(players.remove(0));  	// rotate players;
		}	

		return results;
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
