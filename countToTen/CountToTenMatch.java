package countToTen;

import tourney.*;
import java.util.ArrayList;


public class CountToTenMatch extends Match
{
	ArrayList<Player> players;
	private int numberOfGames = 10;

	public CountToTenMatch(ArrayList <Player> players) 
	{
		this.players = players;
				
		
	}

	public void play()
	{
	 	// do nothing for the moment	
	}

	public String report()
	{
		return "Jon wins, of course!";
	}

	
}
