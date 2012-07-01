package tourney;

public class IllegalMoveException extends TourneyException {
	private Player culprit;
	
	/**
	 * Makes a new Exception defining who messed up and in what Game.
	 */
	public IllegalMoveException(Game game, Player culprit) {
		this.culprit = culprit;
		
		StringBuilder sb = new StringBuilder();
		sb.append(culprit.getID());
		sb.append(" has committed an illegal move in ");
		sb.append(game.getName());
		sb.append(".");
		
		message = sb.toString();
	}
	
	/**
	 * Returns a reference to the offending player, so that he may be kicked from the Tournament.
	 */
	public Player getCulprit() {
		return culprit;
	}
}