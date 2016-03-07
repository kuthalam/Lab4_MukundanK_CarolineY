package assignment4;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.List;

public class WordLadderTester {
	
	@Test
	public void test() {
		WordLadderSolver wordLadderSolver = new WordLadderSolver();
		List<String> result = null;
		try {
			result = wordLadderSolver.computeLadder("aback", "abate");
		}
		catch (NoSuchLadderException e) {
			System.out.println("This should not even happen");
		}
		assert(wordLadderSolver.validateResult("aback", "abate", result) == true); //Why does this not work?
		
		//The second test case
		try {
			result = wordLadderSolver.computeLadder("devil", "angel");
		}
		catch (NoSuchLadderException e) {
			System.out.println("The exception was thrown");
		}
		NoSuchLadderException error = new NoSuchLadderException("No ladder could be found between devil and angel.");
		assert(result.equals(error));
	}
}
