package tourney;

/**
 * Handles the basic functionality of storing, getting and setting the Move
 * values. Not sure if we necessarily need this class, but I'm going to go for
 * it. The methods are pretty self explanatory.
 */
public abstract class AbstractMove implements Move {
	private int[] source, destination;

	public int[] getSource() {
		return source;
	}

	public int[] getDestination() {
		return destination;
	}

	public void setSource(int[] source) {
		this.source = source;
	}

	public void setDestination(int[] destination) {
		this.destination = destination;
	}
}