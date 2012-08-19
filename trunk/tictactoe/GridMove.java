package tictactoe;

import tourney.Move;

/**
 * 
 * This class represents a Tic-Tac-Toe move. Each move contains information on
 * the player that made it, the grid mark (e.g. X, O), and a x/y-based location
 * (e.g. 0,0). The location is zero based.
 * 
 * @author Ryan Beckett
 */
public class GridMove extends Move
{

    /**
     * Create a new Tic-Tac-Toe move with the specified player, mark, and
     * location.
     */
    public GridMove(GridMark mark, GridLocation location, TicTacToePlayer player)
    {
        annotate("mark", mark.toString());
        annotate("location", location.toString());
        setPlayer(player);
    }

    /**
     * Get the location of this move.
     * 
     * @return The location of the mark placed on the grid as a result of this
     *         move.
     */
    public GridLocation getLocation()
    {
        String[] loc = getAnnotation("location").split(":");
        int x = Integer.parseInt(loc[0]), y = Integer.parseInt(loc[1]);
        return new GridLocation(x, y);
    }

    /**
     * Returns a {@link String} object containing the information for this move.
     * 
     * @return A string representation of this move.
     */
    public String toString()
    {
        return "Location = " + getAnnotation("location") + ", Mark = "
                + getAnnotation("mark") + ", Player = " + getPlayer();
    }
}
