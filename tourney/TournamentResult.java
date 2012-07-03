package tourney;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Mostly re-used match code for higher level. If we would like to calculate the
 * winner in some other way, we can do that, too. I just don't see how at the
 * moment.
 */
public class TournamentResult extends AbstractResult<MatchResult>
{
	/** A map of players to the number of matches they won in the tournament. */
	private HashMap<Player, Integer> numberOfWins;
	
	public TournamentResult(List<Player> players)
	{
		super(players);
		numberOfWins = new HashMap<Player, Integer>();
		for (Player player : players) {
			if (!numberOfWins.containsKey(player)) {
				numberOfWins.put(player, 0);
			}
		}
	}
	
	public void add(MatchResult result) {
		super.add(result);

		List<Player> gameWinners = result.getMatchWinners();

		for (Player winner : gameWinners) {
			if (numberOfWins.containsKey(winner)) {
				int oldValue = numberOfWins.get(winner);
				oldValue++;
				numberOfWins.put(winner, oldValue);
			} else {
				// How'd you get in here? Get off of my property!
			}
		}
	}

	/** Returns a list of results for all matches that this Player played in. */
	public ArrayList<MatchResult> getAllMatchesWithPlayer(Player player)
	{
		ArrayList<MatchResult> playersMatches = new ArrayList<MatchResult>();

		List<MatchResult> results = getResults();
		for (MatchResult result : results) {
			if (result.getPlayers().contains(player)) {
				playersMatches.add(result);
			}
		}

		return playersMatches;
	}
	
	/**
	 * Returns the winner(s) of the Tournament. Should we run tie-breakers against
	 * the winners here, and if so, should that be done at Match level?
	 */
	public List<Player> getTournamentWinners()
	{
		List<Player> matchPlayers = getPlayers();
		// Find the highest number in numberOfWins
		int max = 0;
		for (Player player : matchPlayers) {
			int wins = numberOfWins.get(player);
			if (wins > max) {
				max = wins;
			}
		}

		// add all players whose value is max to the winner list
		ArrayList<Player> winners = new ArrayList<Player>();

		for (Player player : matchPlayers) {
			int wins = numberOfWins.get(player);
			if (wins == max) {
				winners.add(player);
			}
		}

		return winners;
	}
}