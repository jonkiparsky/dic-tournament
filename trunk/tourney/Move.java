package tourney;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A Move encapsulates a Player's turn. It is a set of directions for altering
 * the state of a game. The relation between GameState and Move is very close
 * and most of the time that forces them to be made in tandem.
 */
public abstract class Move
{

	/** ID of player who made this the move */
	public static final String PLAYER = "Player";

	/** ID of player who won with this move. */
	public static final String WINNER = "Winner";

	/** The turn number this move was made. */
	public static final String TURN = "Turn";

	/** The player who made this Move. */
	private Player player;
	
	/** The player(s) who won as a result of this move */
	private ArrayList<Player> winners;

	private HashMap<String, String> annotations = new HashMap<String, String>();

	/** Signs this player's identity to this move. */
	public void setPlayer(Player player)
	{
		/*
		 * It should not matter if a Player attempts to use this method because
		 * the Game class will call this and override whatever he sets.
		 */
		this.player = player;
	}
	
	/** Utility method for adding only one player */
	public void setWinner(Player player) {
		this.winners = new ArrayList<Player>();
		this.winners.add(player);
	}

	/** Set all the players who tied for first. */
	public void setWinners(ArrayList<Player> players)
	{
		this.winners = players;
	}

	public Player getPlayer()
	{
		return player;
	}

	/**
	 * returns all winners for a game. most of the time this can be
	 * just size 1, but for draws we'll allow for flexibility
	 */
	public ArrayList<Player> getWinners()
	{
		return winners;
	}

	public void annotate(String key, String value)
	{
		annotations.put(key, value);
	}

	public String getAnnotation(String key)
	{
		return annotations.get(key);
	}

	public boolean isSet(String key)
	{
		return annotations.containsKey(key);
	}
}