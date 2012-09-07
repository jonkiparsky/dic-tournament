package tictactoe;

import java.util.Random;

import tourney.MachinePlayer;
import tourney.Move;

/**
 * 
 * An implementation whose strategy is to pick any move at random with uniform
 * probability, without regard to whether the selected move is legal. Thus, this
 * implementation will periodically submit illegal moves. This is intentional,
 * it is intended for testing the framework's handling of illegal moves. 
 * 
 * @author Ryan Beckett/Jon Kiparsky
 */
public class TTT_Player_BUGGY extends TicTacToePlayer implements MachinePlayer
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
        int x = rand.nextInt(grid.length);
        int y = rand.nextInt(grid.length);
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
