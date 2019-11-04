package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class deals with reading the input files.
 */
class InOut {
	
	private static ArrayList<String> urls = new ArrayList<>();
	private static ArrayList<String> decs = new ArrayList<>();
	private static String ops = "";

	/**
	 * Reads all the urls and descriptions in the urls.txt file
	 * @throws IOException
	 */
	void loadURLs() throws IOException
	{
		int index = 0;
		FileReader f = new FileReader(new File("urls.txt"));
		BufferedReader buffer = new BufferedReader(f);
		
		String str;

		while((str = buffer.readLine()) != null)
		{
			if(index % 2 == 0) {
				if(!str.isEmpty())
					urls.add(str);
			} else {
				decs.add(str);
			}
			index++;
		}
		
		buffer.close();
	}

	/**
	 * reads the ops.txt file for operations
	 * @throws IOException
	 */
	void loadOps() throws IOException
	{
		FileReader f = new FileReader(new File("ops.txt"));
		BufferedReader buffer = new BufferedReader(f);
		
		ops = buffer.readLine();
		buffer.close();
	}

	/**
	 * getter and setters
	 * @return
	 */
	ArrayList<String> getURLs()
	{
		return urls;
	}

	/**
	 * getter and setters
	 * @return
	 */

	ArrayList<String> getDecs()
	{
		return decs;
	}

	/**
	 * getter and setters
	 * @return
	 */
	String getOps()
	{
		return ops;
	}
}
