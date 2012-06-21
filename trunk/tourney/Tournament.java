package tourney;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import countToN.CountToNDataReader;

public class Tournament
{

	static Scanner scanner = new Scanner(System.in); // For end of tourney input
	
	private static HashMap<Class, ArrayList<Class>> gamesToPlayersMap;

	private static int playersPerGame = 2;

	public static void main(String[] args)
	{
		Game g = null;

		gamesToPlayersMap = null;

		Loader loader = new Loader();
		try
		{
			gamesToPlayersMap = loader.listGames();
		} catch (TourneyException te)
		{
			te.printStackTrace();
		}
		System.out.println("Games available: ");

		Class[] gamesArray = gamesToPlayersMap.keySet().toArray(new Class[1]);
		for (int i = 1; i <= gamesArray.length; i++)
		{
			System.out.println(i + ") " + gamesArray[i - 1].getName());
		}

		System.out.print("Enter the number of your preferred game: ");
		Scanner scan = new Scanner(System.in);
		Class chosenClass = gamesArray[scan.nextInt() - 1];
		ArrayList<Player> players = null;
		try
		{
			g = loader.loadGame(chosenClass);
			players = loader.loadPlayers(gamesToPlayersMap.get(chosenClass));

		} catch (TourneyException te)
		{
			te.printStackTrace();
			System.exit(1);
		}

		Match match = new Match(g, players, 2);

		match.playMatch(); // Currently I wouldn't need its return value directly. Would you?
		
		// Evil guard to avoid mismatched data readers. Let's get rid of this soon.
		if(!(g instanceof countToN.CountToN)) {
			System.out.println("Tournament reached end of main.");
			return;
		}
		
		DataReader dr = new CountToNDataReader(match); // sorry about using game package again
		// I need a match to be passed to me. Would all implementations?
		// We need to think about that.
		
		System.out.println(dr.report());
		
		do {
			System.out.print("\nWould you like to save this data to a file (y/n)? ");
			String input = scanner.next();
			if(input.equalsIgnoreCase("y")) {
				System.out.println("Saving Data...");
				dr.write(); // Could this return a file name? How do we handle that?
				System.out.println("Data saved!");
				break;
			} else if (input.equalsIgnoreCase("n")){
				break;
			} else {
				System.out.println("Invalid input! y or n only");
			}
		} while(true);
		
		do {
			System.out.println("Would you like to view the data in an interactive data explorer (y/n)? ");
			String input = scanner.next();
			if(input.equalsIgnoreCase("y")) {
				System.out.println("Running the interactive data explorer for " + g.getName() + "...");
				dr.run();
				break;
			} else if (input.equalsIgnoreCase("n")){
				break;
			} else {
				System.out.println("Invalid input! y or n only");
			}
		} while(true);
		
		System.out.println("Tournament reached end of main.");
	}

	/**
	 * Purpose: Generate all n-player permutations of the Player list, where n
	 * is the number of players required per game. Players are to play
	 * themselves, order is not significant. For a 3-player game, with players
	 * a, b, c, d, e the set <a,a,a> and <a,a,b> must be generated, but <a,a,b>
	 * and <b,a,a> must not both be generated. We will need this presently. It
	 * will also need improved.
	 */
	// This only works for two-player games now.
	// We should worry about combinations here - consider what happens if we try
	// to play Chinese Checkers, and 20 people submit Players!

	public ArrayList<ArrayList<Player>> permute(List<Player> players)
	{
		ArrayList<ArrayList<Player>> permutations = new ArrayList<ArrayList<Player>>();

		ArrayList<Player> copy = new ArrayList<Player>(players);

		for (Player p : players)
		{
			for (Player q : copy)
			{
				ArrayList<Player> perm = new ArrayList<Player>(playersPerGame);
				perm.add(p);
				perm.add(q);
				permutations.add(perm);
			}
			copy.remove(p);
		}
		return permutations;
	}

}
