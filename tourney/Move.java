package tourney;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A Move encapsulates a Player's turn. It is a set of directions for altering
 * the state of a game. The relation between GameState and Move is very close
 * and most of the time that forces them to be made in tandem.
 */
public abstract class Move {

	/** The player who made this Move. */
	private Player player;

	/** The player(s) who won as a result of this move */
	private List<Player> winners;

	private HashMap<String, String> annotations = new HashMap<String, String>();

	/** Signs this player's identity to this move. */
	public void setPlayer(Player player) {
		/*
		 * It should not matter if a Player attempts to use this method because
		 * the Game class will call this and override whatever he sets.
		 */
		this.player = player;
	}

	public Player getPlayer() {
		return player;
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
	 * Returns a list of all the players who win the game as a result of this
	 * move.
	 */
	public List<Player> getWinners() {
		return winners;
	}

	public void annotate(String key, String value) {
		annotations.put(key, value);
	}

	public String getAnnotation(String key) {
		return annotations.get(key);
	}

	public boolean isSet(String key) {
		return annotations.containsKey(key);
	}
}