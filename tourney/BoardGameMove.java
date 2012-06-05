package tourney;

/**
 * A Move in a typical board game. This can commonly refer to 2 locations where
 * a piece should move from the source location to the destination. Several
 * constructors are provided for different situations, such as 2D and 3D moves.
 */
public class BoardGameMove extends AbstractMove {
	/**
	 * Default constructor. This can be used if you prefer to set the values
	 * manually via methods such as setSourcePoint().
	 */
	public BoardGameMove() {
		setSource(new int[0]);
		setDestination(new int[0]);
	}

	/**
	 * Constructor used to straight up set the source and destination points
	 * from arrays.
	 * 
	 * @param src
	 *            An array representing
	 * @param dest
	 */
	public BoardGameMove(int[] src, int[] dest) {
		setSource(src);
		setDestination(dest);
	}

	/*
	 * The following are convenience methods used to construct the array for you
	 * from 2d or 3d points. It might be easier to understand what we are doing
	 * from setSrcPoint(5,5); than to understand setSource(new int{} { 5, 5 });
	 * 
	 * Feel free to refactor as you want, just putting them in for now.
	 */

	/**
	 * Sets this Move's source location to the point (x, y).
	 */
	public void setSrcPoint(int x, int y) {
		setSource(new int[] { x, y });
	}

	/**
	 * Sets this Move's source location to the 3D point (x, y, z).
	 */
	public void setSrcPoint(int x, int y, int z) {
		setSource(new int[] { x, y, z });
	}

	/**
	 * Sets this Move's destination location to the point (x, y).
	 */
	public void setDestPoint(int x, int y) {
		setDestination(new int[] { x, y });
	}

	/**
	 * Sets this Move's destination location to the 3D point (x, y, z).
	 */
	public void setDestPoint(int x, int y, int z) {
		setDestination(new int[] { x, y, z });
	}
}