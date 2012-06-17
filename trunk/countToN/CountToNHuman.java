package countToN;

import java.util.Scanner;

import tourney.GameState;
import tourney.Move;
import tourney.Player;
import tourney.Update;

/**
 * Recommended human implementation for the Count to N game.
 */
public class CountToNHuman implements Player {
	static Scanner input = new Scanner(System.in);

	private String id = "C2N_Human_Player";
	private int currentCount = 0;

	@Override
	public Move getMove() {
		// This player assumes the state is a CountState

		System.out.printf("\nThe current number is: %d\n", currentCount);

		System.out.println("What is the next number?");
		
		CountMove move = new CountMove( input.nextInt() );
		
		return move;
	}

	public String getID()
	{
		return id;
	}

	@Override
	public void update(Move move) {
		CountMove cMove = (CountMove)move;
		currentCount = cMove.getCount();
	}
}
