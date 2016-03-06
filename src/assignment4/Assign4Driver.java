/* Name: Mukundan Kuthalam, Caroline Yao
 * EID: mk33274, chy253
 * Section: Thursday 3:30-5pm
 * EE 422C - Assignment 4
 */

package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

public class Assign4Driver
{
    public static void main(String[] args)
    {
        // Create a word ladder solver object
    	// TODO: It said in the pdf that we need to read input pairs from a file
    	// so I changed it. I left your implementation commented out in case the
    	// TA's change their mind on Piazza since some TA's gave conflicting answers
    	Assignment4Interface wordLadderSolver = new WordLadderSolver(args[0]); //dictionary input
        List<String> result;
        String filename = args[1];
		try {
			FileReader freader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(freader);

			for (String s = reader.readLine(); s != null; s = reader.readLine()) {
				try{
					s.trim();
					String[] input = s.split("[\\s]+");
					result = wordLadderSolver.computeLadder(input[0], input[1]);
					wordLadderSolver.validateResult(input[0], input[1], result);
				} catch (NoSuchLadderException e) {
					System.err.println(e.getMessage());
					System.err.println("**********");
				}
			}
			reader.close();
        } catch (FileNotFoundException e) {
			System.err.println("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			System.err.println("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
    }
}
