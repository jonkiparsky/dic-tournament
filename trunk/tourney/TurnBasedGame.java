package tourney;

import java.util.List;

/**
 * TurnBasedGame is a game format where players are asked for Moves in turn.
 * Each player is asked to come up with his or her <code>Move</code> based on
 * how they see the game. After every <code>Move</code> all Players are updated
 * to reflect the change, and the next player is asked for a <code>Move</code>
 * again.
 * <p>
 * Examples include TicTacToe and Chess.
 * 
 * @since 0.1
 */
public abstract class TurnBasedGame extends Game
{
	
	/** The most recent player to be asked for a move. */
	protected Player currentPlayer;
	
	/** The returned <code>Move</code> of the current player. */
	protected Move move;
	
	/**
	 * Determines the next player in the turn order. Implementations should
	 * change this if they need anything but a circular turn order.
	 * 
	 * @return The <code>Player</code> to get the next move from.
	 */
	protected Player nextPlayer()
	{
		Player currentPlayer = activePlayers.remove(0);
		activePlayers.add(currentPlayer);
		return currentPlayer;
	}
	
	/**
	 * Gets the correct <code>Move</code> object with which to update a
	 * particular player. Default behavior is to return the same move for each
	 * Player, but implementations that require other behavior should override
	 * this method.
	 * 
	 * @param player
	 *            The player that will be sent the return result.
	 * @return The <code>Move</code> object with which to update
	 *         <code>player</code>.
	 */
	protected Move moveForPlayer(Player player)
	{
		return move;
	}
	
	protected void init()
	{
		gameResult = new GameResult(activePlayers);
	}
	
	/**
	 * This method sets the current player. If implementations need to override
	 * this method, they should call <code>super.prePoll()</code> or be sure to
	 * set the next player appropriately.
	 */
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
		for(Player player : activePlayers)
		{
			player.update(moveForPlayer(player));
		}
	}

	protected void record()
	{
		gameResult.add(move);
	}
}
