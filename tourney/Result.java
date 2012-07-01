package tourney;

import java.util.List;

/**
 * A Result is a collection of lesser tiered results or data (T). A GameResult may
 * be a collection of Moves, or Rounds, while a MatchResult would be a
 * collection of GameResults.
 */
public interface Result<T> {
	/**
	 * Return all of the players involved in this result.
	 */
	List<Player> getPlayers();

	/**
	 * Returns all of the results (however the implementations stores them)
	 */
	List<T> getResults();

	/**
	 * Adds a specific lesser tiered result to this result. This result should
	 * handle it accordingly. (For example, if the result implies a victory for
	 * a certain player, then the implementation should record that in this
	 * method, if it needs to)
	 */
	void add(T result);
}