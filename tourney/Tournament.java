package tourney;

import java.util.List;

public class Tournament
{
	/**
	* The game in question.
	*/

	private Game game;

	/**
	*	The number of iterations of each game to play for each set of players
	*/
	private int iterations;

	/**
	*	The number of players required to play an iteration of the game
	*/
	private int numberOfPlayers;


	
	public static void main(String[] args)
	{
		System.out.println("Hello, world.");
	}



	public Tournament(Game game)
	{
		this.game=game;
	}

	/**
	*	Pre: list of Players exists, Game is set, iterations is set
	*	Runs a Tournament: plays all possible combinations of players (incl.
	*	each AI vs. self). Game is responsible for making sure conditions of play
	*	are fair (ie, players start equal number of games)
	*/

	void runTournament()
	{
  		List<List <Player>> playerTuples = generatePlayerTuples(); 
		for (List<Player> tuple: playerTuples)
  		{
    		game.play(tuple, iterations);
  		}
 
	}

	/** Given N players per game and list L of players, generate all possible
 	* combinations of N Players, including duplicates, order not significant.
	*/
	private List<List<Player>> generatePlayerTuples() 
	{   
		return null;
	}

}
