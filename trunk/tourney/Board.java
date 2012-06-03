package tourney;

/**
*	Possibly mis-named, a Board represents the state of play at a moment. A Board
*	object is the mechanism by which a Game communicates to a Player the
*	situation to which that Player must respond, and it is that Board which the
*	Game must use to evaluate the Player's response. 
*	The exact representation of the Board is of course dependent on the game
*	being represented and the whims of the implementers; therefore the Board
*	interface specifies very little. 
*	It may be wise to create sub-interfaces for more particular forms, for
*	example grid-based games. 
*
*	TBD: is a Board responsible for determining legality of a move, or should
*	that be up to the Game? These seem more or less equivalent at first glance. 
*/


public interface Board
{
	
}
