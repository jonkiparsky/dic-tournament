package tourney.loader;

import java.lang.reflect.Modifier;
import tourney.Player;
	
/**
*	Accepts any instance of Player
*/

public class IsPlayerFilter extends ClassFilter
{

	

	public boolean filter(Class c)
	{
		if (cannotInstantiate(c))
			return false;
	
		if (Player.class.isAssignableFrom(c))
			return true;

		return false;
	}
		
	public String toString()
	{	
		return "All Players";
	}
}
