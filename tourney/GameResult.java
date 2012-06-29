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

	/** The player(s) who won the game. */
	private List<Player> winners;

	/**
	* 	The sequence of moves played in the game
	*/
	private ArrayList<Move> game;
	public GameResult() {
		this( new ArrayList<Player>());

	}

	public GameResult(List<Player> players)
	{
		this.game = new ArrayList<Move>();
		this.players = players;
	}


	/**
	 * Add method so we can intercept moves and build data as it is
	 * accumulated.
	 */
	public void  add(Move move) {
		if (!players.contains(move.getPlayer())) {
			players.add(move.getPlayer());
		}

		if (move.getWinners() != null)
			winners = move.getWinners();

		 game.add(move);
	}

	public ArrayList<Move> getGame()
	{
		return game;
	}
	
	public List<Player> getPlayers() {
		return players;
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
