package tourney;

import java.util.List;

/**
 * A Game incorporates the rules of play for some particular board game.
 * Possibly will be expanded to include other sorts of games in future Games do
 * not necessarily include any provision for interactive play; they are
 * primarily intended to run CPU vs. CPU competitions.
 */
public interface Game {
	/**
	 * Based on these players, play through the game, and return a List of Moves
	 * that depicts how the game went down.
	 */
	List<Move> play(List<Player> players);

	/**
	 * We suggest that game developers provide a very basic "AI" for testing.
	 * The default AI must generate legal moves, but they need not be good ones.
	 * Random is fine. If no default AI player is provided, defaultAIPlayer
	 * should be set to null.
	 */
	Player getDefaultAIPlayer();

	/**
	 * We also suggest that game developers provide a human interface, also for
	 * testing. If none is provided, humanPlayer should be null.
	 */
	Player getHumanPlayer();

	/**
	 * Each game should define how many players it needs as a parameter. Users
	 * of this interface should ensure that the list of players passed to play()
	 * is the size of this return value.
	 */
	int playersPerGame();

	String getName();

	String getAuthor();
}
