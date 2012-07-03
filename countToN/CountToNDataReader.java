package countToN;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import tourney.DataReader;
import tourney.Player;
import tourney.TournamentResult;

/** Handles getting the appropriate Data from a Match of CountToN */
public class CountToNDataReader implements DataReader {
	private TournamentResult result;
	
	public CountToNDataReader(TournamentResult result) {
		this.result = result;
	}

	@Override
	public String report() {
		StringBuilder sb = new StringBuilder();

		List<Player> players = result.getPlayers();
		List<Player> winners = result.getTournamentWinners();

	//	sb.append(String.format("%d games of CountToN were played between ", result.size()));

		int playerLength = players.size();
		for (int i = 0; i < playerLength; i++) {
			sb.append(players.get(i).getID());
			if (i < playerLength - 2) {
				sb.append(", ");
			} else if (i == playerLength - 2) {
				sb.append(" and ");
			}
		}

		sb.append(".\nThe results are as follows:\n");

		// The Overall Winner(s)
		if (winners.size() == 1) {
			sb.append("The player who won the most games was: ");
			sb.append(winners.get(0).getID());
			sb.append(".\n");
		} else {
			sb.append("There was a draw between players ");
			for (int i = 0; i < winners.size(); i++) {
				sb.append(winners.get(i).getID());
				if (i < winners.size() - 2) {
					sb.append(", ");
				} else if (i == winners.size() - 2) {
					sb.append(" and ");
				}
			}
			sb.append(".");
		}

		/*
		 * add additional Data to the report as desired. Can't think of more for
		 * simple CountToN. Options include: - Average/Most/Least Number of
		 * Moves per Game (not for CountToN) - First blood (in relevant games) -
		 * # of times an event occurred (Such as getting kinged in checkers) -
		 * Total winnings (in betting games) - Anything that can be polled from
		 * annotations.
		 */

		return sb.toString();
	}

	@Override
	public void run() {
		System.out
				.println("The CountToN interactive data explorer is not available");
		// We should only expend effort on these for the Games we are going to
		// actually run for fun.
		// CountToN, while extremely useful for aiding the design, is a joke for
		// collecting data.
	}

	@Override
	public void write() {
		String fileName = "CountToNMatch.txt";
		try {
			BufferedWriter file = new BufferedWriter(new FileWriter(fileName));
			file.write(report()); // for now if we want to save it. Later we can
									// decide on format.
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
