package tictactoe;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import tourney.DataReader;
import tourney.MatchResult;
import tourney.Player;
import tourney.TournamentResult;

/**
 * 
 * This class generates a report containing basic statistics for a tournament.
 * Currently, the number of matches won by each winning player is reported.
 * 
 * @author Ryan Beckett
 */
public class GameReport implements DataReader
{

    private TournamentResult result;

    /**
     * Create a new report from the specified tournament result.
     * 
     * @param result
     *            The tournament result.
     */
    public GameReport(TournamentResult result)
    {
        this.result = result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String report()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------\n");
        sb.append("Tournament Report\n");
        sb.append("--------------------------------\n");
        HashMap<Player, Integer> winMap = new HashMap<Player, Integer>();

        for (Player p : result.getPlayers())
        {
            for (MatchResult r : result.getAllMatchesWithPlayer(p))
            {
                for (Player winner : r.getMatchWinners())
                {
                    Integer numWon = winMap.get(winner);
                    if (numWon == null)
                        numWon = 1;
                    else
                        numWon++;
                    winMap.put(winner, numWon);
                }
            }
        }
        TreeMap<Player, Integer> sortedWinMap = new TreeMap<Player, Integer>(
                new ValueComparator(winMap));
        sortedWinMap.putAll(winMap);
        for (Map.Entry<Player, Integer> entry : sortedWinMap.entrySet())
        {
            sb.append(entry.getKey().getID() + " won " + entry.getValue()
                    + " matches.");
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Comparator used to sort a map by value.
     */
    private class ValueComparator implements Comparator<Player>
    {

        private HashMap<Player, Integer> map;

        public ValueComparator(HashMap<Player, Integer> map)
        {
            this.map = map;
        }

        public int compare(Player a, Player b)
        {
            return map.get(a).compareTo(map.get(b)) * (-1);
        }
    }

    @Override
    public void run()
    {
    }

    @Override
    public void write()
    {
    }

}
