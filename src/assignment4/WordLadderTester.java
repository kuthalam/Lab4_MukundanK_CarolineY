package assignment4;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.List;

public class WordLadderTester {
	
	@Test
	public void validateResultTest() {
		WordLadderSolver wordLadderSolver = new WordLadderSolver("A4-words.txt");
		List<String> result = null;
		try {
			result = wordLadderSolver.computeLadder("aback", "abate");
			assertTrue(wordLadderSolver.validateResult("aback", "abate", result));
			
			result = wordLadderSolver.computeLadder("zonks", "yolky");
			assertTrue(wordLadderSolver.validateResult("zonks", "yolky", result));
		}
		catch (NoSuchLadderException e) {
			fail("There should be no exception thrown.");
		}
		
	}
		
	@Test
	public void test2(){
		WordLadderSolver wordLadderSolver = new WordLadderSolver("A4-words.txt");
		List<String> result = null;
		try {
			result = wordLadderSolver.computeLadder("aback", "agora");
			fail("This should throw an exception");
		}
		catch (NoSuchLadderException e) {
			System.out.println("An exception was thrown, good.");
		}
		NoSuchLadderException e1 = new NoSuchLadderException("No ladder could be found between aback and agora.");
		assert(result.equals(e1));
		
	}
	
	@Test
	public void test3() {
		WordLadderSolver wordLadderSolver = new WordLadderSolver("A4-words.txt");
		List<String> result = null;
		try {
			result = wordLadderSolver.computeLadder("john", "elway");
			fail("This should throw an exception");
		}
		catch (NoSuchLadderException e) {
			System.out.println("An exception was thrown, good.");
		}
		NoSuchLadderException e2 = new NoSuchLadderException("At least one of the words john and elway are not legitimate 5-letter words from the dictionary.");
		assert(result.equals(e2));
	}
	
	@Test
	public void test4() {
		WordLadderSolver wordLadderSolver = new WordLadderSolver("A4-words.txt");
		List<String> result = null;
		try {
			result = wordLadderSolver.computeLadder("devil", "angel");
			fail("This should throw an exception");
		}
		catch (NoSuchLadderException e) {
			System.out.println("An exception was thrown, good.");
		}
		NoSuchLadderException e3 = new NoSuchLadderException("There is no word ladder between devil and angel.");
		assert(result.equals(e3));
	}
}
