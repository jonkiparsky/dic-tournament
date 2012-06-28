package prisoner;

import tourney.Player;
import tourney.Move;


/**
*	Bastard always defects. His life tends to suck.
*/
public class IPD_Bastard extends IPD_Player
{
	private String id = "IPD_Random";
	private static int instanceCounter = 0;

	
	public IPD_Bastard()
	{
		instanceCounter++;
		id +=instanceCounter;
	}


	public Move getMove()
	{
		return new IPD_Move(false);
	}
	
	public String getID()
	{
		return id;
	}	

	public void update(Move move)
	{
		previousMove = (IPD_Move)move;
		// we don't really care what the move was, since
		// we're always returning false, since we never cooperate
	}
	
}
