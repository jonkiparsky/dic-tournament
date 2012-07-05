package countToN;

import tourney.Move;
import tourney.Player;
import tourney.MachinePlayer;

/**
 * Default AI player for CountToN. He plays the game flawlessly.
 */
public class CountToNPlayer 
			implements Player, MachinePlayer 
{

	private String id = "C2N_Default";
	private static int instanceCounter = 0;

	private int count = 0;
	public CountToNPlayer()
	{
		instanceCounter++;
		id += ("_" + instanceCounter);
	}

	@Override
	public Move getMove() {
		// This player assumes the submitted Move is a CountMove
		CountMove myMove = new CountMove(count + 1);
		return myMove;
	}

	public String getID()
	{
		return id;
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