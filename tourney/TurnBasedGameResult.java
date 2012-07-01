package tourney;

import java.util.List;

public class TurnBasedGameResult extends GameResult_NewVersion<Move> {
	public TurnBasedGameResult(List<Player> players) {
		super(players);
	}

	public void add(Move move) {
		super.add(move);

		if (move.getWinners() != null)
			setWinners(move.getWinners());
	}
}