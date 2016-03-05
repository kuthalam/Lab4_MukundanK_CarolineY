/* Name: Mukundan Kuthalam, Caroline Yao
 * EID: mk33274, chy253
 * Section: Thursday 3:30-5pm
 * EE 422C - Assignment 4
 */

package assignment4;

import java.util.List;

public class Assign4Driver
{
    public static void main(String[] args)
    {
        // Create a word ladder solver object
        Assignment4Interface wordLadderSolver = new WordLadderSolver();

        try 
        {
            List<String> result = wordLadderSolver.computeLadder("money", "honey");
            boolean correct = wordLadderSolver.validateResult("money", "honey", result);
            if (correct == true) {
            	System.out.println("Correct");
            }
            else {
            	System.out.println("Incorrect");
            }
        }
        
        catch (NoSuchLadderException e) 
        {
            e.printStackTrace();
        }
    }
}
