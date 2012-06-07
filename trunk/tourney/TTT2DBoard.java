package tourney;

/**
 * A Board implementation representing a 2D TicTacToe board.
 */
public class TTT2DBoard implements Board {
	public static final int UNSET = 0;
	public static final int X = -1;
	public static final int O = 1;

	private int[][] board;

	public TTT2DBoard() {
		board = new int[3][3];
		// defaults to unset, but reset() for good measure.
		reset();
	}

	public void reset() {
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				board[x][y] = UNSET;
			}
		}
	}

	/*
	 * notice here a Move has no notion of X or O, the process method is from
	 * the Board interface, so we cannot pass to it which value to set (even if
	 * we could determine that in Game)
	 * 
	 * Options include creating a selectPlayer(X or O) method to call before
	 * processing. Alternatively, have Move know what value it places. This
	 * would force the player to also know what value he is.
	 * 
	 * We could create an abstract Implementation of Player for each game, that
	 * the contestant can extend, It would do all the casting for the player,
	 * and we can pass the necessary values (Like "hey you are player X") and
	 * then the actual contestant doesn't have to worry about it.
	 */
	public void process(Move move) {
		if (move instanceof TTT2DMove) {
			TTT2DMove m = (TTT2DMove) move;
			board[m.x][m.y] = X; // how do we know the value? See above.
		}
	}

	/**
	 * Returns the appropriate Move object for this type of Board based on the
	 * players intentions of marking location (x, y) with his mark (X or O).
	 */
	public TTT2DMove getMoveForPosition(int x, int y) {
		return new TTT2DMove(x, y);
	}

	/**
	 * Board specific Move.
	 */
	public class TTT2DMove implements Move {
		/** Position in the array to make the Move */
		private int x, y;

		/**
		 * Creates a new Move that will have the player make his mark at
		 * position (x, y) in the 2D array.
		 */
		TTT2DMove(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public boolean isLegal(Board b) {
			if (!(b instanceof TTT2DBoard))
				return false;
            
      TTT2DBoard passedBoard = (TTT2DBoard)b;
      
			if (passedBoard[x][y] == UNSET)
				return true;
			else
				return false;
		}
	}
}