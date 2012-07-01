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
public class GameResult extends AbstractResult<Move> {
	/** The winners of this game. */
	private List<Player> winners;
	
	public GameResult(List<Player> players) {
		super(players);
		winners = new ArrayList<Player>();
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