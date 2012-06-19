package tourney;


/**
*	A stub class to keep the compiler happy. This fulfills the DataReader
*	contract without doing anything useful. 
*/

public class NullDataReader implements DataReader
{
	/**
	*	Null implementation of report method
	*/
	public String report()
	{	

		return "The NullDataReader.report() method has been called.";
	}

	/**
	*	Null implementation of run method
	*/
	public void run()
	{
		System.out.println("I'd love to help you explore the data from this game, but I'm just the NullDataReader and I don't know how to do that.");
	}


	/**
	*	Null implementation of write method
	*/
	public void write()
	{
		System.out.println("NullDataReader pretended to dump data to an outfile.");
	}

}
