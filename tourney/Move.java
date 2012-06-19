package tourney;

import java.util.HashMap;

/**
 * A Move encapsulates a Player's turn. It is a set of directions for altering
 * the state of a game. The relation between GameState and Move is very close
 * and most of the time that forces them to be made in tandem.
 */
public abstract class Move {
	
	/** ID of player who made this the move */
	public static final String PLAYER = "Player";

	/** ID of player who won with this move. */
	public static final String WINNER = "Winner";

	/** The turn number this move was made. */
	public static final String TURN = "Turn";
	

	/** The player who made this Move. */
	private Player player;

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