package tourney.loader;


import tourney.Game;
	
/**
*	Class filter, accepts instances of Game. 
*/

public class IsGameFilter extends ClassFilter
{

	public boolean filter(Class c)
	{
		if (cannotInstantiate(c))
			return false;
		
		if (Game.class.isAssignableFrom(c))
			return true;

		return false;
	}
		
	public String toString()
	{	
		return "Games";
	}
}
