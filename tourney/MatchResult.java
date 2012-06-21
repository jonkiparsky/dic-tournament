package tourney;

import java.util.ArrayList;
import java.util.List;

/**
 * MatchResult encapsulates the results of an entire match of N games. It
 * interacts with many GameResults to determine match-wide data for the user.
 */
public class MatchResult {
	/** A list of the results for an individual Game. */
	private List<GameResult> gameResults;

	public MatchResult() {
		gameResults = new ArrayList<GameResult>();
	}

	/**
	 * Returns a reference list of individual GameResults. This can be used by
	 * Match for building the list, or by anything that needs access to
	 * individual results.
	 */
	public List<GameResult> getGameResults() {
		return gameResults;
	}
}