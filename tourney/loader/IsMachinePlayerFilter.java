package tourney.loader;


import tourney.MachinePlayer;
	
/**
*	Accepts any instance of MachinePlayer
*/

public class IsMachinePlayerFilter extends ClassFilter
{

	public boolean filter(Class c)
	{
		if (cannotInstantiate(c))
			return false;
		if (MachinePlayer.class.isAssignableFrom(c))
			return true;

		return false;
	}
	
	public String toString()
	{
		return "Machine Players";
	}
}
