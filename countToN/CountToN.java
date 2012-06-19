package countToN;

import tourney.Game;
import tourney.Move;
import tourney.Player;

/**
 * Simple Game that plays through a list of players, asking them to count to a
 * number by rotating each player and asking them for the next number in turn.
 */
public class CountToN extends Game {
	// the recommended implementations for testing.
	private static String name = "Count To Ten";
	private static String author = "R. Shepherd";

	private static Player defaultAI = new CountToNPlayer();
	private static Player human = new CountToNHuman();

	/** The current state of the game. */
	private int count;

	private int numberOfPlayers;
	private int howHigh; // how high you count until game over.

	/** Defaults to a 2 player game of Count to Ten */
	public CountToN() {
		this(2, 10);
	}

	public CountToN(int numberOfPlayers, int howHigh) {
		this.numberOfPlayers = numberOfPlayers;
		this.howHigh = howHigh;
		this.count = 0;
	}

	protected boolean isLegal(Move move) {
		CountMove cMove = (CountMove) move;
		return cMove.getCount() == count + 1;
	}

	protected void processMove() {
		super.processMove(); // Needed to set the player appropriately
		count = ((CountMove) move).getCount(); // we could just increment

		move.annotate(Move.TURN, Integer.toString(count));
		move.annotate(Move.PLAYER, currentPlayer.getID());
		
		if (count == howHigh) {
			move.annotate(Move.WINNER, currentPlayer.getID());
		}
	}

	protected void init() {
		count = 0;
		move = new CountMove(0);
		updateEachPlayer(players);

	}

	protected boolean keepGoing() {
		return count < howHigh;
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

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}
}
