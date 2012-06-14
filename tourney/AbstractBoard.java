package tourney;

/**
*	Possibly mis-named, a Board represents the state of play at a moment. A Board
*	object is the mechanism by which a Game communicates to a Player the
*	situation to which that Player must respond, and it is that Board which the
*	Game must use to evaluate the Player's response. 
*	The exact representation of the Board is of course dependent on the game
*	being represented and the whims of the implementers; therefore the Board
*	interface specifies very little. 
*	It may be wise to create sub-interfaces for more particular forms, for
*	example grid-based games.
*/


public abstract class AbstractBoard implements Board
{

	public static final  int ILLEGAL = 1;
	public static final int GAME_OVER = 2;
	public static final int GAME_CONTINUE = 3;


	private int state;
	private Game game;
	private boolean okayGo;		

	private Player winner;

	public AbstractBoard(Game game)
	{
		this.game = game;
		state= GAME_CONTINUE;
		winner = null;
	}




	/**
	 * Pre: The move is a legal move for this board. Changes the inner state of
	 * the Board, based on the passed move.
	 * 
	 * Sheph: Should this method check for legality? Or should that be the
	 * Game's responsibility before calling process? I'm going for Game's
	 * responsbility for now

		+ JPK: With the current implementation, legality again resides in the
		+	Board. We should provide a method to check, though, and smart Players
		+ will call that method to verify their moves
	 */
	public void submit(Move move, Player player)
	{
	}

	public abstract void apply (Move move, Player player);

	public int state()
	{
		return state;
	}

	private void setState(int state)
	{
		this.state = state;
		
	}
	public boolean gameOver()
	{
		return state == GAME_OVER || state == ILLEGAL;
	}

	/**
	 * Resets the board to empty, so that a new game can be played on it from
	 * the beginning.
	 */
	public abstract void reset();

	public Player winner()
	{
		return winner;
	}
}
