package tourny;

import java.util.ArrayList;
import java.util.List;

import countToN.CountToN;

public class Tournament {
	public static void main(String[] args) {
		Game g = new CountToN();

		ArrayList<Player> players = new ArrayList<Player>();
		Player jon = g.getDefaultAIPlayer();
		Player sheph = g.getDefaultAIPlayer();
		players.add(jon);
		players.add(sheph);

		List<Move> moves = g.play(players);
		
		// get last move
		Move move = moves.get(moves.size() - 1);
		
		// did I win?
		if (move.getPlayer() == sheph) {
			System.out.println("Sheph wins!");
		}
	}
}