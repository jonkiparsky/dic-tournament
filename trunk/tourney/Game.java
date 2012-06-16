package tourney;

import java.util.ArrayList;
import java.util.List;

/**
 * A Game incorporates the rules of play for some particular board game.
 * Possibly will be expanded to include other sorts of games in future Games do
 * not necessarily include any provision for interactive play; they are
 * primarily intended to run CPU vs. CPU competitions.
 */
public abstract class Game {
	/**
	 * Based on these players, play through the game, and return a List of Moves
	 * that depicts a record of the game.
	 */
	public List<Move> play(List<Player> players) {
		// Define what we'll need for a Game
		ArrayList<Player> activePlayers = new ArrayList<Player>();
		ArrayList<Move> moveList = new ArrayList<Move>();

		// Initialize the list of active players for tampering.
		for (Player player : players) {
			activePlayers.add(player);
		}

		init();

		do {
			Player currentPlayer = activePlayers.remove(0);
			activePlayers.add(currentPlayer);

			Move move = currentPlayer.getMove();

			if (!isLegal(move)) {
				// forfeit
				activePlayers.remove(currentPlayer);
			}

			updateEachPlayer(move, activePlayers);

			annotateMove(move);

			moveList.add(move);
		} while (keepGoing());

		return moveList;
	}

	// Back to needing this
	protected abstract boolean isLegal(Move move);

	/** game subclass tells us whether or not we keep going */
	protected abstract boolean keepGoing();

	/**
	 * implementation should analyze the move, and annotate it accordingly for
	 * statistics.
	 */
	protected abstract void annotateMove(Move move);

	/**
	 * Tell all players what happened. Default is to just pass them all the
	 * Move. Implementations can annotate them accordingly.
	 */
	protected void updateEachPlayer(Move move, List<Player> players) {
		for (Player player : players) {
			player.apply(move);
		}
	}

	/**
	 * Any setup code can go here.
	 */
	protected void init() {

	}

	/**
	 * We suggest that game developers provide a very basic "AI" for testing.
	 * The default AI must generate legal moves, but they need not be good ones.
	 * Random is fine. If no default AI player is provided, defaultAIPlayer
	 * should be set to null.
	 */
	public abstract Player getDefaultAIPlayer();

	/**
	 * We also suggest that game developers provide a human interface, also for
	 * testing. If none is provided, humanPlayer should be null.
	 */
	public abstract Player getHumanPlayer();

	/**
	 * Each game should define how many players it needs as a parameter. Users
	 * of this interface should ensure that the list of players passed to play()
	 * is the size of this return value.
	 */
	public abstract int playersPerGame();

	public abstract String getName();

	public abstract String getAuthor();
}
