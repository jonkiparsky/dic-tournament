package tourney;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that encapsulates everything that you would need to know about a
 * Game. It is responsible for informing you about all the data it can possibly
 * extract from the List of Moves, so that classes dealing with GameResult need
 * not be concerned with extracting data directly from the Moves.
 * 
 * This will also allow for certain attributes to be set from within the Game
 * implementation itself, such as the Winner of the game. That will prevent the
 * need to replay the Game to find the winner and other statistics.
 */
public class SimultaneousGameResult extends GameResult {

	/**
	 * The set of indices that each round begins on. offsets.get(0) will be the
	 * starting index of the first round and so on...
	 */
	private ArrayList<Integer> offsets;

	public SimultaneousGameResult(List<Player> players) {
		super(players);
		offsets.add(0); // first round starts at 0 always
	}

	/** Adds a round to the list of moves and records its place. */
	public void addRound(ArrayList<Move> round) {
		for (Move move : round) {
			add(move);
		}

		int lastIndex = offsets.size() - 1;
		offsets.add(round.size() + offsets.get(lastIndex)); // accumulate the
															// index
	}

	/** Returns the number of rounds in this GameResult. */
	public int numberOfRounds() {
		return offsets.size() - 1;
	}

	/**
	 * Gets the list of moves that represent round number X. Rounds start at 0
	 * like arrays.
	 */
	public ArrayList<Move> getRound(int round) {
		int startIndex;
		int endIndex;
		
		ArrayList<Move> moves = new ArrayList<Move>();
		
		// Guard against negatives
		if (round < 0) {
			round = 0;
		}
		
		if (round >= offsets.size()) {
			round = offsets.size() - 1;
			endIndex = offsets.size();
		} else {
			endIndex = offsets.get(round+1);
		}
		
		startIndex = offsets.get(round);
		
		for(int i = startIndex; i < endIndex; i++) {
			moves.add(this.getResults().get(i));
		}
		
		return moves;
	}
}
