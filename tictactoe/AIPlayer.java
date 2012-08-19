package tictactoe;

import java.util.Random;

import tourney.MachinePlayer;
import tourney.Move;

/**
 * 
 * An AI-based TicTacToe player. Currently, the AI engine simply selects random
 * moves and ensures they are legal.
 * 
 * TODO: Finish building the AI engine.
 * 
 * @author Ryan Beckett
 */
public class AIPlayer extends TicTacToePlayer implements MachinePlayer
{

    /**
     * Select a random move and ensure its legal.
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
