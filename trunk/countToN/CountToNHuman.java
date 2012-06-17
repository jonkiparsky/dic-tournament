package countToN;

import java.util.Scanner;

import tourney.Move;
import tourney.Player;

/**
 * Recommended human implementation for the Count to N game.
 */
public class CountToNHuman implements Player {
	static Scanner input = new Scanner(System.in);

	private String id = "C2N_Human_Player";
	
	private int count = 0;

	@Override
	public Move getMove() {
		System.out.printf("\nThe current number is: %d\n", count);
		System.out.println("What is the next number?");
		
		CountMove move = new CountMove(input.nextInt());
		
		return move;
	}

	public String getID()
	{
		return id;
	}

	@Override
	public void update(Move move) {
		CountMove cMove = (CountMove)move;
		count = cMove.getCount();
	}
}
