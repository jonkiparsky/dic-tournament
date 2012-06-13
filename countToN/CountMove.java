package countToN;

import tourny.Move;

/**
 * Simple move for count to N. I purposely allowed for their to be an illegal
 * move so that we can decide where we want to check for it.
 */
public class CountMove extends Move {
	private int nextNumber; // the number the player says is next

	public CountMove(int number) {
		nextNumber = number;
	}

	public int getNextNumber() {
		return nextNumber;
	}
}