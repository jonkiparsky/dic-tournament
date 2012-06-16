package tourny;

/**
 * A Move encapsulates a Player's turn. It is a set of directions for altering
 * the state of a game. The relation between GameState and Move is very close
 * and most of the time that forces them to be made in tandem.
 */
public abstract class Move {
	/** The player who made this Move. */
	private Player player;

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
}