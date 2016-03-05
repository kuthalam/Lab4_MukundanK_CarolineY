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
        	//WordLadderSolver ls = new WordLadderSolver(); - Yay, dictionary is set up
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
