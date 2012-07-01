package prisoner;

import tourney.Player;
import tourney.HumanPlayer;
import tourney.Move;
import java.util.Random;
import java.util.Scanner;


public class IPD_Human 
		extends IPD_Player 
		implements HumanPlayer
{
	private String id = "IPD_Human";
	private static int instanceCounter = 0;
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
		if (previousMove != null)
			System.out.println("Your opponent's previous move was to "+
				(previousMove.getPlay()? "cooperate" : "defect"));
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
			
			
		return new IPD_Move(choice);
	}
	
	public String getID()
	{
		return id;
	}	

	public void update(Move move)
	{
		previousMove = (IPD_Move)move;

		
	}
	
}
