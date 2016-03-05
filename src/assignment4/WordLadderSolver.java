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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
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
    	//TODO Where do we print the ladder?? An implementation is given below
    	boolean startIsValid = false;
    	boolean endIsValid = true;
    	
    	for (Iterator<String> i = dictionary.words.iterator(); i.hasNext();) {
    		String word = i.next(); //Save the word you find in the ArrayList so you do not skip it
    		if (word.equals(startWord)) {
    			startIsValid = true;
    		}
    		if (word.equals(endWord)) { //Remember the fromWord could be the same as the toWord
    			endIsValid = true;
    		}
    	}
    	
    	if ((startIsValid == false) || (endIsValid == false)) { //If neither word can be found in the dictionary, throw an exception
    		throw new NoSuchLadderException("Entered words are invalid");
    	}
    	
    	//What if starting and ending word are the same?
    	if (isOneLetterOff(startWord, endWord) == false) { //The function returns false if the words are the same
    		return solutionList; //If the words are equal, the solution list is empty
    	}
    	
    	//What if starting and ending word are one letter off
    	if (isOneLetterOff(startWord, endWord) == true) { //The function returns false if the words are the same
    		return solutionList; //The same applies to words that are only one letter off
    	}
    	
    	return MakeLadder(startWord, endWord);
    	
    	/*if (solutionList != null) { //Ensure that a word ladder has been found
    		System.out.println(" \n For the input words " + startWord + " and " + endWord + " the following word ladder was found:");
    		System.out.print(startWord + " "); //Print the first word of the ladder
    		for (Iterator<String> ladderWord = solutionList.iterator(); ladderWord.hasNext();) {
    			System.out.println(ladderWord.next() + " ");
    		}
    		System.out.println(endWord + " "); //The last word in the ladder should be the end word
    	} */
    	
        //throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
    	//TODO How to validate that a word ladder should not exist
    	
    	boolean firstIterationFlag = true;
    	String prevWord, nextWord;
    	
    	if (isOneLetterOff(startWord, endWord) == false && wordLadder == null) {
    		return true; //If the words are the same and the wordLadder is empty
    	}
    	
    	if (isOneLetterOff(startWord, endWord) == true && wordLadder == null) {
    		return true; //If the words are one letter off and the wordLadder is empty
    	}
    	
    	if (MakeLadder(startWord, endWord) == null) {
    		return false; //Temporary solution to TODO task
    	}
    	
    	for(ListIterator<String> i = wordLadder.listIterator(); i.hasNext();) {
    		if (firstIterationFlag == true) { //First check the first two words of the word ladder
    			if (isOneLetterOff(i.next(),i.next()) == false) {
    				return false; //If the first two words are not one letter off
    			}
    			firstIterationFlag = false; //The first iteration is over
    			continue; //Move to next iteration
    		}
    		prevWord = i.previous(); //Recover the word that was skipped over due to the previous loop
    		i.next(); //Now you skip over that word again
    		nextWord = i.next(); //The word you want to compare prevWord to is nextWord
    		if (isOneLetterOff(prevWord, nextWord) == false) {
    			return false; //This means that two of the words are not one char away from each other
    		}
    	}
    	
    	return true; //None of the error cases were reached - the word ladder is valid
        //throw new UnsupportedOperationException("Not implemented yet!");
    }

	//add additional methods here
    public List<String> MakeLadder(String fromWord, String toWord){
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
    	for(String word : dictionary.words){ //Iterate over the ArrayList in the Dictionary object
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
