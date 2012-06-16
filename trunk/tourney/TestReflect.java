package tourney;

import countToN.*;
import java.util.ArrayList;
import java.io.File;
import java.io.FileFilter;

public class TestReflect
{

	public void listGames()
	{
		File root = new File ("./src");
		
		ArrayList<File> packages= new ArrayList<File>();
		for (File f: root.listFiles(new FFilter()))
		{
			packages.add(f);
		}
			
		for (File f: packages)
			System.out.println(f.getName());	
	}


	private class FFilter implements FileFilter
	{	

		public boolean accept(File f)
		{
			return (!f.getName().contains("tourney"));
		}
	}
}
