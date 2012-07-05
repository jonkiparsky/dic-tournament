package tourney;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tourney.loader.Loader;
import tourney.loader.ClassFilter;
import tourney.loader.IsHumanPlayerFilter;
import tourney.loader.IsPlayerFilter;
import tourney.loader.IsMachinePlayerFilter;

public class Tournament {
	static Scanner scanner = new Scanner(System.in); // For end of tourney input
	private static Loader loader;	
	private Game game;
	private ArrayList<Player> competitors;
	private int gameIterations;
	private TournamentResult tourneyResult;
	
	public Tournament(Game game, ArrayList<Player> competitors, 
			int gameIterations) {
		this.game = game;
		this.competitors = competitors;
		this.gameIterations = gameIterations;
		
	}
	
	public TournamentResult runTournament() {
		List<List<Player>> combinations = Combinations.generateCombinations(
				competitors, game.playersPerGame(), true);
		tourneyResult = new TournamentResult(competitors);
		
		for(List<Player> combination : combinations) {
			// check for disqualified players
			boolean disqualifiedPlayer = false;
			for(Player player : combination) {
				if(!competitors.contains(player)) {
					disqualifiedPlayer = true;
				}
			}
			if(disqualifiedPlayer) {
				continue;
			}
			
			Match match = new Match(game, combination, gameIterations);
			MatchResult matchResult = null;
			try {
				matchResult = match.playMatch();
			} catch (IllegalMoveException ime) {
				// disqualify the player and move on
				disqualify(ime.getCulprit());
			} catch (GameExecutionException gee) {
				gee.printStackTrace();
			}
			
			tourneyResult.add(matchResult);
		}
		
		return tourneyResult;
	}
	
	/**
	 * Disqualify a player from the tournament. For now we just kick him out and
	 * no future matches with him involved are played. I also added
	 * retroactively removing him from the tournament here.
	 */
	private void disqualify(Player player)
	{
		competitors.remove(player);
		
		// Can remove if necessary.
		List<MatchResult> badMatches = tourneyResult.getAllMatchesWithPlayer(player);
		List<MatchResult> allMatches = tourneyResult.getResults();
		
		for(MatchResult result : badMatches) {
			allMatches.remove(result);
		}
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
			TournamentResult result = tourney.runTournament();
			DataReader data = game.getDataReader(result);
			System.out.println( data.report() );
			// Do more or whatever you want to here.
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


	/** 
	*	Asks the Tournament to pose a question to the user, and returns the
	*	answer as a String. I find it hard to imagine a circumstance where we'll
	*	need this, but implementing anyway. 
	*	Rudimentary implementation, enough to give Tournament the necessary calls,
	*	so this functionality will be in 0.1 but this is not how it will remain
	* 	Definitely will not remain static, but refactor needed to do it right. 
	*	@param question The question to be posed, as a String
	*	@psram responseToken Signifies the type of the expected response. 
	*/
	public static String query (String question)
	{
		String prompt = ">>";
		String response = "";
		System.out.println(question );
		System.out.println(prompt);
		response = scanner.nextLine();
		return response;
	}

	/** 
	*	Asks the Tournament to pose a question to the user, and returns the
	*	answer as an int.
	*	Rudimentary implementation, enough to give Tournament the necessary calls,
	*	so this functionality will be in 0.1 but this is not how it will remain
	* 	Definitely will not remain static, but refactor needed to do it right. 
	*	@param question The question to be posed, as a String
	*	@psram responseToken Signifies the type of the expected response. 
	*/
	public static int query (String question, int responseToken)
	{
		boolean askAgain; 	
		String prompt = ">> ";
		int response = 0;
		do {
			askAgain = false;
			System.out.println(question );
			try{
				System.out.print(prompt);
				response = scanner.nextInt();
			}
			catch (NumberFormatException nfe)
			{
				askAgain = true;
				System.out.println("Expecting an int");
			}
		} while (askAgain);
		return response;
	}

}
