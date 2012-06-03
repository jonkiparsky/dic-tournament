package Tourney;

/**
* 	A Game incorporates the rules of play for some particular board game. 
*	Possibly will be expanded to include other sorts of games in future
*	Games do not necessarily include any provision for interactive play; they are
*	primarily intended to run CPU vs. CPU competitions. 
*/



public abstract class Game
{
	void setPlayer(int who, Player p);
	void play(); // play the game through until the end
	void reset();
	// a few methods for gathering statistics about the game. winner(),
	// duration()
	// etc.

}
