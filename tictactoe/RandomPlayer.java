package tictactoe;

import java.util.Random;

import tourney.MachinePlayer;
import tourney.Move;

/**
 * 
 * An implementation whose strategy is to pick any move at random with uniform
 * probability.
 * 
 * @author Ryan Beckett
 */
public class RandomPlayer extends TicTacToePlayer implements MachinePlayer
{

    /**
     * Generate a random and legal move.
     * 
     * @return The player move.
     */
    @Override
    public Move getMove()
    {
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
