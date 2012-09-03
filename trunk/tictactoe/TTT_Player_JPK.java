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


	public void init()
	{
		
		for (GridMark g : flatGrid)
			g = GridMark.E;

		System.out.println("Completed init");
	}		


    /**
     * Select a random move and ensure its legal.
     * 
     * @return The player move.
     */
    @Override
    public Move getMove()
    {
			System.out.println("Entered getMove()");	
			if (winningPath != null)
			{
				int cell = onlyMoveIn(winningPath);
				flatGrid[cell] = GridMark.X;
			
				return moveFor(cell);
			}
			if (mustBlock != null) 
			{
				int cell = onlyMoveIn(mustBlock);
			flatGrid[cell] = GridMark.X;
			
				return moveFor(cell);
			}
			System.out.println("In  getMove(), past must and winning");	

			
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
			flatGrid[x*3+y] = GridMark.X;
        return new GridMove(playerMark, new GridLocation(x, y), this);
    }


	
	@Override
	public void update (Move move)
	{
			System.out.println("Entered update()");	
		GridLocation loc = ((GridMove) move).getLocation();

		int thisRow = loc.getX();
		int thisCol = loc.getY();

		System.out.println("Rec'd move: "+thisRow +", "+thisCol);

		int flatLoc = thisRow*3+thisCol;
		if (flatGrid[flatLoc] == GridMark.E)
			flatGrid[flatLoc] = GridMark.O; 
		for (int[] path:paths)
		{
			int oCount = 0;
			int xCount = 0;
			for (int i : path)
			{
				if (flatGrid[i] == GridMark.O)
				{
					System.out.println (i+" is an O");
					oCount ++;
				}
				if (flatGrid[i] == GridMark.X)
				{
					System.out.println (i+" is an X");
					xCount ++;
				}
			}

			if (xCount == 2)
			{
				winningPath = path;
				System.out.print("winning path found");
				printPath(path);
				break;
			}
			if (oCount == 2)
			{
				mustBlock = path;
				System.out.print("must block path found:");
				printPath(path);
				break;
			}
			
		}		
	}

private void printPath(int[] path)
{
				for (int j : path)
				{	
					System.out.print(j);
				}
				System.out.println();

}

	private int onlyMoveIn(int[] path)
	{
		System.out.println("entered onlyMoveIn()");
		
		for (int cell: path)
		{
			System.out.println("checking "+cell);
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
