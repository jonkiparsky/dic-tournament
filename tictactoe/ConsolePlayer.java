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
public class ConsolePlayer extends TicTacToePlayer implements HumanPlayer
{

    private Scanner kbd;

    /**
     * Create a new console-based player.
     */
    public ConsolePlayer()
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

        return new GridMove(playerMark, new GridLocation(x, y), this);
    }

    /**
     * Display the game grid to the user via the console.
     */
    private void printGrid()
    {
        printGridHeader();
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
     * Display the grid header.
     */
    private void printGridHeader()
    {
        System.out.println("\n-----------------------------");
        System.out.println("Player " + id + "'s turn. (Your marker is "
                + playerMark + ")");
        System.out.println("-----------------------------");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void signalWinner(boolean isWinner)
    {
        System.out.print("Player " + id + ": ");
        if (isWinner)
            System.out.println("Congratulations. You won!");
        else
            System.out.println("You lost.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void signalDraw()
    {
        System.out.println("Player " + id + ": The game has ended in a draw.");
    }
}
