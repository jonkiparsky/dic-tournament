package tourney.loader;

import java.lang.reflect.Modifier;
/**
*	ClassFilter is a modular (swappable) mechanism for accepting certain classes
*	and rejecting others. 
*/
public abstract class ClassFilter
{
	/**
	*	Return true if Class c meets the desired criteria, false otherwise
	*/
	public abstract boolean filter(Class c);

	public static boolean cannotInstantiate(Class c)
	{
		int mods = c.getModifiers();
		return  Modifier.isAbstract(mods) || Modifier.isInterface(mods);
	}

}
