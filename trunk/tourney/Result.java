package tourney;

import java.util.List;

/**
 * A Result is a collection of lower-level results or raw data (T). A GameResult
 * may be a collection of Moves, or Rounds, while a MatchResult would be a
 * collection of GameResults and a TournamentResult would aggregate
 * MatchResults. Ideally, each Result should perform as much analysis as it is
 * capable of doing. 
 */
public interface Result<T> {
	/**
	 * Return all of the players involved in this result.
	 */
	List<Player> getPlayers();

	/**
	 * Returns all of the results (however the implementation stores them)
	 */
	List<T> getResults();

	/**
	 * Adds a specific subsidiary result to this aggregation. When implemented,
	 * this method may perform on-the-fly analysis appropriate to this game, ie,
	 * keeping track of number of games won by each Player or types of moves
	 * deployed by a Player (defection/cooperation in Prisoner's Dilemma).
	 * Alternatively, this information may be aggregated by the Game's associated
	 * DataReader, in which case the default implementation in AbstractResult
	 * should be sufficient. 
	 */
	void add(T result);
}
