package tourney;

/*
 * Previously named 'Board', I think GameState more accurately reflects the
 * responsibilities of this class.
 */

/**
 * A GameState holds the state of the game at any given moment, and it must
 * provide a way for that state to be changed based on a Move object.
 * 
 * It should provide a set of interface methods that allow a Player to access
 * (but not mutate) the inner state of the game, so it may understand how to
 * proceed.
 * 
 * It should also provide a set of interface methods that allow a Player to come
 * up with a Move object based on how the player wants to proceed. For example,
 * if the player wants to move his piece to position (x, y) on the grid, then
 * the interface might provide a method
 * <code>Move getMoveForMoveTo(int x, int y)</code>. This would allow a player
 * to interact with the GameState in such a way that he does not have to try to
 * construct a Move from scratch based on his intentions.
 * 
 * Since the implementation of a GameState will widely vary from game to game,
 * we cannot enforce these interfaces to be implemented, as we do not know the
 * exact contract it they must fulfill. We can only strongly suggest that
 * implementations follow this pattern.
 */

public abstract class GameState implements Cloneable {
	/** Update the inner state of the game based on this Move. */
	public abstract void updateState(Move move);
	
	/** For my idea to clone the board. */
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}