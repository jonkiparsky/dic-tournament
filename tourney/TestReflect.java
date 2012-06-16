package tourney;

import countToTen.*;
import countToN.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.FileFilter;

public class TestReflect
{

	public void listGames()
	{
		File root = new File ("./src");
		
		System.out.println(File.class.getName());
	

		for (File f: root.listFiles(new FFilter()))
		{
			ArrayList<Class> games = new ArrayList<Class>();
			ArrayList<Class> players = new ArrayList<Class>();
			for (File classFile : f.listFiles(new FFilter()))
			{	
				
				String className = classFile.getName().replace(".java", "");
				try {
					Class c = Class.forName(f.getName() + "."+className);
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
			for (Class c:games)
			{
				System.out.println("Game: "+c.getName());
			}
			for (Class c:players)
			{
				System.out.println("Player: "+c.getName());
			}
		
		}	
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
}
