package tictactoe;

/**
 * 
 * An coordinate-based grid location.
 * 
 * @author Ryan Beckett
 */
public class GridLocation
{

    private int x, y;

    /**
     * Create a new grid location from the specified <code>x</code> and
     * <code>y</code> coordinates.
     * 
     * @param x
     *            The <code>x</code> coordinate.
     * @param y
     *            The <code>y</code> coordinate.
     */
    public GridLocation(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the <code>x</code> component of the location.
     * 
     * @return The integer value of <code>x</code>.
     */
    public int getX()
    {
        return x;
    }

    /**
     * Set the <code>x</code> component of the location.
     * 
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * Get the <code>y</code> component of the location.
     * 
     * @return The integer value of <code>y</code>.
     */
    public int getY()
    {
        return y;
    }

    /**
     * Set the <code>y</code> component of the location.
     * 
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * Returns a {@link String} object containing the information for this
     * location.
     * 
     * @return A string representation of this location.
     */
    public String toString()
    {
        return x + ":" + y;
    }
}