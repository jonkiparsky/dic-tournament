package tourney;

import java.util.List;

/**
 * A Game incorporates the rules of play for some particular board game.
 * Possibly will be expanded to include other sorts of games in future Games do
 * not necessarily include any provision for interactive play; they are
 * primarily intended to run CPU vs. CPU competitions.
 */

public abstract class Game {

	private Board board;

	private int numberOfPlayers = 2; // a good default

	/**
	 * We suggest that game developers provide a very basic "AI" for testing.
	 * The default AI must generate legal moves, but they need not be good ones.
	 * Random is fine. If no default AI player is provided, defaultAIPlayer
	 * should be set to null.
	 */
	private Player defaultAIPlayer;

	/**
	 * We also suggest that game developers provide a human interface, also for
	 * testing. If none is provided, humanPlayer should be null.
	 */
	private Player humanPlayer;

	/**
	 * Play some number of iterations of the game with the specified list of
	 * Players, and report back (somehow) the results. Reporting TBD
	 */
	public void play(List<Player> players, int iterations)
	{

		// should verify that number of players is acceptable
		if (players.size() != numberOfPlayers)
			return;

		for (int i = 0; i < iterations; i++)
		{
			play(players);
		}
	}

	/**
	 * Some thoughts from Sheph:
	 * 
	 * I think it would be best to let this be the public method. Play through a
	 * list of players, calling their moves in turn. What if a game would allow
	 * for a player to take 2 moves in a row? This method would need to be
	 * handled differently... ? Maybe we can refactor this down to a subclass?
	 * Or have this be in an interface method, and refactor this class to
	 * DefaultBoardGame?
	 */
	private void play(List<Player> players)
	{
		reset(); // or board.reset();
		while (!gameOver)
		{
			board = process(players.remove(0).getMove(board));

		}
	}

	/**
	 * Apply a Move to a Board to produce a new state of affairs. If the game is
	 * over, set the flag. If an illegal move is submitted, ?? kick the violater
	 * out of the tournament ?? (or deal with it another way?)
	 * 
	 * How do we kick the violator out? We would have to disturb the tuples.
	 * Perhaps we can throw an IllegalMoveException with a reference to the
	 * player, which the tournament can then disqualify and recalculate the
	 * tuples? Or Just give a bypass to his competitor?
	 */

	protected abstract Board process(Move move, Board board);

	/**
	 * Not sure what this is meant for. Sheph?
	 * 
	 * Was thinking the tournament would call this from outside. Do you think
	 * the Game should be responsible for playing itself multiple times? I think
	 * the main argument for that is to keep which player plays first fair? We
	 * could always cycle the list we input.
	 */
	public abstract void reset();

	// need to determine statistics methods

}