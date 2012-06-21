package prisoner;

import tourney.Player;
import tourney.Move;
import java.util.Random;


public class IPD_Random extends IPD_Player
{
	private String id = "IPD_Random";
	private static int instanceCounter = 0;

	private Random random = null;
	
	public IPD_Random()
	{
		random = new Random();
		instanceCounter++;
		id +=instanceCounter;
	}


	public Move getMove()
	{
		return new IPDMove(random.nextBoolean());
	}
	
	public String getID()
	{
		return id;
	}	

	public void update(Move move)
	{

		// we don't really care what the move was, since
		// we're a random move generator
	}
	
}
