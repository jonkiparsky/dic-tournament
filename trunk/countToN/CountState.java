package countToN;

import tourny.GameState;
import tourny.Move;

/** A simple GameState for maintaining a count. */
public class CountState extends GameState {
	private int count;

	@Override
	public void updateState(Move move) {
		// TODO Auto-generated method stub
		if (!(move instanceof CountMove)) {
			// big error
		}

		CountMove cMove = (CountMove) move;
		if (isLegal(cMove))
			count = cMove.getNextNumber();
		else {
			// big error again - tempted to throw an exception
		}
	}

	/**
	 * The interface for accessing the inner state of the game for the players
	 * to decide what to do.
	 */
	public int getCount() {
		return count;
	}

	/**
	 * We can leave where we determine if a move is legal to the
	 * implementations. In this game, I think it is best to do it here.
	 */
	private boolean isLegal(CountMove move) {
		if (move.getNextNumber() == (count + 1))
			return true;
		else
			return false;
	}

	/**
	 * An interface for the player to get a Move based on their guess for the
	 * next number. Again, I'm being purposely naive about letting them be
	 * wrong.
	 */
	public static CountMove getMoveForNumber(int nextNumber) {
		return new CountMove(nextNumber);
	}
}