package tourney;

public class GameExecutionException extends TourneyException {
	public GameExecutionException(String msg) {
		message = msg;
	}
}