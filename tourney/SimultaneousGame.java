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
	protected boolean forfeit = false;

	/**
	 * Based on these players, play through the game, and return a List of Moves
	 * that depicts a record of the game.
	 */
	public GameResult play(List<Player> players) {
		this.players = new ArrayList<Player>(players);

		gameResult = new GameResult();
		int currentRound  = 0;	
		init();
			
		do {
			preMove();
			round = getMoves(currentRound);
	
			processRound(round);
			updateEachPlayer(players);
			postUpdate();
			recordRound(round);
		
		} while (keepGoing() && !forfeit);
		
		return gameResult;
	}

	// Back to needing this
	protected abstract boolean isLegal(Move move);

	protected ArrayList<Move> getMoves(int currentRound)
	{	
		ArrayList<Move> moves = new ArrayList<Move>();
		for (Player p: players)
		{
			Move thisMove = p.getMove();
			thisMove.setPlayer(p);
			thisMove.annotate("Round", Integer.toString(currentRound));
			if (!isLegal(thisMove))
			{
				registerForfeit(thisMove);
				return moves;
			}				
			moves.add(thisMove);				
		}
		return moves;
	}

	/**
	*	In a simultaneous game, we process moves a round at a time. It would be
	*	perfectly reasonable to loop over the ArrayList of Moves using
	*	processMove() to handle each. 	
	*/
	protected void processRound(ArrayList<Move> round)
	{
				
	}

	/**
	*	In a simultaneous game, we record moves by rounds. As with processRounds,
	*	it would be perfectly reasonable to use this method to loop over
	*	processMove(). 
	*/	
	protected void recordRound(ArrayList<Move> round)
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
	

	/** game subclass tells us whether or not we keep going */
	protected abstract boolean keepGoing();


	/**
	 * Tell all players what happened. Default is to just pass them all the
	 * Moves from the last Round. Implementations can annotate them accordingly.
	 */
	protected void updateEachPlayer(List<Player> players) {
		
		for (Player p: players)
		{
			for (Move m : round) {
				if (p != m.getPlayer())		// reference equality is intentional
													// since we know that no instance will
													// play against itself. This can change
													// if we want to implement equals for
													// Player (based on ID, I suppose)		 
					p.update(m);
			}
		}
	}

}
