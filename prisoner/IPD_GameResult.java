package prisoner;

import tourney.Move;
import tourney.Player;
import tourney.SimultaneousGameResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IPD_GameResult extends SimultaneousGameResult
{
	private HashMap<Player, ArrayList<Integer>> timeServed;
	
	public IPD_GameResult(List<Player> players)
	{
		super(players);
		timeServed = new HashMap<Player, ArrayList<Integer>>();
		for(Player p : players) {
			timeServed.put(p, new ArrayList<Integer>());
		}
	}
	
	public void addRound(ArrayList<Move> round)
	{
		super.addRound(round);
		
		HashMap<Player, Boolean> plays = new HashMap<Player, Boolean>();
		
		for(Move move : round)
		{
			IPD_Move ipd_move = (IPD_Move)move;
			boolean play = ipd_move.getPlay();
			Player player = ipd_move.getPlayer();
			
			plays.put(player, play);
		}
		
		List<Player> players = getPlayers();
		
		boolean player1 = plays.get(players.get(0));
		boolean player2 = plays.get(players.get(1));
		
		if(player1)
		{
			if(player2)
			{
				timeServed.get(players.get(0)).add(IteratedPrisonersDilemma.BOTH_COOP);
				timeServed.get(players.get(1)).add(IteratedPrisonersDilemma.BOTH_COOP);
			}
			else
			{
				timeServed.get(players.get(0)).add(IteratedPrisonersDilemma.ONE_COOP);
				timeServed.get(players.get(1)).add(IteratedPrisonersDilemma.ONE_DEFECT);
			}
		}
		else if (player2)
		{
			// we know player 1 defected
			timeServed.get(players.get(0)).add(IteratedPrisonersDilemma.ONE_DEFECT);
			timeServed.get(players.get(1)).add(IteratedPrisonersDilemma.ONE_COOP);
		}
		else
		{
			timeServed.get(players.get(0)).add(IteratedPrisonersDilemma.BOTH_DEFECT);
			timeServed.get(players.get(1)).add(IteratedPrisonersDilemma.BOTH_DEFECT);
		}
	}
	
	/**
	 * Gets the total time served by a player in this game.
	 * 
	 * @param p
	 *            The player whose statistics to get.
	 * @return The total time served by <code>p</code>.
	 */
	public int getTotalTimeServed(Player p)
	{
		ArrayList<Integer> rounds = timeServed.get(p);
		int sum = 0;
		for(Integer x : rounds)
		{
			sum+=x;
		}
		return sum;
	}
	
	/**
	 * Gets the amount of time served by the player in the round. The proper
	 * range of round will be determined by the Game you play. Default is 0-9.
	 * 
	 * @param p
	 *            The player you would like to get statistics for.
	 * @param round
	 *            The round number that you want to find who got what payoff.
	 * @return The amount of time served by <code>p</code> in the specified
	 *         round.
	 */
	public int getTimeServed(Player p, int round)
	{
		return timeServed.get(p).get(round);
	}
}
