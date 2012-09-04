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
	 GridMark [] flatGrid;



		// define ways to win. We'll interate these looking for must-block moves
		// and winning moves
	int[] [] paths =	{	{0,1,2}, {3,4,5}, {6,7,8},
								{0,3,6}, {1,4,7}, {2,5,8},
								{0,4,8}, {2,4,6}
							};

	int[]  mustBlock;  // a row, column, or diagonal on which the enemy might win
	int[] winningPath; // a row, column, or diagonal on which we might win


	@Override
	public void clearGrid()
	{
		flatGrid = new GridMark[9]; 
	
		for (int i = 0; i <9; i++)
		{
			flatGrid[i] = GridMark.E;
		}		
	}

    /**
     * Select a random move and ensure its legal.
     * 
     * @return The player move.
     */
    @Override
    public Move getMove()
    {
			int cell = -1;
			if (winningPath != null)
			{
				cell = onlyMoveIn(winningPath);
				flatGrid[cell] = GridMark.X;
				winningPath = null;	
				return moveFor(cell);
			}
			if (mustBlock != null) 
			{
				cell = onlyMoveIn(mustBlock);
				flatGrid[cell] = GridMark.X;
				mustBlock = null;
				return moveFor(cell);
			}

			
			for (cell = 0; cell < 9; cell ++)
			{
            if (flatGrid[cell] == GridMark.E)
                break;
			}
			flatGrid[cell] = GridMark.X;
			return new GridMove(playerMark, new GridLocation(cell/3, cell%3), this);
    }


	
	@Override
	public void update (Move move)
	{
		GridLocation loc = ((GridMove) move).getLocation();

		int thisRow = loc.getX();
		int thisCol = loc.getY();


		int flatLoc = thisRow*3+thisCol;
		if (flatGrid[flatLoc] != GridMark.E)
		{
			return;	
		}
		flatGrid[flatLoc] = GridMark.O; 

	
		
		for (int[] path:paths)
		{
			int oCount = 0;
			int xCount = 0;
			for (int i : path)
			{
				if (flatGrid[i] == GridMark.O)
				{
					oCount ++;
				}
				if (flatGrid[i] == GridMark.X)
				{
					xCount ++;
				}
			}

			if (xCount == 2 && oCount == 0)
			{
				winningPath = path;
				break;
			}
			if (oCount == 2 && xCount == 0)
			{
				mustBlock = path;
				break;
			}
			
		}		
	}

	private int onlyMoveIn(int[] path)
	{
		
		for (int cell: path)
		{
			if (flatGrid[cell] == GridMark.E)
			{
				return cell;
			}
		}

		System.out.println("failed in onlyMoveIn()");
		return -1;
	}


	private GridMove moveFor(int cell)
	{
		return new GridMove(playerMark, new GridLocation(cell/3, cell%3), this);
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
