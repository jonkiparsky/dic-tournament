package tourney;

import java.util.List;

/**
 * TurnBasedGame is a game format where players are asked for Moves in turn.
 * Each player is given an indefinite amount of time to come up with his or her
 * Move based on how they see the Board. After every Move all Players are
 * updated to reflect the change.
 * 
 * Examples include TicTacToe and Chess.
 */
public abstract class TurnBasedGame extends Game
{
	
	/** The most recent player to make a move. */
	protected Player currentPlayer;
	
	/** The returned move of the current player. */
	protected Move move;
	
	/**
	 * Returns the next player to move. Base implementation simply rotates first
	 * player in list to end of list.
	 */
	protected Player nextPlayer()
	{
		Player currentPlayer = activePlayers.remove(0);
		activePlayers.add(currentPlayer);
		return currentPlayer;
	}
	
	/**
	 * Get the correct Move object for a particular player. Default is to return
	 * the same move for each Player. If private information is to be returned
	 * for particular Players, use a Map of Players to Moves.
	 */
	protected Move moveForPlayer(Player player)
	{
		return move;
	}
	
	protected void init()
	{
		gameResult = new GameResult(activePlayers); 
				// Default, should be overridden
	}
	
	protected void prePoll()
	{
		currentPlayer = nextPlayer();
	}
	
	protected void poll() throws IllegalMoveException
	{
		move = currentPlayer.getMove();
		move.setPlayer(currentPlayer); // Ensure that the source is correct.

		if (!isLegal(move)) {
			throw new IllegalMoveException(this, move.getPlayer());
		}
	}
			
	protected void updateEachPlayer(List<Player> players)
	{
		for(Player player : activePlayers) {
			player.update(moveForPlayer(player)); 
					// Default, can be overridden if need be.
		}
	}

	protected void record()
	{
		gameResult.add(move);
	}
}
