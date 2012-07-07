package tourney;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A Move encapsulates a Player's turn. It is a set of directions for altering
 * the state of a game. Moves are generated solely by <code>Player</code>
 * implementations, but may be annotated and decorated by the game to keep track
 * of various data. All games must have a child class of <code>Move</code> that
 * encapsulates a turn of that game, even if it is a shared class.
 */
public abstract class Move {

	/** The player who made this Move. */
	private Player player;

	/** The player(s) who won as a result of this move */
	private List<Player> winners;
	
	/**
	 * A map of annotations that can be used to decorate this move with special
	 * data.
	 */
	private HashMap<String, String> annotations = new HashMap<String, String>();

	/**
	 * Sets the player who made this move to <code>player</code>. It is often
	 * important to know who made what move. Player implementations should not
	 * try to set this data, but doing so will be harmless because the Game will
	 * overwrite any data set. That means the Game is also responsible for
	 * setting this data after they receive the move from the player.
	 * 
	 * @param player
	 *            The player who made this move.
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	/**
	 * Gets the player object that made this move. It will often be necessary
	 * for certain classes to know which player made what move, so it is
	 * important that games set this data.
	 * 
	 * @return The player who made this move.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Sets the player who became the winner of the game as a result of this
	 * move. This method is provided for declaring only one winner, which should
	 * be the most frequent use of <code>setWinner</code>.
	 * 
	 * @param winner
	 *            The player who won the game.
	 */
	public void setWinner(Player winner) {
		ArrayList<Player> singleWinner = new ArrayList<Player>();
		singleWinner.add(winner);

		setWinners(singleWinner);
	}

	/**
	 * Sets the players who won the game as a result of this move. This method
	 * is provided in the event that there is a tie for first place between
	 * multiple players. The game should append all the players who tied to a
	 * list and set them using this method. Do not try to call
	 * <code>setWinner(Player)</code> multiple times!
	 * 
	 * @param winners
	 *            The list of players who tied for first place.
	 */
	public void setWinners(List<Player> winners) {
		this.winners = winners;
	}

	/**
	 * Gets the list of players who won the game on this move. Most often a game
	 * will only have one winner, so the list returned here will frequently be
	 * of size 1. However, if it is larger, all the players are considered to be
	 * tied for first place. No one player in the list is considered to have
	 * done better than the other, regardless of order.
	 * 
	 * @return A list of all the players who won the game.
	 */
	public List<Player> getWinners() {
		return winners;
	}
	
	/**
	 * Appends an annotation to this move. Some games may find it useful to
	 * decorate the move based on how the player responded, or for other unique
	 * instances.
	 * 
	 * @param key
	 *            The String that will be used to reference this
	 *            <code>value</code>.
	 * @param value
	 *            The value to record in the specified <code>key</code>.
	 */
	public void annotate(String key, String value) {
		annotations.put(key, value);
	}
	
	/**
	 * Gets the annotation that was set for <code>key</code>.
	 * 
	 * @param key
	 *            The String that references the desired value.
	 * @return Gets the specified annotation.
	 */
	public String getAnnotation(String key) {
		return annotations.get(key);
	}
	
	/**
	 * Determines if <code>key</code> has been set for this move.
	 * 
	 * @param key
	 *            The specified key.
	 * @return True if <code>key</code> has been set by the
	 *         <code>annotate</code> method.
	 */
	public boolean isSet(String key) {
		return annotations.containsKey(key);
	}
}