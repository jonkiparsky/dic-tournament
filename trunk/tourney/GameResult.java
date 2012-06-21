package tourney;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that encapsulates everything that you would need to know about a
 * Game. It is responsible for informing you about all the data it can possibly
 * extract from the List of Moves, so that classes dealing with GameResult need
 * not be concerned with extracting data directly from the Moves.
 * 
 * This will also allow for certain attributes to be set from within the Game
 * implementation itself, such as the Winner of the game. That will prevent the
 * need to replay the Game to find the winner and other statistics.
 */
public class GameResult {
	/** The players who participated in the game. */
	private List<Player> players;

	private List<Move> moves;

	/** The player(s) who won the game. */
	private List<Player> winners;

	public GameResult() {
		// more of a builder pattern, constructor not needed.
	}

	/**
	 * Saves a reference to each Player that participated in the game within
	 * this GameResult. In order to maintain accurate and complete results, it
	 * would be desirable for the list of players to be in the order they were
	 * when the game started. Therefore, a Game should call this method before
	 * it alters the order of its players. Likewise, this method adds the
	 * players to a separate List so the order will not be altered.
	 */
	public void setPlayers(List<Player> players) {
		players = new ArrayList<Player>();
		for (Player player : players) {
			this.players.add(player);
		}
	}

	public List<Player> getPlayerList() {
		return players;
	}

	/**
	 * Saves a reference to the record of moves for this game. A Game can call
	 * this method at the end, or in the beginning. It shouldn't matter, because
	 * only a reference is being saved.
	 */
	public void setMoveList(List<Move> moves) {
		this.moves = moves;
	}

	public List<Move> getMoveList() {
		return moves;
	}

	/**
	 * Utility method for declaring only one winner, which should most often be
	 * the case.
	 */
	public void setWinner(Player winner) {
		ArrayList<Player> singleWinner = new ArrayList<Player>();
		singleWinner.add(winner);
		
		setWinners(singleWinner);
	}

	/** Set all the players who tied for first place. */
	public void setWinners(List<Player> winners) {
		this.winners = winners;
	}

	/**
	 * Returns a list of all the players who were in first place. A list of size
	 * 1, means that only one player won the game. This should be the most often
	 * result, however to allow for the case of a tie, we must pass a List. If
	 * the list has more than one entry, it can be assumed that all of the
	 * players were tied for first. It must not be assumed that any of the
	 * players in this list did better than the others, unless agreed upon by
	 * the Game and the other classes using this data.
	 */
	public List<Player> getWinners() {
		return winners;
	}
}