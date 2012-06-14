package tourny;

import java.util.ArrayList;
import java.util.List;

import countToN.CountToN;

public class Tournament {
	public static void main(String[] args) {
		Game g = new CountToN();

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(g.getHumanPlayer());
		players.add(g.getDefaultAIPlayer());

		List<Move> moves = g.play(players);
		
		// get last move
		Move move = moves.get(moves.size() - 1);
		
		// did I win?
		System.out.println("The winner is "+move.getPlayer().getID());
	}
}
