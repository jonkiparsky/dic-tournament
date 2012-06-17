package countToN;

import tourney.GameState;
import tourney.Move;
import tourney.Player;
import tourney.Update;

/**
 * Default AI player for CountToN. He plays the game flawlessly.
 */
public class CountToNPlayer implements Player {

	private String id = "C2N_Default";
	private static int instanceCounter = 0;


	private int currentCount = 0;
	public CountToNPlayer()
	{
		instanceCounter++;
		id += instanceCounter;
	}

	@Override
	public Move getMove() {
		// This player assumes the submitted Move is a CountMove
		CountMove myMove = new CountMove(currentCount + 1);

		return myMove;
	}

	public String getID()
	{
		return id;
	}

	@Override
	public void update(Move move) {
		CountMove cMove = (CountMove) move;
		currentCount = cMove.getCount();
	}
}
