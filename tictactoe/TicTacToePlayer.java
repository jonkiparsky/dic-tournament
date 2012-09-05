package tictactoe;

import tourney.Move;
import tourney.Player;

/**
 * 
 * A base class for a Tic-Tac-Toe player implementation.
 * 
 * @author Ryan Beckett
 */
public abstract class TicTacToePlayer extends Player
{

    protected GridMark[][] grid;
    protected GridMark playerMark;
    protected int id;

    /**
     * Construct a Tic-Tac-Toe player.
     */
    public TicTacToePlayer()
    {
        this.grid = new GridMark[3][3];
        clearGrid();
    }

    /**
     * Clear this player's grid.
     */
    public void clearGrid()
    {
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
                grid[i][j] = GridMark.E;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
	public void init() { // added to fit into the framework's specifications
		clearGrid();
	}

    /**
     * Determine whether the move occupies an empty space.
     * 
     * @param x
     *            The x coordinate of the move location.
     * @param y
     *            The y coordinate of the move location.
     * @return Returns <code>true</code> if the move is legal, otherwise returns
     *         <code>false</code>.
     */
    protected boolean isMoveLegal(int x, int y)
    {
        return grid[x][y] == GridMark.E;
    }

    /**
     * Update the state of the grid with the new move information. The move may
     * be its own or its opponent's.
     */
    @Override
    public void update(Move move)
    {
        GridLocation loc = ((GridMove) move).getLocation();
        grid[loc.getX()][loc.getY()] = Enum.valueOf(GridMark.class,
                move.getAnnotation("mark"));
    }

    /**
     * Return the next move for this player. This method should be overriden and
     * specialized for its extending class.
     * 
     * @return The player's move.
     */
    @Override
    public abstract Move getMove();

    /**
     * Alert the user that a winner has been determined.
     * 
     * @param isWinner
     *            A flag that's set to <code>true</code> if this user won the
     *            game, otherwise it's set to <code>false</code>.
     */
    public abstract void signalWinner(boolean isWinner);

    /**
     * Alert the user that a draw has occurred. A draw will occur when the grid
     * is full.
     * 
     */
    public abstract void signalDraw();

    /**
     * Get this player's grid mark.
     * 
     * @return The grid mark.
     */
    public GridMark getPlayerMark()
    {
        return playerMark;
    }

    /**
     * Assign a grid marker to this player.
     * 
     * @param mark
     *            The grid mark.
     */
    public void setMarker(GridMark mark)
    {
        playerMark = mark;
    }

    /**
     * Get this player's unique id.
     * 
     * @return The id as an integer value.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Set this player's id. The id must be unique.
     * 
     * @param id
     *            The id.
     */
    public void setId(int id)
    {
        this.id = id;
    }

}
