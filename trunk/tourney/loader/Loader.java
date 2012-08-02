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
	HashMap<Class, ArrayList<Class>> gamesToPlayersMap;


	/**
	*	returns a list of Class objects representing the available Games in this
	*	instance of the Tournament. Games are discovered by reflection, so they do
	*	not need to be registered. If they are visible, they will be loaded. 
	*/
	public Class[] listGames() throws TourneyException
	{
		
			gamesToPlayersMap = new HashMap<Class, ArrayList<Class>>();

		File current = new File(".");
		for (File f: current.listFiles())
			System.out.println(f.toString());

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
		return gamesToPlayersMap.keySet().toArray(new Class[1]);
	}

	/**
	*	Given a class extending Game, create and return an instance of the Game
	*	object. 
	* 	@throws TourneyException
	*/ 
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
		{			// fix this for 0.1
			System.out.println("Error in loader.loadGame()");
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

	
	/**
	*	Load the chosen set of Player objects for the selected game. Player
	*	objects are selected by choosing an appropriate ClassFilter, from the list
	*	of Players mapped to this Game when listGames() is run. 
	*	@param chosenGame the Class for the game we wish to play
	*	@param filter the ClassFilter passing only the classes we need to load
	*/
	public ArrayList<Player> loadPlayers (Class chosenGame, ClassFilter filter)
				throws TourneyException
	{
		ArrayList<Class> classList = gamesToPlayersMap.get(chosenGame);
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
											// after debugging. When logging is implented,
											// this should be logged. 
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


	/**
	*	Clone a Player object. It would be reasonable to just make Player
	*	cloneable, I suppose. This would be an internal change, can be made
	*	whenever. 
	*/
	public Player getNewInstance(Player p)
	{
		Player newPlayer = null;
		try{	
			 newPlayer =  p.getClass().newInstance();
		}   // fix these Exceptions for 0.1
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return newPlayer;
	}


	
	/**
	* 	File filter to detect relevant files to load.
	* 	Improve this for 0.1
	*/ 
	private class FFilter implements FileFilter
	{	
		public boolean accept(File f)
		{
				if (f.getName().contains("tourney")) return false;
 				if (f.getName().contains("svn")) return false;
 				return true;
		}
	}
	

}
