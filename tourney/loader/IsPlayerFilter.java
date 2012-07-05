package tourney.loader;


import tourney.Player;
	
/**
*	Accepts any instance of Player
*/

public class IsPlayerFilter extends ClassFilter
{

	public boolean filter(Class c)
	{
		if (Player.class.isAssignableFrom(c))
			return true;

		return false;
	}
		
	public String toString()
	{	
		return "All Players";
	}
}
