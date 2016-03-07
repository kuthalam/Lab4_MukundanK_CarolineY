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

public class WordLadderSolver implements Assignment4Interface
{
	protected Dictionary dictionary; //Create a Dictionary object to hold the dictionary words
	
    WordLadderSolver(String dictionaryFile) {
    	dictionary = new Dictionary(dictionaryFile); //Set up the words from the file
    }
    
    // add a constructor for this object. HINT: it would be a good idea to set up the dictionary there

    // do not change signature of the method implemented from the interface
    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
    	//throw exception if at least one of the input words is not in the dictionary
    	if(!dictionary.words.contains(startWord) || !dictionary.words.contains(endWord)){
        	System.out.println("For the input words '" + startWord + "' and '" + endWord + "' the following word ladder was found");
    		throw new NoSuchLadderException("At least one of the words " + startWord + " and " + endWord + " are not legitimate 5-letter words from the dictionary.");
    	}
    	List<String> ladder = MakeLadder(startWord, endWord);
    	if(ladder == null){
        	System.out.println("For the input words '" + startWord + "' and '" + endWord + "' the following word ladder was found");
    		throw new NoSuchLadderException("There is no word ladder between " + startWord + " and " + endWord + ".");
    	}
    	return ladder;
    	
        //throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder)
    {
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
        
    	//TODO: throw new UnsupportedOperationException("Not implemented yet!");
    	//what is this for?
    }

	//add additional methods here
    public List<String> MakeLadder(String fromWord, String toWord) {
    	//TODO I've figured out what's wrong with the BFS, I'll fix it later
    	Map<String, String> parentMap = new HashMap<String, String>();
    	if(isOneLetterOff(fromWord, toWord) || fromWord.equals(toWord)){ //if input is same or one letter off
    		parentMap.put(toWord, fromWord);
    		List<String> ladder = ladderToList(fromWord, toWord, parentMap);
    		return ladder;
    	}
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
    	return null;
    	//no ladder could be found
    	//throw new NoSuchLadderException("No ladder could be found between " + fromWord + " and " + toWord + ".");
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
    	System.out.println("For the input words '" + startWord + "' and '" + endWord + "' the following word ladder was found");
    	for(String word : wordLadder){
    		System.out.print(word + " ");
    	}
    	System.out.println("\n**********");
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


