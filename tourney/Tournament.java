package tourney;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


public class Tournament {

	private static HashMap<Class, ArrayList<Class>> gamesToPlayersMap;

	private static int playersPerGame = 2;

	public static void main(String[] args) {
		Game g =null; 

		gamesToPlayersMap = null;

		Loader loader= new Loader();
		try{
		gamesToPlayersMap = loader.listGames();		
		}
		catch (TourneyException te)
		{	
			te.printStackTrace();
		}
		System.out.println ("Games available: ");

		Class[] gamesArray = gamesToPlayersMap.keySet().toArray(new Class[1]);
		for (int i = 1; i <= gamesArray.length; i ++)
		{
			System.out.println(i+ ") "+gamesArray[i-1].getName());
		}
		
		System.out.print("Enter the number of your preferred game: ");
		Scanner scan = new Scanner(System.in);
		Class chosenClass =gamesArray[scan.nextInt()-1];
		ArrayList<Player> players = null;
		try{	
		g = loader.loadGame(chosenClass);
		players =loader.loadPlayers(gamesToPlayersMap.get(chosenClass));
			
		}
		catch (TourneyException te)
		{
			te.printStackTrace();
			System.exit(1);
		}
		

		Match match = new Match(g, players, 2);
		//match.playMatch();


		ArrayList<ArrayList<Move>> gameRecords = match.playMatch();
		
		//match.getGameRecords();
			
		for (ArrayList<Move> gameRecord:gameRecords)
		{
			
			Move move = gameRecord.get(gameRecord.size() - 1);
			System.out.println("The winner is "+move.getPlayer().getID());			
		}	
		
		System.out.println("Tournament reached end of main.");
		
	}


	


	/** 
	*	Purpose: Generate all n-player permutations of the Player list, where n is
	*	the number of players required per game. Players are to play themselves,
	*	order is not significant. For a 3-player game, with players a, b, c, d, e
	*	the set <a,a,a> and <a,a,b> must be generated, but <a,a,b> and <b,a,a>
	*	must not both be generated. 
	* We will need this presently. It will also need improved. 
	*/
	// This only works for two-player games now. 
   // We should worry about combinations here - consider what happens if we try
   // to play Chinese Checkers, and 20 people submit Players!

   public ArrayList<ArrayList<Player>> permute (List <Player> players)
   {
      ArrayList<ArrayList<Player>> permutations 
            = new ArrayList<ArrayList<Player>>();
      
      ArrayList<Player> copy = new ArrayList<Player>(players);

      for (Player p: players)
      {
         for (Player q: copy)
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
