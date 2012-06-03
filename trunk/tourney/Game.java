package tourney;

import java.util.List;

/**
* 	A Game incorporates the rules of play for some particular board game. 
*	Possibly will be expanded to include other sorts of games in future
*	Games do not necessarily include any provision for interactive play; they are
*	primarily intended to run CPU vs. CPU competitions. 
*/



public abstract class Game
{

	private Board board;

	private int numberOfPlayers=2; // a good default

	/**
	* We suggest that game developers provide a very basic "AI" for testing. The
	* default AI must generate legal moves, but they need not be good ones.
	* Random is fine. If no default AI player is provided, defaultAIPlayer should
	* be set to null.
	*/
	private Player defaultAIPlayer;
	
	/**
	*	We also suggest that game developers provide a human interface, also for
	*	testing. If none is provided, humanPlayer should be null.
	*/
	private Player humanPlayer;


	/**
	*	Play some number of iterations of the game with the specified list of
	*	Players, and report back (somehow) the results. Reporting TBD
	*/
	public void play(List<Player> players, int iterations)
	{
		

		// should verify that number of players is acceptable
		if (players.size() != numberOfPlayers)
			return;
			
		for (int i = 0; i<iterations; i++)
		{
			play(players);
		}
	}


	private void play(List<Player> players)
	{
		board.reset();	
		while (!gameOver)
		{
			board = process(players.remove(0).getMove(board));
			
		}
	}	


	/**
	*	Apply a Move to a Board to produce a new state of affairs. If the game is
	*	over, set the flag. If an illegal move is submitted, ?? kick the violater
	*	out of the tournament ?? (or deal with it another way?)
	*/

	private abstract Board process(Move move);
	
	
	/**
	* Not sure what this is meant for. Sheph? 
	*/
	public  abstract void reset();


	// need to determine statistics methods

}
