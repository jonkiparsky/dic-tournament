package tourney;

import java.util.ArrayList;
import java.util.List;

/**
 * A Game incorporates the rules of play for a specific non-video game. It is
 * designed to be extended so that a computer version of many games can be
 * implemented. Its main purpose is not to design video games, but rather
 * provide a set of rules so that artificial intelligence implementations (by
 * means of the <code>Player</code> interface) can be run against each other.
 * Although it is possible to design human implementations, there are no
 * provisions within this class to make such implementations user-friendly.
 * 
 * @since 0.1
 */
public abstract class Game
{
	
	/**
	 * A list of all the players in the game, available to implementations of
	 * this class.
	 */
	protected List<Player> activePlayers;
	
	/**
	 * The current record of the Game. This must be instantiated to the proper
	 * type by the implementation in init() or the Game won't play.
	 */
	protected GameResult gameResult;
	

	/**
	 * Plays through the game, based on the given <code>players</code>, and
	 * returns a <code>GameResult</code> that depicts a record of the Game. The
	 * list of players passed to this method should contain the proper amount of
	 * players as specified by the Game's <code>playersPerGame</code> method.
	 * 
	 * @param players
	 *            A list of Player implementations that will play the Game
	 *            against each other (Or with each other for cooperative games).
	 * @return A GameResult structure that contains a list of moves, and
	 *         possibly more data (depending on the implementation).
	 * @throws IllegalMoveException
	 *             If a player implementation submits an illegal move, it is
	 *             passed up the ladder so that it may be dealt with at the
	 *             highest level desirable. Note that when dealing with player
	 *             implementations, they are meant to be machine implementations
	 *             and if it is capable of submitting an incorrect Move, then it
	 *             will most likely do it again.
	 * @throws GameExecutionException
	 *             This is an unchecked Exception, however if a Game
	 *             implementation arrives at a state that it is incapable of
	 *             recovering from, it may throw this exception from somewhere
	 *             within this method. It can be caught and handled, if desired,
	 *             at a higher level.
	 * @see GameResult
	 */
	public GameResult play(List<Player> players)
			throws IllegalMoveException
	{
		// Ensure that activePlayers is initialized before we enter init()
		this.activePlayers = new ArrayList<Player>(players);

		init();

		if (gameResult == null)
		{
			abort(getName() + " does not instantiate gameResult"
					+ " in init() like it's supposed to.");
		}   // JPK: This should be handled by unit testing of submitted Games
			//instead. A Game that doesn't pass this shouldn't even play

		while(keepGoing())
		{
			prePoll();
			poll();
			process();
			updateEachPlayer(activePlayers);
			postUpdate();
			record();
		}
		return gameResult;
	}

	/* Hook Methods in Order of Appearance in play() */

	/**
	 * A pre-game-loop hook method called before the Game starts. All setup code
	 * can go here for the Game.
	 * <p>
	 * Implementations should instantiate <code>gameResult</code> to the
	 * necessary type by overriding this method.
	 */
	protected void init()
	{
	}

	/**
	 * A hook provided in case it is necessary to perform actions each iteration
	 * before the game polls any players for their move.
	 * <p>
	 * An example might be to tell the player the new weights for this round in
	 * an IPD Game.
	 */
	protected void prePoll()
	{
	}

	/**
	 * A hook method for allowing this <code>Game</code> to poll any
	 * <code>Player</code>s for moves before processing any data. This is where
	 * you call <code>player.getMove()</code>, and check for legality. This
	 * method is the only place given to check for the legality of a
	 * <code>Move</code>.
	 * 
	 * @throws IllegalMoveException
	 *             If a player submits an an illegal move, this method must
	 *             notify the code responsible for dealing with it.
	 */
	protected void poll() throws IllegalMoveException
	{
	}

	/**
	 * A hook method that is called after the game polls any Players. Any
	 * processing of the data should go here.
	 * <p>
	 * An example might be updating the game's version of the state or
	 * annotating the moves for the game record.
	 */
	protected void process()
	{
	}

	/**
	 * A hook method that is designed to tell all players what happened for this
	 * iteration.
	 * 
	 * @param players
	 *            A list of players that must be updated.
	 */
	protected void updateEachPlayer(List<Player> players)
	{
	}

	/**
	 * A hook method that is executed after players are updated.
	 */
	protected void postUpdate()
	{
		/*
		 * Included on the off chance that we want to allow something to happen
		 * here. Can't think of a use case just now. Will remove if we can't
		 * think of a reason to keep it.
		 * 
		 * RS: Leaving this comment out of the javadoc...
		 */
	}

	/**
	 * A hook method that is called so the implementation may update the game
	 * record as needed.
	 */
	protected void record()
	{
	}
	
	/**
	 * Causes this game to end on account of the specified <code>reason</code>.
	 * Games can call this if it is necessary to abort their game mid-play.
	 * 
	 * @param reason
	 *            The reason why this Game could not continue.
	 */
	protected final void abort(String reason)
	{
		throw new GameExecutionException(reason);
	}

	/**
	 * Determines if <code>move</code> is a legal action for this Game in its
	 * current state. Illegal can be defined as an "unforgivable mistake". There
	 * is no mechanism for giving the player a second chance for coming up with
	 * a correct move, as we are dealing with artificial intelligence most of
	 * the time, therefore if the game cannot handle the <code>Move</code>
	 * without needing to ask the player for another one, the <code>Move</code>
	 * is by definition, illegal.
	 * 
	 * @param move
	 *            The Move object that was received from one of the players.
	 * @return True if <code>move</code> is acceptable.
	 */
	protected abstract boolean isLegal(Move move);

	/**
	 * Determines if the this game should continue to execute. In other words,
	 * tells when the game is over by returning <code>false</code>.
	 * 
	 * @return False when the game is over. True while the game is still
	 *         playing.
	 */
	protected abstract boolean keepGoing();

	/**
	 * Gets the default <code>Player</code> implementation that this game
	 * provides. This implementation must generate legal moves, but it does not
	 * need to be good at the game.
	 * 
	 * @return The default machine implementation of <code>Player</code> for
	 *         this game.
	 */
	public abstract Player getDefaultAIPlayer();

	/**
	 * Gets the human <code>Player</code> implementation that this game
	 * provides. This implementation must provide an interface to a human,
	 * through the console or otherwise, so that legal moves may be generated
	 * for this game based on their choices.
	 * 
	 * @return The default human implementation of <code>Player</code> for this
	 *         game.
	 */
	public abstract Player getHumanPlayer();

	/**
	 * Gets the <code>DataReader</code> provided by this game to read and
	 * interpret <code>result</code>.
	 * 
	 * @param result
	 *            The results of a Tournament of this game type.
	 * @return A <code>DataReader</code> capable of interpreting
	 *         <code>result</code> correctly.
	 * @see DataReader
	 * @see TournamentResult
	 */
	public abstract DataReader getDataReader(TournamentResult result);

	/**
	 * Gets the String representation of the name of this game.
	 * 
	 * @return The name of this game.
	 */
	public abstract String getName();

	/**
	 * Gets the name of the author who wrote or designed the code for this game.
	 * 
	 * @return The author of this game.
	 */
	public abstract String getAuthor();

	/**
	 * Gets the number of players that are required to play this game. This does
	 * not necessarily mean the minimum number of players. For some games, like
	 * Chinese Checkers, some instances of this game may desire different number
	 * of players. The number returned does reflect the number of players that
	 * this <code>Game</code> requires to be passed to <code>play</code>.
	 * 
	 * @return The number of players that are needed to play this Game.
	 */
	public abstract int playersPerGame();
}
