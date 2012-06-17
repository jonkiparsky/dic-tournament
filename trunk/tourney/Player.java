package tourney;

/**
 * A Player could be used to implement a human player for interactive contexts.
 * (It might even be sensible to prepare such a HumanPlayer class for
 * interactive testing!) but it is intended to support a machine player for CPU
 * vs CPU competition.
 */
public interface Player {
	/**
	 * Demand a move from the player. No longer requires a state to be passed!
	 * Each player keeps track of their own state of affairs and are responsible
	 * for keeping it up to date.
	 */
	Move getMove();

	/**
	 * A Player must have a unique identifier. This will allow stats to make
	 * coherent reports. Tentative suggestion: ID should be composed of a game
	 * code, player's DIC member name, and a serial number (to allow for
	 * multiple entries)
	 * 
	 * R.S: Suggested Form: GameName_DICName_Submission#_Instance#
	 * 
	 * GameName and DICName should be obvious.
	 * 
	 * Submission# is the number of their entry. (ie, TTT_Sheph_1 and
	 * TTT_Sheph_2 are my two implementations.)
	 * 
	 * Instance# will be reserved for when we put TTT_Sheph_1 vs TTT_Sheph_1.
	 */
	String getID();

	/**
	 * Sends an update to the player so they may appropriately change
	 * their game's state.
	 */
	void update(Move move);
}
