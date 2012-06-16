package countToN;

import java.util.ArrayList;
import java.util.List;

import tourney.Game;
import tourney.Move;
import tourney.Player;

/**
 * Simple Game that plays through a list of players, asking them to count to a
 * number by rotating each player and asking them for the next number in turn.
 */
public class CountToN implements Game {
	// the recommended implementations for testing.
	private static Player defaultAI = new CountToNPlayer();
	private static Player human = new CountToNHuman();

	/** The list of moves that went down */
	private List<Move> moveList;

	/** The current state of the game. */
	private CountState state;

	private int numberOfPlayers;
	private int howHigh; // how high you count until game over.

	/** Defaults to a 2 player game of Count to Ten */
	public CountToN() {
		this(2, 10);
	}

	public CountToN(int numberOfPlayers, int howHigh) {
		state = new CountState();
		moveList = new ArrayList<Move>(); // chose simple ArrayList for now
		this.numberOfPlayers = numberOfPlayers;
		this.howHigh = howHigh;
	}

	public List<Move> play(List<Player> players) {
		int count = 0;

		while (count < howHigh) {
			int indexOfPlayer = count % numberOfPlayers;

			Player currentPlayer = players.get(indexOfPlayer);

			Move move = currentPlayer.getMove((CountState)state.clone());
			move.setPlayer(currentPlayer); // set the player who made the move.
			state.updateState(move);

			// if it was legal then...
			moveList.add(move);

			/*
			 * else illegal moves force the count to stay the same and therefore
			 * the player to stay the same, because I based who plays on the
			 * count.
			 */

			count = state.getCount();
		}

		return moveList;
	}

	public Player getDefaultAIPlayer() {
		return defaultAI;
	}

	public Player getHumanPlayer() {
		return human;
	}

	public int playersPerGame() {
		return numberOfPlayers;
	}
}
