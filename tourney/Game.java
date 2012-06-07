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

	private int numberOfPlayers = 2; // a good default
	private boolean gameOver = false;
	private boolean moveReceived = false;

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


	/**
	 * Some thoughts from Sheph:
	 * 
	 * I think it would be best to let this be the public method. Play through a
	 * list of players, calling their moves in turn. What if a game would allow
	 * for a player to take 2 moves in a row? This method would need to be
	 * handled differently... ? Maybe we can refactor this down to a subclass?
	 * Or have this be in an interface method, and refactor this class to
	 * DefaultBoardGame?

	  + JPK: Game will have access to List, it can rearrange it as needed. 
	 */
	public void play(List<Player> players)
	{
		reset(); // or board.reset();
		if (players.size() != numberOfPlayers)
			return;
		while (!gameOver)
		{
			currentPlayer = players.remove(0);
			players.add(currentPlayer);	
			currentPlayerNotMovedYet = true;
			moveReceived = false;

			doMove(currentPlayer, board);
		}
	}



	void doMove(Player currentPlayer, Board board)
	{
		currentPlayer.makeMove(board);
		while (!moveReceived) 
		{
			// wait for move
			// let's not do it this way eventually, but
			// let the busy wait be a placeholder for a better way
		}


		if (board.state() == AbstractBoard.ILLEGAL)
		{
			// react to illegal move
		}
		else if (board.state() == AbstractBoard.GAME_OVER)
		{
			// deal with a finished game
			
		}
		else 
		{	
			// cleanup for next turn
		}
	}


	public void confirmMoveMade()
	{
		moveReceived = true;
	}

	public boolean confirmPlayerToMove(Player player)
	{
		if	(player != currentPlayer) return false;
		if (currentPlayerNotMovedYet)
		{
			currentPlayerNotMovedYet = false;
			return true;
		}
		return false;
	}

	/**
	 * Apply a Move to a Board to produce a new state of affairs. If the game is
	 * over, set the flag. If an illegal move is submitted, ?? kick the violater
	 * out of the tournament ?? (or deal with it another way?)
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
