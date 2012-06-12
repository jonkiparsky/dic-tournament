package countToTen;
import tourney.*;
import java.util.ArrayList;

public class CountToTenTournament 
{

	private static ArrayList <Player> players;
	

	public static void main (String[] args)
	{
		players = new ArrayList <Player>();
		players.add(new CountToTenPlayer("Jon"));
		players.add(new CountToTenPlayer("Sheph"));
		Match match = null;
		match = new CountToTenMatch(players);
		match.play();
		System.out.println(match.report());
		
	}
	


	

}
