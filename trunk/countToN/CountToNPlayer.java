package countToN;

import tourney.GameState;
import tourney.Move;
import tourney.Player;

/**
 * Default AI player for CountToN. He plays the game flawlessly.
 */
public class CountToNPlayer implements Player {

	private final String id = "C2N_Default_1";

	@Override
	public Move getMove(GameState state) {
		// This player assumes the state is a CountState
		CountState cState = (CountState) state;

		CountMove move = CountState.getMoveForNumber(cState.getCount() + 1);

		return move;
	}

	public String getID()
	{
		return id;
	}

}
