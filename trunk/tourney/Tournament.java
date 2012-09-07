package tourney;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import tourney.loader.Loader;
import tourney.loader.ClassFilter;
import tourney.loader.IsHumanPlayerFilter;
import tourney.loader.IsPlayerFilter;
import tourney.loader.IsMachinePlayerFilter;

public class Tournament {
	static Scanner scanner = new Scanner(System.in); // For end of tourney input
	private Loader loader;	
	private Game game;
	private ArrayList<Player> players;
	private int gameIterations = 5;
	private TournamentResult tourneyResult;

	public Tournament()
	{
	 	// simply create the object
	}

	public static void main(String[] args) {
		Tournament tourney = new Tournament();
		tourney.setupAndRun();
	}
	
	/**
	*	Sets up the game, with input from the tournament runner	
	*/
	protected void setupAndRun()
	{
		game= null;
		Class[] gamesArray = null;
		loader = new Loader();
		
		try {  
			gamesArray = loader.listGames();
		} catch (TourneyException te) {
			te.printStackTrace(); 
		}

		Class chosenGame = chooseGame(gamesArray);
		int matchType = chooseMatchType();	
		players = loadChosenPlayerSet(matchType, chosenGame);
		game = loadChosenGame(chosenGame);


		
	// dispatch code should be in a method


		if (matchType == 1) {
			players = selectMachinePlayers(players);	
			TournamentResult result = runTournament();
			DataReader data = game.getDataReader(result);
			System.out.println( data.report() );
			data.run();
			// Do more or whatever you want to here.
		}
		
		if (matchType == 2) {
			
			MatchResult m = playHumanPlayers(game, players);
		}	
		
		if (matchType == 3) {
			MatchResult m = playHumanVersusMachine(game, players);
		}


		System.out.println("Tournament reached end of main.");
	}
	
	private MatchResult playHumanVersusMachine(Game game, 
				ArrayList<Player> players)
	{
		Player mp = selectMachinePlayer(players);
		Player hp = game.getHumanPlayer();
		ArrayList<Player> playersList = new ArrayList<Player>();
		playersList.add(mp);
		playersList.add(hp);
		Match m = new Match (game, playersList, 1);
		try {
			return m.playMatch();
		}
		catch (IllegalMoveException ime)
		{
			ime.printStackTrace();
			return null;
		}
	
	}

	private Player selectMachinePlayer (ArrayList<Player> players)
	{
		ArrayList<Player> potentialPlayers = new ArrayList<Player>();

		System.out.println("Searching for Machine Players");

		for (Player p : players)
			if (p instanceof MachinePlayer)
				potentialPlayers.add(p);


		if (potentialPlayers.size() == 0)
		{
			System.out.println("don't seem to have found a Machine Player (??)");
			return null;
		}
		System.out.println("Pick a player (enter the number)");
		for (int i = 0; i <potentialPlayers.size(); i++)
		{

			System.out.println((i+1)+ ") "+potentialPlayers.get(i).getID());
			
		}
		int i = scanner.nextInt();
		return potentialPlayers.get(i-1);	
		
	}



	/**
	*	Sort of a rinky-dink way of selecting players to exclude from a match.
	*	Quick, dirty, should be replaced. 
	*/
	private ArrayList<Player> selectMachinePlayers (ArrayList<Player> players)
	{

		for (Player p : players)
			if (!( p instanceof MachinePlayer))
				players.remove(p);


		if (players.size() == 0)
		{
			System.out.println("don't seem to have found any Machine Players (??)");
			return null;
		}
		System.out.println("Select player to exclude: ");
		for (int i = 0; i <players.size(); i++)
		{
			System.out.println((i+1)+ ") "+players.get(i).getID());
		}
		while (true)
		{
			int input = scanner.nextInt();
			if (input <0 || input > players.size())
					break;
			players.remove(input -1);
			System.out.println("Select player to exclude, -1 to stop selecting: ");
			for (int i = 0; i <players.size(); i++)
			{
				System.out.println((i+1)+ ") "+players.get(i).getID());
			}
		}
		return players;
	}



	
	/**
	*	Presents the tournament runner with the list of available games, and
	*	returns their choice. 
	*
	*	@return the chosen game, as a Class	
	*/
	private Class chooseGame (Class[] gamesArray){
		System.out.println("Games available: ");

		for (int i = 1; i <= gamesArray.length; i++) {
			System.out.println(i + ") " + gamesArray[i - 1].getName());
		}

		System.out.print("Enter the number of your preferred game: ");
		Class chosenGame =  gamesArray[scanner.nextInt() - 1];
		System.out.println("chosen game = " + chosenGame.getName());
		return chosenGame;
	}

	/**
	*	Runs the tournament that has been configured by <code>setup</code>
	*	@return a TournamentResult containing a complete record of the tournament.
	*	Reading the TournamentResult is the responsibility of the GameReader
	*	prepared for this Game. 
	*/
	
	public TournamentResult runTournament() {
		tourneyResult = new TournamentResult(players);
		if (players.size() <2) {
			System.out.println("can't play a tournament with only one player!");
			return tourneyResult;
		}
		List<List<Player>> combinations = Combinations.generateCombinations(
				players, game.playersPerGame(), true);
		

		for(List<Player> combination : combinations) {
			// check for disqualified players
			boolean disqualifiedPlayer = false;
			for(Player player : combination) {
				if(!players.contains(player)) {
					disqualifiedPlayer = true;
				}
			}
			if(disqualifiedPlayer) {
				continue;
			}
			combination = makeUniqueInstances(combination);
	

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

	private List<Player> makeUniqueInstances(
			List<Player> combination)
	{
		for (int i = 0; i <combination.size(); i ++)
		{
			for (int j = i+1; j < combination.size(); j ++)
			if (combination.get(i) == combination.get(j))
			{
				
				Player replacement = loader.getNewInstance(combination.get(j));
				combination.remove(j);
				combination.add(j, replacement);
			}
		}
		return combination;
	}	


	
	/**
	 * Disqualify a player from the tournament. For now we just kick him out and
	 * no future matches with him involved are played. I also added
	 * retroactively removing him from the tournament here.
	 */
	private void disqualify(Player player)
	{
		players.remove(player);
		
		// Can remove if necessary.
		List<MatchResult> badMatches = 
				tourneyResult.getAllMatchesWithPlayer(player);
		List<MatchResult> allMatches = tourneyResult.getResults();
		
		for(MatchResult result : badMatches) {
			allMatches.remove(result);
		}
	}

	private MatchResult playHumanPlayers(Game game, 
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
	*	Presents the user with options for playing different combinations of
	*	Human or Machine players. Returns the player's choice as an int. 
	*	NOTE: there's plenty of room for refactoring here. 
	*/
	private  int chooseMatchType()
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

	/**
	* Loads the necessary <code>Player</code> objects for the chosen match type.
	* Again, this is all ripe for refactoring in a future iteration. 
	*/
	private  ArrayList<Player> loadChosenPlayerSet(int matchType,
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

	/**
	*	Instantiates and returns an object of the submitted Class. which must be a
	*	Game. 
	*	
	*/

	private  Game loadChosenGame(Class chosenGame)
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
	*	@param responseToken Signifies the type of the expected response. 
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
	*	@param responseToken Signifies the type of the expected response. 
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
