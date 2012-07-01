package tourney;

public class TourneyException extends Exception {
	protected String message;
	
	public TourneyException() {
		message = "";
	}
	
	public TourneyException(String msg) {
		this.message = msg;
	}
	
	public String getMessage() {
		return message;
	}
}