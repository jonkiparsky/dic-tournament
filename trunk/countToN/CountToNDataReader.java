package countToN;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tourney.DataReader;
import tourney.Match;
import tourney.MatchResult;
import tourney.Move;
import tourney.Player;

/** Handles getting the appropriate Data from a Match of CountToN */
public class CountToNDataReader implements DataReader
{
	private MatchResult result;

	public CountToNDataReader(MatchResult result)
	{
		this.result = result;
	}

	/**
	 * Finds the player(s) who won the most frequently. If it is a draw, there
	 * will be more than one player in winners.
	 */
	private void calculateWinners()
	{
		List<Player> players = match.getPlayers();

		int[] winnerTally = new int[players.size()];

		for (ArrayList<Move> gameRecord : result)
		{
			Move move = gameRecord.get(gameRecord.size() - 1);
			ArrayList<Player> gameWinners = move.getWinners(); // *** not yet
																// implemented
																// in
																// Move.
			for (Player winner : gameWinners)
			{
				winnerTally[players.indexOf(winner)]++;
			}
		}

		int max = 0;
		for (int i = 0; i < winnerTally.length; i++)
		{
			if (winnerTally[i] > max)
			{
				max = winnerTally[i];
			}
		}

		for (int i = 0; i < winnerTally.length; i++)
		{
			if (winnerTally[i] == max)
			{
				winners.add(players.get(i));
			}
		}
	}

	@Override
	public String report()
	{
		StringBuilder sb = new StringBuilder();

		ArrayList<ArrayList<Move>> gameRecords = match.getMatchResult();
		List<Player> players = match.getPlayers();

		sb.append(String.format("%d games of CountToN were played between ",
				gameRecords.size()));

		int playerLength = players.size();
		for (int i = 0; i < playerLength; i++)
		{
			sb.append(players.get(i).getID());
			if (i < playerLength - 2)
			{
				sb.append(", ");
			} else if (i == playerLength - 2)
			{
				sb.append(" and ");
			}
		}

		sb.append(".\nThe results are as follows:\n");

		// The Overall Winner(s)
		if (winners.size() == 1)
		{
			sb.append("The player who won the most games was: ");
			sb.append(winners.get(0).getID());
			sb.append(".\n");
		} else
		{
			sb.append("There was a draw between players ");
			for (int i = 0; i < winners.size(); i++)
			{
				sb.append(winners.get(i).getID());
				if (i < winners.size() - 2)
				{
					sb.append(", ");
				} else if (i == winners.size() - 2)
				{
					sb.append(" and ");
				}
			}
			sb.append(".");
		}

		/*
		 * add additional Data to the report as desired. Can't think of more for
		 * simple CountToN. Options include:
		 * - Average/Most/Least Number of Moves per Game (not for CountToN)
		 * - First blood (in relevant games)
		 * - # of times an event occurred (Such as getting kinged in checkers)
		 * - Total winnings (in betting games)
		 * - Anything that can be polled from annotations.
		 */

		return sb.toString();
	}

	@Override
	public void run()
	{
		System.out.println("The CountToN interactive data explorer is not available");
		// We should only expend effort on these for the Games we are going to
		// actually run for fun.
		// CountToN, while extremely useful for aiding the design, is a joke for
		// collecting data.
	}

	@Override
	public void write()
	{
		String fileName = "CountToNMatch_" + instanceCounter + ".txt";
		try
		{
			BufferedWriter file = new BufferedWriter(new FileWriter(fileName));
			file.write(report()); // for now if we want to save it. Later we can
									// decide on format.
			file.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}