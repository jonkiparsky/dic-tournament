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
	private List<Player> players;

	/** A map of players to the number of games they won in the match. */
	private HashMap<Player, Integer> numberOfWins;

	public MatchResult() {
		super();
	}

	public boolean add(GameResult result) {
		List<Player> gamePlayers = result.getPlayers();

		// Add any new players to the list of match-wide players
		for (Player player : gamePlayers) {
			if (!players.contains(player)) {
				players.add(player);
			}

			if (!numberOfWins.containsKey(player)) {
				numberOfWins.put(player, 0);
			}
		}

		List<Player> gameWinners = result.getWinners();

		for (Player winner : gameWinners) {
			if (numberOfWins.containsKey(winner)) {
				int oldValue = numberOfWins.get(winner);
				oldValue++;
				numberOfWins.put(winner, oldValue);
			} else {
				// we don't know who this winner is...
			}
		}

		return super.add(result);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public List<Player> getMatchWinners() {
		// Find the highest number in numberOfWins
		int max = 0;
		for (Player player : players) {
			int wins = numberOfWins.get(player);
			if (wins > max) {
				max = wins;
			}
		}

		// add all players whose value is max to the winner list
		ArrayList<Player> winners = new ArrayList<Player>();

		for (Player player : players) {
			int wins = numberOfWins.get(player);
			if (wins == max) {
				winners.add(player);
			}
		}

		return winners;
	}
}