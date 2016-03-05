/* Name: Mukundan Kuthalam, Caroline Yao
 * EID: mk33274, chy253
 * Section: Thursday 3:30-5pm
 * EE 422C - Assignment 4
 */

package assignment4;

import java.util.List;
import java.util.ListIterator;

public class Assign4Driver
{
    public static void main(String[] args)
    {
        // Create a word ladder solver object
        Assignment4Interface wordLadderSolver = new WordLadderSolver();
        List<String> result;
        try 
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
    }
}
