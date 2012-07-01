package tourney.loader;

import tourney.TourneyException;
import tourney.Game;
import tourney.Player;
import tourney.HumanPlayer;
import tourney.MachinePlayer;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.FileFilter;


public class Loader
{

	public HashMap<Class, ArrayList<Class>> listGames() throws TourneyException
	{
			HashMap<Class, ArrayList<Class>> gamesToPlayersMap =
				new HashMap<Class, ArrayList<Class>>();
		File root = new File("./src");
		for (File f: root.listFiles(new FFilter()))
		{
			ArrayList<Class> games = new ArrayList<Class>();
			ArrayList<Class> players = new ArrayList<Class>();
			for (File classFile : f.listFiles(new FFilter()))
			{	
				String packagedName;
				String className = classFile.getName().replace(".java", "");
				packagedName =f.getName() + "."+className; 
				try {
					Class c = Class.forName(packagedName);
					if (Game.class.isAssignableFrom(c)){
						games.add(c);
					}
					if (Player.class.isAssignableFrom(c)){
						players.add(c);
					}
				}	
				catch(Exception e) {  // evil, clean this up
					e.printStackTrace();
				}
			}	
			if (games.size()!= 1)
			{
				for (Class c: games) System.out.println(c);
				throw new TourneyException("Too many games in directory " +
					f.getName());
			}			
			else
			{
				Class game = games.get(0);
				
				gamesToPlayersMap.put(game, players);
			}

				
		}	
		return gamesToPlayersMap;
	}
	

	private class FFilter implements FileFilter
	{	
		public boolean accept(File f)
		{
			if (f.getName().contains("tourney")) return false;
			if (f.getName().contains("svn")) return false;
			return true;
		
		}
	}

	public Game loadGame(Class c) throws TourneyException
	{
		IsGameFilter isGame = new IsGameFilter();
		if (! isGame.filter(c))
		{
			throw new TourneyException("LoadGame failed: class" +
					c.getName() + "failed to pass the Game filter");
			
		}
		Object o = null;
		try{
			o = c.newInstance();
		}
		catch(Exception e)
		{	
			e.printStackTrace();
			System.exit(1);
		}
		if (o instanceof Game)
		{
			return (Game) o;
		}
		else throw new TourneyException("loadGame failed: "+c.getName()+
			" does not extend Game");

	}

	public ArrayList<Player> loadPlayers (ArrayList<Class> classList)
		throws TourneyException

	{
		return loadPlayers(classList, new IsPlayerFilter());
	}

	
	

	public ArrayList<Player> loadPlayers (ArrayList<Class> classList, 
				ClassFilter filter)
				throws TourneyException
	{
		ArrayList<Player> players = new ArrayList<Player>();
		for (Class c: classList)
		{
			if ( filter.filter(c))
			{
				Object o = null;
				try {
		
					o = c.newInstance();
				}
				catch(IllegalAccessException iae) {
				
					iae.printStackTrace(); // should go to logging

					System.exit(1);	// break the game for now, 
											// but this should be demoted to continue
											// after debugging
				}
				catch (InstantiationException ie)
				{	
					ie.printStackTrace(); // see above
					System.exit(1);
				}
				catch (SecurityException ie)
				{	
					ie.printStackTrace(); // see above
					System.exit(1);
				}
					players.add ((Player) o);
			}
		}
		return players;

	}


	

}
