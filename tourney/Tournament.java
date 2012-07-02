package tourney;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import tourney.loader.Loader;
import tourney.loader.ClassFilter;
import tourney.loader.IsHumanPlayerFilter;
import tourney.loader.IsPlayerFilter;
import tourney.loader.IsMachinePlayerFilter;
import countToN.CountToNDataReader;

public class Tournament {
	static Scanner scanner = new Scanner(System.in); // For end of tourney input
	private static HashMap<Class, ArrayList<Class>> gamesToPlayersMap;

	private static int playersPerGame = 2;
	private static Loader loader;	
	private Game game;
	private ArrayList<Player> competitors;
	private int gameIterations;
	
	public Tournament(Game game, ArrayList<Player> competitors, 
			int gameIterations) {
		this.game = game;
		this.competitors = competitors;
		this.gameIterations = gameIterations;
	}
	
	public void runTournament() {
		ArrayList<ArrayList<Player>> combinations = 
				generateCombinationsWithRepetition(
					competitors, game.playersPerGame());
		
		for(ArrayList<Player> combination : combinations) {
			Match match = new Match(game, combination, gameIterations);
			try {
				MatchResult matchResult = match.playMatch();
			} catch (IllegalMoveException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GameExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Handle sending MatchResult somewhere...
		}
		
		// Do we return a tournament result of some sort or handle that here?
	}

	public static void main(String[] args) {
		Game game= null;
		Class[] gamesArray = null;
		loader = new Loader();
		try {
			gamesArray = loader.listGames();
		} catch (TourneyException te) {
			te.printStackTrace();
		}
		System.out.println("Games available: ");

		for (int i = 1; i <= gamesArray.length; i++) {
			System.out.println(i + ") " + gamesArray[i - 1].getName());
		}

		System.out.print("Enter the number of your preferred game: ");
		Class chosenGame = gamesArray[scanner.nextInt() - 1];

		int matchType = choosePlayerSet();	
		ArrayList<Player> players = loadChosenPlayerSet(matchType, chosenGame);
		game = loadChosenGame(chosenGame);

		
	// dispatch code should be in a method, but we don't refactor until we have
	// working code

		if (matchType == 1) {
		
			Tournament tourney = new Tournament(game, players, 10);
			tourney.runTournament();
		}
		
		if (matchType == 2) {
			MatchResult m = playHumanPlayers(game, players);
		}	

		System.out.println("Tournament reached end of main.");
	}

	private static MatchResult playHumanPlayers(Game game, 
				ArrayList <Player> players)
	{
		if (players.size() == 0)
		{
			System.out.println("I'm sorry, I can't find any interactive players "	
				+"for that game.");
			return null;
		}

		if (players.size() == 1)
		{
			System.out.println("There is just exactly one interactive player " +
				"for that game. I'll load up a game for you.");
			players.add(loader.getNewInstance(players.get(0)));
			Match match = new Match(game, players);
			MatchResult result = null;
			try{
			result = match.playMatch();
			}
			catch (IllegalMoveException ime)
			{
				System.out.println("Sorry, that was a bad move.");
				ime.printStackTrace();
					// fortunately, this is not meant for playing interactively!
			}
			catch (GameExecutionException gee)
			{
				System.out.println("I'm sorry, the game has screwed up.");
				gee.printStackTrace();
			}
			return result;
		}
		else
		{
			System.out.println("There are multiple interactive Players for that " +
				"game. I'll have to make you pick one.");
			return null;
		}
	}

	/**
	 * Purpose: Generate all n-player combinations of the Player list, where n
	 * is the number of players required per game. Players are to play
	 * themselves, order is not significant. For a 3-player game, with players
	 * a, b, c, d, e the set <a,a,a> and <a,a,b> must be generated, but <a,a,b>
	 * and <b,a,a> must not both be generated. We will need this presently. It
	 * will also need improved.
	 */
	public static ArrayList<ArrayList<Player>> 
				generateCombinationsWithRepetition(
					ArrayList<Player> array, int combinationSize) {


		ArrayList<ArrayList<Player>> combinations = 
					new ArrayList<ArrayList<Player>>();

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

			ArrayList<ArrayList<Player>> subsetCombinations = 
					generateCombinationsWithRepetition(
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


	private static int choosePlayerSet()
   {
      System.out.println("Do you want to: ");
      System.out.println(" 1) load and run a tournament of machine players?");
      System.out.println(" 2) load Human Player only (to play both sides " +
            "interactively?");
      System.out.println(" 3) load Human Player and play against a selected " +
            "machine player?");
      System.out.println(" 4) observe one game played by machines? ");
		int matchType = scanner.nextInt();
		return matchType;

	}
	private static ArrayList<Player> loadChosenPlayerSet(int matchType,
			Class chosenGame)
	{
		ClassFilter playerFilter = null;
		if (matchType == 1)
			playerFilter = new IsMachinePlayerFilter();
		if (matchType == 2)
			playerFilter = new IsHumanPlayerFilter();
		if	(matchType == 3)
			playerFilter = new IsPlayerFilter();
		if (matchType == 4)
			playerFilter = new IsMachinePlayerFilter();
					
		ArrayList<Player> players = null;
		try {
			players = loader.loadPlayers(chosenGame, playerFilter);

		} catch (TourneyException te) {
			te.printStackTrace();
			System.exit(1);
		}

		return players;
	}

	private static Game loadChosenGame(Class chosenGame)
	{

		Game game = null;
		try {
			game = loader.loadGame(chosenGame);

		} catch (TourneyException te) {
			te.printStackTrace();
			System.exit(1);
		}

		return game;
	}
}
