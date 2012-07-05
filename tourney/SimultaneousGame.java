package tourney;

import java.util.ArrayList;
import java.util.List;

/**
 * A Simultaneous Game is a game format in which all players submit their moves
 * on the same round, without knowing what the other players will do.
 * <p>
 * Examples include Prisoner's Dilemma and Rock, Paper, Scissors.
 * 
 * @since 0.1
 */
public abstract class SimultaneousGame extends Game
{
	
	/** The current set of Moves from each player. */
	protected ArrayList<Move> round;
	
	protected void init()
	{
		gameResult = new SimultaneousGameResult(activePlayers);
	}

	protected void poll() throws IllegalMoveException
	{
		ArrayList<Move> moves = new ArrayList<Move>();
		for (Player p : activePlayers) {
			Move thisMove = p.getMove();
			thisMove.setPlayer(p);

			if (!isLegal(thisMove)) {
				throw new IllegalMoveException(this, thisMove.getPlayer());
			}
			moves.add(thisMove);
		}
		round = moves;
	}
	
	protected void updateEachPlayer(List<Player> players)
	{
		for (Player p : players)
		{
			for (Move m : round)
			{
				/*
				 * Removed reference issue. We give the player the move he gave
				 * to us in the other game. We should do it here and stay
				 * consistent. A Player should only update his state from a call
				 * to the update() method
				 */
				p.update(m);
			}
		}
	}
	
	protected void record() {
		SimultaneousGameResult gr = (SimultaneousGameResult) gameResult;
		gr.addRound(round);
	}
}