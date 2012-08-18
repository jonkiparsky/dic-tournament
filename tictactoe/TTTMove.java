package tictactoe;

import tourney.Move;

/**
 * 
 * This class represents a TicTacToe move. Each move is decorated with a grid
 * mark (e.g. X, O) and a x/y-based location (e.g. 0,0). The location is zero
 * based.
 * 
 * @author Ryan Beckett
 */
public class TTTMove extends Move
{

    /**
     * Create a new TicTacToe move with the specified mark and location.
     */
    public TTTMove(GridMark mark, GridLocation location, TTTPlayer player)
    {
        annotate("mark", mark.toString());
        annotate("location", location.toString());
        setPlayer(player);
    }

    /**
     * Get the location of this move.
     * 
     * @return A {@link GridLocation} containing the x/y coordinates of the mark
     *         placed on the grid.
     */
    public GridLocation getLocation()
    {
        String[] loc = getAnnotation("location").split(":");
        int x = Integer.parseInt(loc[0]), y = Integer.parseInt(loc[1]);
        return new GridLocation(x, y);
    }

    public String toString()
    {
        return "Location = " + getAnnotation("location") + ", Mark = "
                + getAnnotation("mark") + ", Player = " + getPlayer();
    }
}
