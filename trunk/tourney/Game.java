package tourney;

import java.util.List;

/**
 * A Game incorporates the rules of play for some particular board game.
 * Possibly will be expanded to include other sorts of games in future Games do
 * not necessarily include any provision for interactive play; they are
 * primarily intended to run CPU vs. CPU competitions.
 */

public abstract class Game {
	private AbstractBoard board;
	private List<Move> moves;

	private boolean currentPlayerNotMovedYet = true;
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
	private Player currentPlayer;
	private List<Player> players;




	public void play()
	{
		while (! board.gameOver())
		{
			Move m = currentPlayer.makeMove(board);
			board.apply(m, currentPlayer);
			moves.add(m);
			setNextPlayer();
			
		}
	
	}


	private void setNextPlayer()
	{
		currentPlayer = players.remove(0);
		players.add(currentPlayer);
	}


	public abstract void reset();

	
}
