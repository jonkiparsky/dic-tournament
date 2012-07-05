package tourney.loader;


import tourney.HumanPlayer;
	
/**
*	Accepts HumanPlayers only.
*/

public class IsHumanPlayerFilter extends ClassFilter
{

	public boolean filter(Class c)
	{
		if (HumanPlayer.class.isAssignableFrom(c))
			return true;

		return false;
	}
		
	public String toString()
	{	
		return "Human Players";
	}
}
