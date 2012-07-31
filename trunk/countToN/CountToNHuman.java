package countToN;

import java.util.Scanner;

import tourney.Move;
import tourney.HumanPlayer;
import tourney.Player;

/**
 * Recommended human implementation for the Count to N game.
 */
public class CountToNHuman 
			extends Player
			implements HumanPlayer 
{		
	static Scanner input = new Scanner(System.in);


	private int count = 0;


	public CountToNHuman() {
	}

	@Override
	public Move getMove() {
		System.out.printf("\nThe current number is: %d\n", count);
		System.out.println("What is the next number?");

		CountMove move = new CountMove(input.nextInt());

		return move;
	}


	@Override
	public void update(Move move) {
		CountMove cMove = (CountMove) move;
		count = cMove.getCount();
	}
}
