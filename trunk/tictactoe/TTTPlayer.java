package tictactoe;

import tourney.Move;
import tourney.Player;

/**
 * 
 * A base class for a TicTacToe player.
 * 
 * @author Ryan Beckett
 */
public abstract class TTTPlayer extends Player
{

    protected GridMark[][] grid;
    protected GridMark playerMark;
    protected int playerId;
    private static int playerCount;

    /**
     * Construct a TicTacToe player and let the specified
     * <code>playerMark</code> designate its grid marker (e.g. X, O).
     */
    protected TTTPlayer()
    {
        playerId = playerCount++;
        this.grid = new GridMark[3][3];
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
                grid[i][j] = GridMark.E;
        }
        assignMarker();
    }

    /**
     * Assign a marker (e.g. X, O) to this player.
     */
    private void assignMarker()
    {
        if (playerId % 2 == 0)
            playerMark = GridMark.X;
        else
            playerMark = GridMark.O;
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
     * Update the state of the grid with the new move. Note that the provided
     * move may be its own or its opponent's.
     */
    @Override
    public void update(Move move)
    {
        GridLocation loc = ((TTTMove) move).getLocation();
        grid[loc.getX()][loc.getY()] = Enum.valueOf(GridMark.class,
                move.getAnnotation("mark"));
    }

    /**
     * Return the next move for this player. This method should be overriden and
     * specialized for its extending class.
     * 
     * @return The player move.
     */
    @Override
    public abstract Move getMove();

    /**
     * Alert the user that a winner has been determined.
     * 
     * @param isWinner
     *            A flag that should be set to <code>true</code> if this user
     *            won the game, otherwise it's set to <code>false</code>.
     */
    public abstract void signalWinner(boolean isWinner);

    /**
     * Alert the user that a draw has occurred (e.g. The grid is full).
     * 
     */
    public abstract void signalDraw();

    /**
     * Get the designated grid mark assigned to this player (e.g. X, O).
     * 
     * @return The player's mark.
     */
    public GridMark getPlayerMark()
    {
        return playerMark;
    }

    /**
     * Get the id of this player.
     * 
     * @return A unique id for this player.
     */
    public int getPlayerId()
    {
        return playerId;
    }

}
