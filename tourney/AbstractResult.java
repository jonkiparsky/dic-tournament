package tourney;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractResult just adds the common code that all Results a going to share
 * into one class so we can reuse the code.
 */
public abstract class AbstractResult<T> implements Result<T> {
	/** The players who participated in the game. */
	private List<Player> players;

	/**
	 * The sequence of data that represents this result
	 */
	private List<T> results;

	public AbstractResult() {
		this(new ArrayList<Player>());
	}

	public AbstractResult(List<Player> players) {
		this.results = new ArrayList<T>();
		this.players = players;
	}

	/**
	 * Add method so we can intercept moves (or rounds) and build data as it is
	 * accumulated.
	 */
	public void add(T result) {
		results.add(result);
	}

	public List<T> getResults() {
		return results;
	}

	public List<Player> getPlayers() {
		return players;
	}
}
