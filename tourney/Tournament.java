package tourney;
public class Tournament
{
	public static void main(String[] args)
	{
		System.out.println("Hello, world.");
	}



	void runTournament(){
  		List playerTuples = generatePlayerTuples(); 
		for (List<Player> tuple: playerTuples)
  		{
    		game.play(tuple, sets);
  		}
 
}

// Given N players per game and list L of players, generate all combinations of
// N Players from L
	private List<List<Players>> generatePlayerTuples() 
	{   
		return null;
	}

}
