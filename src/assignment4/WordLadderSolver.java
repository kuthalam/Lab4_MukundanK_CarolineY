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
import java.util.Arrays;
import java.util.HashMap;
//import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
//import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;

// do not change class name or interface it implements
//TODO: I'm working out a lot of bugs so I've commented out a lot of stuff.
//Sorry that it's so messy. I'll fix everything Sunday. Try not to change anything
//because it might make me lose what I was trying to do since I'm still in the middle
//of changing everything to fix the bugs.
//TODO: Can you start working on the testing report that's mentioned in the pdf?
public class WordLadderSolver implements Assignment4Interface
{
	protected Dictionary dictionary; //Create a Dictionary object to hold the dictionary words
	//protected List<String> solutionList = new ArrayList<String>(); //This is a list of all the words that can be moved to (for the Ladder)

    WordLadderSolver() {
    	dictionary = new Dictionary(); //Set up the words from the file
    }
    
    // add a constructor for this object. HINT: it would be a good idea to set up the dictionary there

    // do not change signature of the method implemented from the interface
    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
    	
    	//TODO Where do we print the ladder?? An implementation is given below
    	/*boolean startIsValid = false;
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
    	*/
    	if(!dictionary.words.contains(startWord) || !dictionary.words.contains(endWord))
    		throw new NoSuchLadderException("At least one of the entered words is invalid.");
    	
    	/*//What if starting and ending word are the same?
    	if (startWord.equals(endWord) == true) { //The function returns true if the words are the same
    		return solutionList; //If the words are equal, the solution list is empty
    	}
    	
    	//What if starting and ending word are one letter off
    	if (isOneLetterOff(startWord, endWord) == true) { //The function returns false if the words are the same
    		solutionList.add(startWord);
    		solutionList.add(endWord);
    		return solutionList; //The same applies to words that are only one letter off
    	}*/
    	
    	return MakeLadder(startWord, endWord);
    	
        //throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder)
    {
    	//TODO How to validate that a word ladder should not exist
    	
   /* 	boolean firstIterationFlag = true;
    	String prevWord, nextWord;
    	
    	if (isOneLetterOff(startWord, endWord) == false && wordLadder.isEmpty() == true) {
    		printLadder(startWord, endWord, wordLadder); //Print the wordLadder
    		return true; //If the words are the same and the wordLadder is empty
    	}
    	
    	if (isOneLetterOff(startWord, endWord) == true && wordLadder.isEmpty() == true) {
    		printLadder(startWord, endWord, wordLadder); //Print the wordLadder
    		return true; //If the words are one letter off and the wordLadder is empty
    	}*/
    	
    	/*if (MakeLadder(startWord, endWord) == null) {
    		return true; //Temporary solution to TODO task
    	}*/
    	
    	/*for(ListIterator<String> i = wordLadder.listIterator(); i.hasNext();) {
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
    	}*/
    	//check if first and last words are the start and end words
    	if(startWord.equals(endWord)){
    		if(wordLadder.size() != 2) //should contain start and end word
    			return false;
    		if(!wordLadder.get(0).equals(wordLadder.get(1))) //the 2 entries should be the same
    			return false;
    		if(!wordLadder.get(0).equals(startWord)) //first and last entry must equal the start word
    			return false;
    	}
    	else{
    		if(!wordLadder.get(0).equals(startWord)) //first entry must be start word
    			return false;
    		if(!wordLadder.get(wordLadder.size() - 1).equals(endWord)) //last entry must be end word
    			return false;
    		for(int i = 0; i < wordLadder.size() - 1; i++){ //length - 1 iterations
    			if(!isOneLetterOff(wordLadder.get(i), wordLadder.get(i+1)))
    				return false;
    		}
    	}
    	printLadder(startWord, endWord, wordLadder); //Print the valid word ladder
    	return true; //None of the error cases were reached - the word ladder is valid
        
    	//throw new UnsupportedOperationException("Not implemented yet!");
    }

	//add additional methods here
    public List<String> MakeLadder(String fromWord, String toWord) throws NoSuchLadderException{
    	//TODO I've figured out what's wrong with the BFS, I'll fix it later
    	//List<String> solutionList = new ArrayList<String>();
    	Map<String, String> parentMap = new HashMap<String, String>();
    	if(isOneLetterOff(fromWord, toWord) || fromWord.equals(toWord)){ //if input is same or one letter off
    		/*solutionList.add(fromWord);
    		solutionList.add(toWord);
    		return solutionList;*/
    		parentMap.put(toWord, fromWord);
    		List<String> ladder = ladderToList(fromWord, toWord, parentMap);
    		return ladder;
    	}
    	//solutionList.add(fromWord);
    	Map<String, List<String>> wordMap = new HashMap<String, List<String>>(); //lists of all words off by one char from dictionary word (key)
    	fillMap(wordMap);
    	Queue<String> q = new LinkedList<String>();
    	List<String> visited = new ArrayList<String>();	//list of words already visited
    	q.add(fromWord);
    	visited.add(fromWord);
    	while(!q.isEmpty()){
    		String word = q.poll();
    		if(isOneLetterOff(word,toWord)){
    			parentMap.put(toWord, word); //parent of end word is the word that is one letter off
    			List<String> ladder = ladderToList(fromWord, toWord, parentMap);
        		return ladder;
    			/*solutionList.add(word);	//last word in ladder
    			solutionList.add(toWord); //add ending word
    			return solutionList;*/
    		}
    		List<String> nextLayer = wordMap.get(word);	//all words one letter off from current word
    		for(String node : nextLayer){
    			if(visited.contains(node))
    				continue; //node has already been visited
    			q.add(node); //add newly discovered nodes to queue
    			visited.add(node); //mark as visited
    			parentMap.put(node, word);
    		}
    	}
    	//return null; //no ladder found
    	//solutionList.add(toWord); //need this?
    	throw new NoSuchLadderException("No ladder could be found between " + fromWord + " and " + toWord + ".");
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
    		for(String w : dictionary.words){ //iterate through all words in dictionary to find all one letter off from word
    			if(isOneLetterOff(w, word))
    				list.add(w);
    			//if w isn't off from word by one letter, check next w
    		}
    		map.put(word, list);
    	}
    }
    
    public void printLadder(String startWord, String endWord, List<String> wordLadder) {
    	/*if (solutionList != null) { //Ensure that a word ladder has been found
    		//TODO don't need null anymore?
    		System.out.println("\nFor the input words \"" + startWord + "\" and \"" + endWord + "\" the following word ladder was found:");
    		//System.out.print(startWord + " "); //Print the first word of the ladder
    		for (Iterator<String> ladderWord = solutionList.iterator(); ladderWord.hasNext();) {
    			System.out.println(ladderWord.next() + " ");
    		}
    		//System.out.println(endWord + " "); //The last word in the ladder should be the end word
    		System.out.println("**********"); //Use 10 asterisks to denote the end of a word ladder
    	}*/
   
    		//What if the ladder is empty because the input words are one letter off?
    		/*System.out.println("\nFor the input words \"" + startWord + "\" and \"" + endWord + "\" the following word ladder was found:");
    		//TODO fix this since startWord and endWord have been added to the beginning and end of returned ladder
    		for (Iterator<String> ladderWord = wordLadder.iterator(); ladderWord.hasNext();) {
    			System.out.print(ladderWord.next() + " ");
    		}
    		System.out.println("\n**********"); //Use 10 asterisks to denote the end of a word ladder
    	*/
    	/*else { //What if a valid word ladder was not found
    		System.out.println("\nFor the input words " + startWord + " and " + endWord + " a valid word ladder could not be found");
    		System.out.println("**********"); //Use 10 asterisks to denote the end of a word ladder
    	}*/
    		/*String ladder = endWord;
    		String word = endWord;
    		while(!word.equals(startWord)){
    			String parent = wordLadder.get(word);
    			ladder = parent + " " + ladder;
    			word = parent;
    		}*/
    		System.out.println(Arrays.toString(wordLadder.toArray()));
    		System.out.println("**********");
    }
    
    public List<String> ladderToList (String startWord, String endWord, Map<String, String> map){
    	LinkedList<String> ladder = new LinkedList<String>();
    	if(startWord.equals(endWord)){
    		ladder.add(startWord);
    		ladder.add(endWord);
    		return ladder;
    	}
    	ladder.add(endWord);
    	String word = endWord;
    	while(!word.equals(startWord)){
    		String parent = map.get(word);
    		ladder.addFirst(parent);
    		word = parent;
    	}
    	return ladder;
    	
    }
}


