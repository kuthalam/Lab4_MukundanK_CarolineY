/* Name: Mukundan Kuthalam, Caroline Yao
 * EID: mk33274, chy253
 * EE 422C - Assignment 4
 */
package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dictionary {
	
	protected ArrayList<String> words;
	
	Dictionary () {
		words = processLinesInFile("A4-words.txt"); //This sets up the dictionary
	}
	
	public ArrayList<String> processLinesInFile (String filename) 
	{
		ArrayList<String> dictionary = new ArrayList<String>(); //Set up an array list to hold the dictionary words
		try 
		{
			FileReader freader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(freader); //Both lines copied from before, no clue how this works exactly
			
			for (String s = reader.readLine(); s != null; s = reader.readLine()) 
			{
				String line = s; //Get the first line of the file
				if (line.charAt(0) == '*') {
					continue; //Skip this iteration of the loop since you don't care about this line
				}
				dictionary.add(line.substring(0,5)); //Since you know the first 5 chars is the word, load it into the dictionary
			} //The dictionary is complete
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println ("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) 
		{
			System.err.println ("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
		return dictionary;
	}
}
