package tourney;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * MatchResult encapsulates the results of an entire match of N games. It is a
 * list of the results for an individual Game. It interacts with many
 * GameResults to determine match-wide data for the user.
 */
public class MatchResult extends ArrayList<GameResult> {
	/** A map of players to the number of games they won in the match. */
	private HashMap<Player, Integer> players;

	public MatchResult() {
		super();
	}

	public boolean add(GameResult result) {
		List<Player> gamePlayers = result.getPlayers();

		// Add any new players to the list of match-wide players
		for (Player player : gamePlayers) {
			if (!players.containsKey(player)) {
				players.put(player, 0);
			}
		}

		List<Player> gameWinners = result.getWinners();

		for (Player winner : gameWinners) {
			if (players.containsKey(winner)) {
				int oldValue = players.get(winner);
				oldValue++;
				players.put(winner, oldValue);
			} else {
				// we don't know who this winner is...
			}
		}

		return super.add(result);
	}
}