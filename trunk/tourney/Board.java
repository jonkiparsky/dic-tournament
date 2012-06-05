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
*/


public interface Board
{
  /**
  * Going along with the decision to boot the player if he makes an incorrect
  * move, by putting the responsibility here for determining a legal move, the
  * player should have no excuse to submit an illegal one, as they can verify
  * themselves from the passed Board. As well as the fact that multiple games can
  * share the same Game class, so the Game would not know what to do anyway.
  */
  boolean isLegal(Move m);
}
