package tourney;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import tourney.loader.Loader;


import countToN.CountToNDataReader;

public class Tournament {
	static Scanner scanner = new Scanner(System.in); // For end of tourney input
	private static HashMap<Class, ArrayList<Class>> gamesToPlayersMap;

	private static int playersPerGame = 2;
	
	private Game game;
	private ArrayList<Player> competitors;
	private int gameIterations;
	
	public Tournament(Game game, ArrayList<Player> competitors, int gameIterations) {
		this.game = game;
		this.competitors = competitors;
		this.gameIterations = gameIterations;
	}
	
	public void runTournament() {
		ArrayList<ArrayList<Player>> combinations = generateCombinationsWithRepetition(
				competitors, game.playersPerGame());
		
		for(ArrayList<Player> combination : combinations) {
			Match match = new Match(game, combination, gameIterations);
			MatchResult matchResult = match.playMatch();
			
			// Handle sending MatchResult somewhere...
		}
		
		// Do we return a tournament result of some sort or handle that here?
	}

	public static void main(String[] args) {
		Game g = null;

		gamesToPlayersMap = null;

		Loader loader = new Loader();
		try {
			gamesToPlayersMap = loader.listGames();
		} catch (TourneyException te) {
			te.printStackTrace();
		}
		System.out.println("Games available: ");

		Class[] gamesArray = gamesToPlayersMap.keySet().toArray(new Class[1]);
		for (int i = 1; i <= gamesArray.length; i++) {
			System.out.println(i + ") " + gamesArray[i - 1].getName());
		}

		System.out.print("Enter the number of your preferred game: ");
		Scanner scan = new Scanner(System.in);
		Class chosenClass = gamesArray[scan.nextInt() - 1];
		ArrayList<Player> players = null;
		try {
			g = loader.loadGame(chosenClass);
			players = loader.loadPlayers(gamesToPlayersMap.get(chosenClass));

		} catch (TourneyException te) {
			te.printStackTrace();
			System.exit(1);
		}
		
		Tournament tourney = new Tournament(g, players, 10);
		tourney.runTournament();
		
		System.out.println("Tournament reached end of main.");
	}

	/**
	 * Purpose: Generate all n-player combinations of the Player list, where n
	 * is the number of players required per game. Players are to play
	 * themselves, order is not significant. For a 3-player game, with players
	 * a, b, c, d, e the set <a,a,a> and <a,a,b> must be generated, but <a,a,b>
	 * and <b,a,a> must not both be generated. We will need this presently. It
	 * will also need improved.
	 */
	public static ArrayList<ArrayList<Player>> generateCombinationsWithRepetition(
			ArrayList<Player> array, int combinationSize) {
		ArrayList<ArrayList<Player>> combinations = new ArrayList<ArrayList<Player>>();

		final int arrayLength = array.size();

		// if combination size is too small
		if (combinationSize < 1) {
			return null;
		}

		// base case
		if (combinationSize == 1) {
			for (Player p : array) {
				ArrayList<Player> singleElement = new ArrayList<Player>();
				singleElement.add(p);
				combinations.add(singleElement);
			}
			return combinations;
		}

		// recurse
		for (int i = 0; i < arrayLength; i++) {
			ArrayList<Player> subset = new ArrayList<Player>();
			for (int j = i; j < arrayLength; j++) {
				subset.add(array.get(j));
			}

			int newComboSize = combinationSize - 1;

			ArrayList<ArrayList<Player>> subsetCombinations = generateCombinationsWithRepetition(
					subset, newComboSize);

			// Haven't had any NPEs from above, so I assume
			// this loop never enters if the subsetCombinations is null
			for (ArrayList<Player> subsetCombo : subsetCombinations) {
				ArrayList<Player> combo = new ArrayList<Player>();
				combo.add(array.get(i));
				combo.addAll(subsetCombo);
				combinations.add(combo);
			}
		}

		return combinations;
	}
}
