package tourney;

/**
 * RS: Just a rough sketch for an idea here.
 * 
 * I was thinking that we could add a class (what we had called "package") for
 * sending updates to the players. This would allow us to save annotations for
 * the Move for statistics purposes. We don't want move to have to get overly
 * complicated, and I also foresee some issues with Move security.
 * 
 * Let's say we annotate a Move with a statistic that says
 * "He played his last trump card on turn #7", well I think that wouldn't be
 * something we want to pass to his opponents.
 * 
 * Also this raises an issue of how do we annotate of a Move. If we pass a
 * reference to the last Move, to all of these players, and then annotate it,
 * they would be able to access that. We'd have to make Move cloneable, or use a
 * wrapper design for annotation...
 * 
 * What are your thoughts?
 */
public class Update {
	/** The move the last player made. */
	private Move lastMove;

	public Update(Move move) {
		lastMove = move;
	}

	public Move getLastMove() {
		return lastMove;
	}

	// Children classes can add further information retrieval. ie PokerUpdate
}
