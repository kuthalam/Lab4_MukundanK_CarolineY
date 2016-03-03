/* Name: Mukundan Kuthalam, Caroline Yao
 * EID: mk33274, chy253
 * EE 422C - Assignment 4
 */

package assignment4;

/*import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException; */
import java.util.ArrayList;
import java.util.List;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface
{
	protected Dictionary dictionary; //Create a Dictionary object to hold the dictionary
	protected List<String> solutionList; //This is a list of all the words that can be moved to (for the Ladder)

    WordLadderSolver() {
    	dictionary = new Dictionary(); //Set up the words from the file
    }
    
    // add a constructor for this object. HINT: it would be a good idea to set up the dictionary there

    // do not change signature of the method implemented from the interface
    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
    	
        // implement this method
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

	//add additional methods here
    public List<String> MakeLadder(String fromWord, String toWord, int posChanged) {
    	/* Here is the process for this:
    	 * Step 1: Create an array of all words that are one away from each char of the starting word
    	 * Do this by switching every char of the forWord from a-z and seeing if each changes results in a legit word
    	 * Step 2: Compare each char of the target word to the sorted words and append the appropriate numbers to
    	 * the beginning of the string
    	 * Step 3: Re-sort the array
    	 * Step 4: Now move to the first possible word (follow the order of the array)
    	 * Step 5: Perform above operations until you run into an impossible word
    	 * Step 6: This will happen when SolutionList is empty
    	 * Step 7: In that case, go back and mark the node you ran into as a "fail"
    	 * Step 8: Keep checking and going back as needed until you hit gold or you run out of words to check
    	 * Step 9: You hit gold, return the list. If not, throw the NoSuchLadderException
    	 */
    	
    	List<String> candidates = new ArrayList<String>(); //All the possible words that could go in solutionList
    	String changeWord = "a"; //This is a variable that holds the word after changing one letter 
    	
    	if (fromWord.equals(toWord)) { //This is when you have reached the end of the ladder
    		return solutionList;
    	}
    	
    	if ((fromWord.toCharArray()).length != 5) {
    		//If the length of the word is not 5, we have a problem
    	}
    	
    	if ((toWord.toCharArray()).length != 5) {
    		//If the length of the word is not 5, we have a problem
    	}
    	
    	//Fill the candidate array
    	
    	
    	
		return solutionList;
    }
}
