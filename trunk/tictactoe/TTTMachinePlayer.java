package tictactoe;

import java.util.Random;

import tourney.MachinePlayer;
import tourney.Move;

/**
 * 
 * An AI-based TicTacToe player. Currently, the AI engine simply selects random
 * moves and ensures they are legal.
 * 
 * @author Ryan Beckett
 */
public class TTTMachinePlayer extends TTTPlayer implements MachinePlayer
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
        return new TTTMove(playerMark, new GridLocation(x, y), this);
    }

    @Override
    public void signalWinner(boolean isWinner)
    {
    }

    @Override
    public void signalDraw()
    {
    }

}
