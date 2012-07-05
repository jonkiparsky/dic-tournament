package tourney.loader;


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

}
