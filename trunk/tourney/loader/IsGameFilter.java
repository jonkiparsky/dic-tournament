package tourney.loader;


import tourney.Game;
	
/**
*	A filter to recognize Games
*/

public class IsGameFilter extends ClassFilter
{

	public boolean filter(Class c)
	{
		if (Game.class.isAssignableFrom(c))
			return true;

		return false;
	}
		
	public String toString()
	{	
		return "Games";
	}
}
