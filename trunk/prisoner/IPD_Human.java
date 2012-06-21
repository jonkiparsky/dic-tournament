package prisoner;

import tourney.Player;
import tourney.Move;
import java.util.Random;
import java.util.Scanner;


public class IPD_Human extends IPD_Player
{
	private String id = "IPD_Human";
	private static int instanceCounter = 0;
	private boolean prevMove = false;
	private boolean haveMoved=false;
	private Random random = null;
	private Scanner scan = null;
	public IPD_Human()
	{
		random = new Random();
		instanceCounter++;
		id +=instanceCounter;
		scan = new Scanner(System.in);

	}


	public Move getMove()
	{
		if (haveMoved)
			System.out.println("Your opponent's previous move was to "+
				(prevMove? "cooperate" : "defect"));
		System.out.println ("Do you defect or cooperate? (d/c)");
		boolean choice;
		boolean acceptInput = false;
		while (true)
		{
		 	String input = scan.nextLine();
			if (input.charAt(0) == 'd')
			{
				choice = false;
				break;
			}
			if (input.charAt(0) == 'c')
			{
				choice = true;
				break;
			}
		}
			
			
		return new IPDMove(choice);
	}
	
	public String getID()
	{
		return id;
	}	

	public void update(Move move)
	{
		IPDMove ipdMove = (IPDMove)move;
		if (ipdMove.getPlayer()==this)
		{
			return;
		} // skip
		haveMoved = true;
		prevMove = ipdMove.getMove();

		
	}
	
}
