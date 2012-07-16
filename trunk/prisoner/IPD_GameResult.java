package prisoner;

import tourney.Move;
import tourney.Player;
import tourney.SimultaneousGameResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IPD_GameResult extends SimultaneousGameResult
{
	
	private ArrayList<Round> rounds;
	private ArrayList<IPD_Player> myPlayers;

	public IPD_GameResult(List<Player> players)
	{
		super(players);
		rounds = new ArrayList<Round>();
		myPlayers = new ArrayList<IPD_Player>();

		for (Player p: players)
			myPlayers.add((IPD_Player) p);

	}
	
	public void addRound(ArrayList<Move> round)
	{
		super.addRound(round); // for politeness, we won't use this for anything
		Round newRound = new Round(round);	

		rounds.add(newRound);
		
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
		return ((IPD_Player)p).getScore();
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
	public int getTimeServed(IPD_Player p, int round)
	{
		return rounds.get(round).scoreForPlayer(p);	
	}


	public String details()
	{
			StringBuffer sb = new StringBuffer();
		int i = 0;
		for (Round round: rounds)
		{	
			for (IPD_Player p: round.getMoves().keySet())
			{
				sb.append(p.getID());
				sb.append(": ");
				sb.append(round.getMoves().get(p)
						.getPlay()? "cooperate\t":"defect\t");
	
			}
			sb.append("\n");
		}
		return sb.toString();
	}	

	// Convert this list of generic Moves to something we can use
	private class Round 
	{
		private HashMap<IPD_Player, IPD_Move> movesByPlayer;
		
		public Round(ArrayList<Move> moves)
		{
			movesByPlayer = new HashMap<IPD_Player, IPD_Move>();
			for (Move m :moves)
			{
				movesByPlayer.put((IPD_Player)m.getPlayer(), (IPD_Move) m);
				
			}

			scoreRound(movesByPlayer);
			
		}	
	
		private void scoreRound(HashMap<IPD_Player, IPD_Move> moveList)
		{
			
			IPD_Move [] arr = moveList.values().toArray(new IPD_Move[0]);
			((IPD_Player)arr[0].getPlayer()).updateScore(arr[0].scoreMove(arr[1]));
			((IPD_Player)arr[1].getPlayer()).updateScore(arr[1].scoreMove(arr[0]));
		}	

		public int scoreForPlayer(IPD_Player p)
		{
			return movesByPlayer.get(p).getScore();
		}
		
		public HashMap<IPD_Player, IPD_Move> getMoves()
		{
			return movesByPlayer;
		}
	}
}
