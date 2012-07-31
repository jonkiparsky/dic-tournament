package prisoner;

import tourney.Player;
import tourney.Move;
import tourney.MachinePlayer;
import java.util.Random;


public class IPD_Random 
		extends IPD_Player
		implements MachinePlayer
{

	private Random random = null;
	
	public IPD_Random()
	{
		random = new Random();
	}


	public Move getMove()
	{
		return new IPD_Move(random.nextBoolean());
	}

	public void update(Move move)
	{

		// we don't really care what the move was, since
		// we're a random move generator
	}
	
}
