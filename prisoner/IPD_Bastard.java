package prisoner;

import tourney.Player;
import tourney.Move;
import tourney.MachinePlayer;

/**
*	Bastard always defects. His life tends to suck.
*/
public class IPD_Bastard 
		extends IPD_Player
		implements MachinePlayer
{

	public IPD_Bastard()
	{
	}


	public Move getMove()
	{
		return new IPD_Move(false);
	}
	
	public void update(Move move)
	{
		previousMove = (IPD_Move)move;
		// we don't really care what the move was, since
		// we're always returning false, since we never cooperate
	}
	
}
