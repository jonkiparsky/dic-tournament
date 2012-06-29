package tourney;

import java.util.ArrayList;
import java.util.List;

/**
 *	A Simultaneous Game is a game in which all players enter their moves without
 *	seeing previous moves. Examples include Prisoner's Dilemma and Rock, Paper,
 *	Scissors. 
 */
public abstract class SimultaneousGame extends Game {

	protected ArrayList<Move> round;


	/**
	 * Based on these players, play through the game, and return a List of Moves
	 * that depicts a record of the game.
	 */
	public GameResult play(List<Player> players) {
		this.players = new ArrayList<Player>(players);

		gameResult = new GameResult();
		
		init();
			
		do {
			preMove();
			round = getMoves();
	
			processRound();
			updateEachPlayer(players);
			postUpdate();
			recordRound(round);
		} while (keepGoing());
		
		return gameResult;
	}

	// Back to needing this
	protected abstract boolean isLegal(Move move);

	protected ArrayList<Move> getMoves()
	{	
		ArrayList<Move> moves
		for (Player p: players)
		{
			Move thisMove = p.getMove();
			thisMove.setPlayer(p);
			thisMove.annotate(round, Integer.toString(currentRound));
			if (!isLegal(thisMove)
			{
				registerForfeit(thisMove);
				return gameResult;
			}				
			
		}
		
	}

	/**
	*	In a simultaneous game, we process moves a round at a time. It would be
	*	perfectly reasonable to loop over the ArrayList of Moves using
	*	processMove() to handle each. 	
	*/
	processRound(ArrayList<Move> round)
	{
				
	}

	/**
	*	In a simultaneous game, we record moves by rounds. As with processRounds,
	*	it would be perfectly reasonable to use this method to loop over
	*	processMove(). 
	*/	
	recordRound(ArrayList<Move> round)
	{	
			
	}

	/**
	*	This method is executed after the current player is selected, and before
	*	their move is solicited. 
	*/
	protected void preMove()
	{
	}

	/**
	*	This method is called after the currentPlayer's move has been received.
	*	Any processing of the move should go here, including updating our state
	*	and annotating the move for other Players and for the game record.
	*	Current move is in field "move"
	*/
	protected void processMove()
	{
		// implementations should call super.processMove();
		move.setPlayer(currentPlayer);
	}

	/**
	*	This method is called to add the current move to the game record. The
	*	default is to simply place the Move returned by the current Player, as
	*	annotated in processMove(), in the list. This method is made available for
	*	any cases where other steps need to be taken. 
	*/
	protected void recordMove()
	{
			gameResult.add(move);
	}
	
	/**
	*	This method is executed after players are updated. Included on the off
	*	chance that we want to allow something to happen here. Can't think of a
	*	use case just now. Will remove if we can't think of a reason to keep it. 
	*/
	protected void postUpdate()
	{	
	}

	/**
	* Returns the next player to move. Base implementation simply rotates first
	* player in list to end of list.
	*/
	protected Player nextPlayer()
	{
			Player currentPlayer = players.remove(0);
			players.add(currentPlayer);
			return currentPlayer;
			
	}

	/** game subclass tells us whether or not we keep going */
	protected abstract boolean keepGoing();


	/**
	 * Tell all players what happened. Default is to just pass them all the
	 * Move. Implementations can annotate them accordingly.
	 */
	protected void updateEachPlayer(List<Player> players) {
		for (Player player : players) {
			player.update(moveForPlayer(player));
		}
	}

	/**
	*	Get the correct Move object for a particular player. Default is to return
	*	the same move for each Player. If private information is to be returned
	*	for particular Players, use a Map of Players to Moves. 
	*/

	protected Move moveForPlayer(Player player)
	{
		return move;
	}

	/**
	* Hook for handling a case of a forfeit due to illegal move
	*/

	protected void registerForfeit()
	{
	}

	/**
	 * Any setup code can go here. One good trick would be to use
	 * updateEachPlayer() to set up the initial state if that in not
	 * predetermined. 
	 */
	protected void init() {

	}

	/**
	 * We suggest that game developers provide a very basic "AI" for testing.
	 * The default AI must generate legal moves, but they need not be good ones.
	 * Random is fine. If no default AI player is provided, defaultAIPlayer
	 * should be set to null.
	 */
	public abstract Player getDefaultAIPlayer();

	/**
	 * We also suggest that game developers provide a human interface, also for
	 * testing. If none is provided, humanPlayer should be null.
	 */
	public abstract Player getHumanPlayer();

	/**
	 * Each game should define how many players it needs as a parameter. Users
	 * of this interface should ensure that the list of players passed to play()
	 * is the size of this return value.
	 */
	public abstract int playersPerGame();


	public abstract String getName();

	public abstract String getAuthor();
}
