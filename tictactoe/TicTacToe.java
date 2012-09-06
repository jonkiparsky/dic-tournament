package tictactoe;

import tourney.DataReader;
import tourney.Move;
import tourney.Player;
import tourney.TournamentResult;
import tourney.TurnBasedGame;

/**
 * 
 * A console-based TicTacToe game where humans can interactively play against
 * other humans or machine-based implementations.
 * 
 * @author Ryan Beckett
 */
public class TicTacToe extends TurnBasedGame
{

    private TicTacToePlayer winner;
    private GridMark[][] grid;

    /**
     * {@inheritDoc}
     */
    protected void init()
    {
        super.init();
        System.out.println("\nInitializing new game...");
        winner = null;
        grid = new GridMark[3][3];
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
                grid[i][j] = GridMark.E;
        }
        for (Player p : activePlayers)
            ((TicTacToePlayer) p).init();
        ((TicTacToePlayer) activePlayers.get(0)).setMarker(GridMark.X);
        ((TicTacToePlayer) activePlayers.get(0)).setId(0);
        ((TicTacToePlayer) activePlayers.get(1)).setMarker(GridMark.O);
        ((TicTacToePlayer) activePlayers.get(1)).setId(1);
    }

    /**
     * Update the state of the game board with the new move.
     */
    @Override
    protected void process()
    {
        super.process();
        GridLocation loc = ((GridMove) move).getLocation();
        grid[loc.getX()][loc.getY()] = Enum.valueOf(GridMark.class,
                move.getAnnotation("mark"));
    }

    /**
     * Determines whether the move occupies an empty space.
     * 
     * @param move
     *            The player move.
     * @return Returns <code>true</code> if the move is legal, otherwise returns
     *         <code>false</code>.
     */
    @Override
    protected boolean isLegal(Move move)
    {
        GridLocation loc = ((GridMove) move).getLocation();
        return grid[loc.getX()][loc.getY()] == GridMark.E;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean keepGoing()
    {
        if (winnerExists())
        {
            winner.signalWinner(true);
            getOpponent(winner).signalWinner(false);
            return false;
        } else if (gridFull())
        {
            for (Player p : activePlayers)
                ((TicTacToePlayer) p).signalDraw();
            return false;
        } else
            return true;
    }

    /**
     * Determine whether either player has succeeded in placing three marks in
     * successive vertical, horizontal, or vertical positions.
     * 
     * @return Returns <code>true</code> if either player was successful,
     *         otherwise returns <code>false</code>.
     */
    private boolean winnerExists()
    {
        return horizontalWin() || verticalWin() || diagonalWin();
    }

    /**
     * Check whether either player has won in either diagonal.
     * 
     * @return Returns <code>true</code> if either player has won, otherwise
     *         returns <code>false</code>.
     */
    private boolean diagonalWin()
    {
        boolean upward_success = true, downward_success = true;
        GridMark upward_first = grid[0][0], downward_first = grid[grid.length - 1][0];
        if (upward_first == GridMark.E)
            upward_success = false;
        if (downward_first == GridMark.E)
            downward_success = false;
        for (int i = 1, k = i, j = 1; i < grid.length; i++, k--, j++)
        {
            if (grid[i][j] != upward_first)
                upward_success = false;
            if (grid[k][j] != downward_first)
                downward_success = false;
        }
        if (upward_success)
        {
            setWinnerByMark(upward_first);
            return true;
        } else if (downward_success)
        {
            setWinnerByMark(downward_first);
            return true;
        } else
            return false;
    }

    /**
     * Check whether either player has won in any row.
     * 
     * @return Returns <code>true</code> if either player has won, otherwise
     *         returns <code>false</code>.
     */
    private boolean horizontalWin()
    {
        for (int i = 0; i < grid.length; i++)
        {
            boolean success = true;
            GridMark first = grid[i][0];
            if (first == GridMark.E)
                continue;
            for (int j = 1; j < grid[i].length; j++)
                if (grid[i][j] != first)
                    success = false;
            if (success)
            {
                setWinnerByMark(first);
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether either player has won in any column.
     * 
     * @return Returns <code>true</code> if either player has won, otherwise
     *         returns <code>false</code>.
     */
    private boolean verticalWin()
    {
        for (int i = 0; i < grid[0].length; i++)
        {
            boolean success = true;
            GridMark first = grid[0][i];
            if (first == GridMark.E)
                continue;
            for (int j = 1; j < grid.length; j++)
                if (grid[j][i] != first)
                    success = false;
            if (success)
            {
                setWinnerByMark(first);
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether the grid is completely full with marks.
     * 
     * @return Returns <code>true</code> if the grid is full, otherwise returns
     *         <code>false</code>.
     */
    private boolean gridFull()
    {
        for (GridMark[] row : grid)
            for (GridMark mark : row)
                if (mark == GridMark.E)
                    return false;
        return true;
    }

    /**
     * Set the game winner as the player assigned to the given {@link GridMark}.
     * 
     * @param mark
     *            The grid mark.
     * 
     */
    private void setWinnerByMark(GridMark mark)
    {
        TicTacToePlayer p = getPlayerByMark(mark);
        winner = p;
        gameResult.setWinner(p);
    }

    /**
     * Get the player who is assigned the given {@link GridMark}.
     * 
     * @param mark
     *            The grid mark.
     * @return The assigned player.
     */
    private TicTacToePlayer getPlayerByMark(GridMark mark)
    {
        TicTacToePlayer p1 = (TicTacToePlayer) activePlayers.get(0);
        TicTacToePlayer p2 = (TicTacToePlayer) activePlayers.get(1);
        return (p1.getPlayerMark().equals(mark)) ? p1 : p2;
    }

    /**
     * Get the opposing player of the specified {@link TicTacToePlayer}. This
     * mainly serves as a helper method.
     * 
     * @param player
     *            The player whose opponent is to be found.
     * @return The opponent.
     */
    private TicTacToePlayer getOpponent(TicTacToePlayer player)
    {
        Player first = activePlayers.get(0);
        return (TicTacToePlayer) ((first == player) ? activePlayers.get(1)
                : first);
    }

    @Override
    public Player getDefaultAIPlayer()
    {
        return new RandomPlayer();
    }

    @Override
    public Player getHumanPlayer()
    {
        return new ConsolePlayer();
    }

    @Override
    public DataReader getDataReader(TournamentResult result)
    {
        return new GameReport(result);
    }

    @Override
    public String getName()
    {
        return "Tic Tac Toe";
    }

    @Override
    public String getAuthor()
    {
        return "Ryan Beckett";
    }

    @Override
    public int playersPerGame()
    {
        return 2;
    }

}
