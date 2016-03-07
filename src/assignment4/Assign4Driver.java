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

public class Assign4Driver
{
    public static void main(String[] args)
    {
        // Create a word ladder solver object
    	// TODO: It said in the pdf that we need to read input pairs from a file
    	// so I changed it. I left your implementation commented out in case the
    	// TA's change their mind on Piazza since some TA's gave conflicting answers
    	Assignment4Interface wordLadderSolver = new WordLadderSolver();
        List<String> result;
        String filename = args[0];
		try {
			FileReader freader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(freader);

			for (String s = reader.readLine(); s != null; s = reader.readLine()) {
				s.trim();
				String[] input = s.split("[\\s]+");
				result = wordLadderSolver.computeLadder(input[0], input[1]);
	            wordLadderSolver.validateResult(input[0], input[1], result);
			}
			reader.close();
		} catch (NoSuchLadderException e) {
			e.getMessage();
            e.printStackTrace();
        } catch (FileNotFoundException e) {
			System.err.println("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			System.err.println("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
    
        /*try 
        {
            result = wordLadderSolver.computeLadder("money", "honey");
            wordLadderSolver.validateResult("money", "honey", result);
            
            result = wordLadderSolver.computeLadder("dears", "fears");
            wordLadderSolver.validateResult("dears", "fears", result);
            //The test cases above this comment work just fine
            result = wordLadderSolver.computeLadder("stone", "money");
            wordLadderSolver.validateResult("stone", "money", result);
            
            result = wordLadderSolver.computeLadder("money", "smart");
            wordLadderSolver.validateResult("money", "smart", result);
            
            result = wordLadderSolver.computeLadder("devil", "angel");
            wordLadderSolver.validateResult("devil", "angel", result);
            
            result = wordLadderSolver.computeLadder("atlas", "zebra");
            wordLadderSolver.validateResult("atlas", "zebra", result);
            
            result = wordLadderSolver.computeLadder("heart", "heart");
            wordLadderSolver.validateResult("heart", "heart", result);
            
            result = wordLadderSolver.computeLadder("babes", "child");
            wordLadderSolver.validateResult("babes", "child", result);
            
            result = wordLadderSolver.computeLadder("mumbo", "ghost");
            wordLadderSolver.validateResult("mumbo", "ghost", result);
            
            result = wordLadderSolver.computeLadder("ryan", "joe");
            wordLadderSolver.validateResult("hello", "buddy", result);
            
            result = wordLadderSolver.computeLadder("hello", "world");
            wordLadderSolver.validateResult("hello", "world", result);
            
            result = wordLadderSolver.computeLadder("heads", "tails");
            wordLadderSolver.validateResult("heads", "tails", result);
        }
        
        catch (NoSuchLadderException e) 
        {
            e.printStackTrace();
        }
    }*/
    }
}
