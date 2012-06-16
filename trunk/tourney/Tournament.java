package tourney;

import java.util.ArrayList;
import java.util.List;

import countToN.CountToN;

public class Tournament {

	private static int playersPerGame = 2;

	public static void main(String[] args) {
		Game g = new CountToN();


		TestReflect tr = new TestReflect();
		tr.listGames();		

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(g.getHumanPlayer());
		players.add(g.getDefaultAIPlayer());

		Match match = new Match(g, players, 2);
		//match.playMatch();


		ArrayList<ArrayList<Move>> gameRecords = match.playMatch();
		
		//match.getGameRecords();
			
		for (ArrayList<Move> gameRecord:gameRecords)
		{
			
			Move move = gameRecord.get(gameRecord.size() - 1);
			System.out.println("The winner is "+move.getPlayer().getID());			
		}	
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
