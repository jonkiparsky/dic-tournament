package prisoner;

import tourney.Player;
import tourney.Move;
import tourney.MachinePlayer;
/**
*	Sucker always cooperates. He tends to get nailed by Bastard pretty hard, but
*	he works out good when he plays against himself.
*/
public class IPD_Sucker
		extends IPD_Player
		implements MachinePlayer
{

	
	public IPD_Sucker()
	{
		super("IPD Sucker");

	}


	public Move getMove()
	{
		return new IPD_Move(previousMove, true);
	}
	
	public void update(Move move)
	{
		previousMove = (IPD_Move)move;
		// we don't really care what the move was, since
		// we're always returning true. 
	}
	
}
