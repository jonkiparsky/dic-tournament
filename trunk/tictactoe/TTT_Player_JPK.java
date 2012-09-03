package tictactoe;

import java.util.Random;

import tourney.MachinePlayer;
import tourney.Move;

/**
 * 
 * An AI-based TicTacToe player. Currently, the AI engine simply selects random
 * moves and ensures they are legal.
 * 
 * 
 * @author Jon Kiparsky
 */
public class TTT_Player_JPK extends TicTacToePlayer implements MachinePlayer
{
	GridMark [] flatGrid = new GridMark[9];


		// define ways to win. We'll interate these looking for must-block moves
		// and winning moves
	int[] [] paths =	{	{0,1,2}, {3,4,5}, {6,7,8},
								{0,3,6}, {1,4,7}, {2,5,8},
								{0,4,8}, {3,4,6}
							};

	int[]  mustBlock;
	int[] winningPath;

	boolean firstMove = true;

	public void init()
	{
		for (GridMark g : flatGrid)
			g = GridMark.E;
	}		


    /**
     * Select a random move and ensure its legal.
     * 
     * @return The player move.
     */
    @Override
    public Move getMove()
    {

			if (firstMove) 
				return new GridMove(playerMark, new GridLocation(1,1), this);
			if (winningPath != null)
				return (onlyMoveIn(winningPath));
			if (mustBlock != null) 
				return (onlyMoveIn(mustBlock));
			
        Random rand = new Random();
        boolean legalMove = false;
        int x = -1, y = -1;
        while (!legalMove)
        {
            x = rand.nextInt(grid.length);
            y = rand.nextInt(grid.length);
            if (isMoveLegal(x, y))
                break;
        }
        return new GridMove(playerMark, new GridLocation(x, y), this);
    }


	
	@Override
	public void update (Move move)
	{
		GridLocation loc = ((GridMove) move).getLocation();

		int thisRow = loc.getX();
		int thisCol = loc.getY();

		int flatLoc = thisRow*3+thisCol;
		flatGrid[flatLoc] = GridMark.O; 
		for (int[] path:paths)
		{
			int oCount = 0;
			int xCount = 0;
			for (int i : path)
			{
				if (flatGrid[i] == GridMark.O)
					oCount ++;
				if (flatGrid[i] == GridMark.X)
					xCount ++;
			
			}

			if (xCount == 2)
			{
				winningPath = path;
				break;
			}
			if (oCount == 2)
			{
				mustBlock = path;
				break;
			}
			
		}		
	}



	private GridMove onlyMoveIn(int[] path)
	{
		for (int cell: path)
		{
			if (flatGrid[cell] == GridMark.E)
			{
				return new GridMove(playerMark, 
						new GridLocation(cell/3, cell%3), this);
			}
		}
		return null;
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public void signalWinner(boolean isWinner)
    {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void signalDraw()
    {
    }

}
