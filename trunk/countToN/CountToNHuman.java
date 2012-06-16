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

	@Override
	public Move getMove() {
		// This player assumes the state is a CountState
		CountState cState = (CountState) state;

		System.out.printf("\nThe current number is: %d\n", cState.getCount());
		System.out.println("What is the next number?");
		
		CountMove move = CountState.getMoveForNumber( input.nextInt() );
		
		return move;
	}

	public String getID()
	{
		return id;
	}

	@Override
	public void apply(Move move) {
		// TODO Auto-generated method stub
	}
}
