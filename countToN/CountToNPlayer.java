package countToN;

import tourney.Move;
import tourney.Player;
import tourney.MachinePlayer;

/**
 * Default AI player for CountToN. He plays the game flawlessly.
 */
public class CountToNPlayer 
			extends Player
			implements MachinePlayer 
{

	 String PLAYER_NAME = "C2N_Default #";

	private int count = 0;
	public CountToNPlayer()
	{
		super("C2N_Default");
	}

	@Override
	public Move getMove() {
		// This player assumes the submitted Move is a CountMove
		CountMove myMove = new CountMove(count + 1);
		return myMove;
	}

	public String toString() {
		return getID();
	}

	@Override
	public void update(Move move) {
		CountMove cMove = (CountMove) move;
		count = cMove.getCount();
	}
}
