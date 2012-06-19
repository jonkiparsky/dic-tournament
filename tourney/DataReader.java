package tourney;


/**
*	This is an idea I'm working out on the fly. Nothing about it is fixed, and
*	input is not only welcome but urgently wanted. 
*	If it's not working, we can kill it, as well. 
*
*	Notes: Move is the basic unit of interest. While we will want to be able to
*	query Games and Matches, or possibly some data class (GameRecord and
*	MatchRecord?), DataReader should allow the user of the Tournament class to
*	drill down to the Move level for raw details. Although I have no idea how
*	that will work, and will only figure out what that means by writing some
*	classes. 
*/

public interface DataReader
{
	/**
	*	Returns a String summarizing the results of the Tournament
	*	This may be a longish String, but should probably be restricted to one
	*	reasonable screen length. 
	*/
	public String read();

	/**
	*	Starts up an interactive data explorer, allowing the tournament runner to
	*	drill into whatever data the game designer has decided to provide.
	*	It is up to the designer to provide whatever interface they prefer,
	*	although we should probably provide the basics. 
	*	This will probably have to be run in a GUI, which prospect excites me not
	*	at all. 
	*/
	public void run();


	/**
	*	Dumps data to an output file, format as yet unspecified. 
	*/
	public void report();

}
