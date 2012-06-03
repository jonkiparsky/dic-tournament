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
	public abstract void play(List<Player> players, int interations); 


	
	/**
	* Not sure what this is meant for. Sheph? 
	*/
	public  abstract void reset();


	// need to determine statistics methods

}
