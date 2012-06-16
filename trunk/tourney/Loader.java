package tourney;

import countToN.*;

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
					System.out.println("Okay with: " + c.getName()+
							games.contains(c)+" "+players.contains(c));	
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
}
