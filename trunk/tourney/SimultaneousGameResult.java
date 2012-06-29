package tourney;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that encapsulates everything that you would need to know about a
 * Game. It is responsible for informing you about all the data it can possibly
 * extract from the List of Moves, so that classes dealing with GameResult need
 * not be concerned with extracting data directly from the Moves.
 * 
 * This will also allow for certain attributes to be set from within the Game
 * implementation itself, such as the Winner of the game. That will prevent the
 * need to replay the Game to find the winner and other statistics.
 */
public class SimultaneousGameResult{
	/** The players who participated in the game. */
	private List<Player> players;


	/**
	* 	The sequence of rounds played in the game
	*/
	private ArrayList<Round> game;
	public SimultaneousGameResult() {
		this( new ArrayList<Player>());

	}

	public SimultaneousGameResult(List<Player> players)
	{
		this.game = new ArrayList<Round>();
		this.players = players;
	}


	public void add(ArrayList<Move> round)
	{
		game.add (new Round(round));	
	}


	public ArrayList<Round> getGame()
	{
		return game;
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	private class Round
	{

		private ArrayList<Move> moves;

		public Round(ArrayList<Move> moves)
		{
			this.moves = moves;
		}

		public ArrayList<Move> getMoves()
		{
			return moves;
		}	

	}
}
