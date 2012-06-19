package tourney;

import java.util.ArrayList;
import java.util.List;

public  class Match
{
	ArrayList<ArrayList<Move>> gameRecords;	
	List<Player> players;
	Game game;
	int numberOfGames;

	public Match(Game game, List <Player> players, int numberOfGames)
	{
		this.players = players;


		System.out.println("Match - # of players = "+players.size());

		this.numberOfGames= numberOfGames;
		this.game = game;
		gameRecords = new ArrayList<ArrayList<Move>>();
	}

	public Match(Game game, List <Player> players)
	{
		this(game, players, 10); 	// play 10 games by default
									// no reason for picking 10!
	}


	public ArrayList<ArrayList<Move>> playMatch()
	{
		for (int i = 0; i<numberOfGames; i++)
		{
			gameRecords.add((ArrayList<Move>)game.play(players));
			players.add(players.remove(0));  	// rotate players;
		}	

		return gameRecords;
	}


	public String getReport()
	{
		return "I believe " + numberOfGames + 
			" were played. I don't know who won.";	
	}

	public ArrayList<ArrayList<Move>> getGameRecords()
	{
		return gameRecords;
	}

	public List<Player> getPlayers()
	{
		return players;
	}

}