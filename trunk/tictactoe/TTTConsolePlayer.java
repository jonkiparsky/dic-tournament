package tictactoe;

import java.util.Scanner;

import tourney.HumanPlayer;
import tourney.Move;

/**
 * 
 * A console-based TicTacToe player.
 * 
 * @author Ryan Beckett
 */
public class TTTConsolePlayer extends TTTPlayer implements HumanPlayer
{

    private Scanner kbd;

    /**
     * Create a new console-based player.
     */
    public TTTConsolePlayer()
    {
        kbd = new Scanner(System.in);
    }

    /**
     * Show the user the current state of the grid, and prompt them for the
     * location of their move. If the user enters an invalid location (e.g. off
     * the board or atop an opponent piece), the user is re-prompted.
     * 
     * @return The user {@link Move}.
     */
    @Override
    public Move getMove()
    {
        printGrid();
        int x = 0, y = 0;
        while (true)
        {
            System.out.println("Enter row & column (e.g. 1,3): ");
            String[] coords = kbd.nextLine().trim().split("\\s*,\\s*");
            try
            {
                if (coords.length != 2)
                    throw new NumberFormatException();
                x = Integer.parseInt(coords[0]) - 1;
                y = Integer.parseInt(coords[1]) - 1;
            } catch (NumberFormatException e)
            {
                System.out
                        .println("Please enter the location using the specified format.");
                continue;
            }
            if ((x < 0 || x > 2) || (y < 0 || y > 2) || !isMoveLegal(x, y))
                System.out.println("That's not a valid location. Try again.");
            else
                break;
        }

        return new TTTMove(playerMark, new GridLocation(x, y), this);
    }

    /**
     * Display the game grid to the user via the console.
     */
    private void printGrid()
    {
        System.out.println("\n-----------------------------");
        System.out.println("Player " + playerId + "'s GRID (Your marker is "
                + playerMark + ")");
        System.out.println("-----------------------------");
        for (GridMark[] row : grid)
        {
            System.out.print("|");
            for (GridMark mark : row)
            {
                System.out.print(mark + "|");
            }
            System.out.println("\n-------");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void signalWinner(boolean isWinner)
    {
        System.out.print("Player " + playerId + ": ");
        if (isWinner)
            System.out.println("Congratulations. You won!");
        else
            System.out.println("You lost.");
    }

    /**
     * {@inheritDoc}
     */
    public void signalDraw()
    {
        System.out.println("Player " + playerId
                + ": The game has ended in a draw.");
    }

}
