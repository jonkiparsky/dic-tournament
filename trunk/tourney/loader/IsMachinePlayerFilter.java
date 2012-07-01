package tourney.loader;


import tourney.MachinePlayer;
	
/**
*	A filter to recognize MachinePlayers
*/

public class IsMachinePlayerFilter extends ClassFilter
{

	public boolean filter(Class c)
	{
		if (MachinePlayer.class.isAssignableFrom(c))
			return true;

		return false;
	}
	
	public String toString()
	{
		return "Machine Players";
	}
}
