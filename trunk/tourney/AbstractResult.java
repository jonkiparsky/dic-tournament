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


	/**
	*	Creates an empty result set with an empty list of Players. It is the
	*	responsibility of the Game designer to ensure that the list of Players is
	*	not empty. 
	*/
	public AbstractResult() {
		this(new ArrayList<Player>());
	}

	
	/**
	*	Creates a empty result set, using the players provided. 
	*/
	public AbstractResult(List<Player> players) {
		this.results = new ArrayList<T>();
		this.players = players;
	}

	/**
	 *	Adds a lower-level result object to this aggregation. When implemented,
	 *	this method may perform on-the-fly analysis appropriate to this game.
	 *	Alternately, all analysis may be performed by the DataReader; in this
	 *	latter case, there is no need to override this method.
	 */
	public void add(T result) {
		results.add(result);
	}

	/**
	*	Returns the result set aggregated here as unanalyzed data. 
	*/
	public List<T> getResults() {
		return results;
	}

	/**
	*	Returns the Players represented in this aggregation. It is the
	*	responsibility of the Game writer to ensure that all Players are added. 
	* 		(unless we can ensure that this will happen? -jpk)
	*/
	public List<Player> getPlayers() {
		return players;
	}
}
