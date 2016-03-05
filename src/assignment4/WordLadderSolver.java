/* Name: Mukundan Kuthalam, Caroline Yao
 * EID: mk33274, chy253
 * Section: Thursday 3:30-5pm
 * EE 422C - Assignment 4
 */

package assignment4;

/*import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException; */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface
{
	protected Dictionary dictionary; //Create a Dictionary object to hold the dictionary words
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
    public List<String> MakeLadder(String fromWord, String toWord) {
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
    	// TODO what happens if the starting and ending word are the same?
    	// TODO what happens if the starting and ending word are only one letter off?
    	// TODO need to check that both input words are valid words found in dictionary (error message)
    	
    	Map<String, List<String>> wordMap = new HashMap<String, List<String>>(); //lists of all words off by one char from dictionary word (key)
    	fillMap(wordMap);
    	
    	Queue<String> q = new LinkedList<String>();
    	List<String> visited = new ArrayList<String>();	//list of words already visited
    	q.add(fromWord);
    	while(!q.isEmpty()){
    		String word = q.poll();
    		visited.add(word);
    		if(isOneLetterOff(word,toWord)){
    			solutionList.add(word);	//last word in the ladder, done
    			return solutionList;
    		}
    		List<String> nextLayer = wordMap.get(word);	//all words one letter off from current word
    		for(String node : nextLayer){
    			if(visited.contains(node))
    				continue; //node has already been visited
    			q.add(node); //add newly discovered nodes to queue
    		}
    	}
    	return null; //no ladder found
    }
    
    public boolean isOneLetterOff(String word1, String word2){
    	if(word1.equals(word2)){ //all letters same
    		return false;
    	}
    	if(word1.substring(1).equals(word2.substring(1))){	//all letters same except 1st
			return true;
		}
		if(word1.substring(0,1).equals(word2.substring(0, 1)) && word1.substring(2).equals(word2.substring(2))){ //all letters same except 2nd
			return true;
		}
		if(word1.substring(0,2).equals(word2.substring(0,2)) && word1.substring(3).equals(word2.substring(3))){	//all letters same except 3rd
			return true;
		}
		if(word1.substring(0,3).equals(word2.substring(0,3)) && word1.substring(4).equals(word2.substring(4))){	//all letters same except 4th
			return true;
		}
		if(word1.substring(0,4).equals(word2.substring(0,4))){	//all letters same except 5th
			return true;
		}
    	return false;
    }
    
    public void fillMap(Map<String, List<String>> map){
    	//for each word, make a list of words that are only one char off
    	//need to check each letter and find words where that is the only char different
    	//store these words in a list for each dictionary word
    	for(String word : dictionary.words){
    		List<String> list = new ArrayList<String>();
    		for(String w : dictionary.words){
    			if(isOneLetterOff(w, word))
    				list.add(w);
    			//if w isn't off from word by one letter, check next w
    		}
    		map.put(word, list);
    	}
    }
}
