package tourney.loader;


import tourney.Player;
	
/**
*	A filter to recognize Players
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
