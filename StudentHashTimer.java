package assign09;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

public class StudentHashTimer {
	
	private static String allCharacters = "abcdefghijklmnopqrstuvwxyz";

	private static Random rng = new Random();
	
	public static void main(String[] args) {
		Random rng = new Random(1);
		System.out.println("N\tnanoTime");
		
		
		int incr = 1000;
		for (int probSize = 1000; probSize <= 100000; probSize += incr) {

			int timesToLoop = 2;
			
			HashTable<StudentBadHash, Integer> badHashCode = new HashTable<>();
			HashTable<StudentMediumHash, Integer> mediumHashCode = new HashTable<>();
			HashTable<StudentGoodHash, Integer> goodHashCode = new HashTable<>();

			
				
			
			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			long stopTime, midpointTime, startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {
			}

			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {	
				for (int j = 0; j < probSize; j++) {
					int uid = rng.nextInt(99999999);
	
					StringBuilder firstNameBuilder = new StringBuilder();
					StringBuilder lastNameBuilder = new StringBuilder();
	
					for (int k = 0; k < rng.nextInt(5); k++)
						firstNameBuilder.append(allCharacters.charAt(rng.nextInt(26)));
					for (int k = 0; k < rng.nextInt(10); k++)
						lastNameBuilder.append(allCharacters.charAt(rng.nextInt(26)));
	
					String firstName = firstNameBuilder.toString();
					String lastName = lastNameBuilder.toString();
	
					badHashCode.put(new StudentBadHash(uid, firstName, lastName), 0);
	//				mediumHashCode.put(new StudentMediumHash(uid, firstName, lastName), 0);
//					goodHashCode.put(new StudentGoodHash(uid, firstName, lastName), 0);
				}
			}

			midpointTime = System.nanoTime();

			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			for (int i = 0; i < timesToLoop; i++) {
				for (int j = 0; j < probSize; j++) {
					int uid = rng.nextInt(99999999);
	
					StringBuilder firstNameBuilder = new StringBuilder();
					StringBuilder lastNameBuilder = new StringBuilder();
	
					for (int k = 0; k < rng.nextInt(5); k++)
						firstNameBuilder.append(allCharacters.charAt(rng.nextInt(26)));
					for (int k = 0; k < rng.nextInt(10); k++)
						lastNameBuilder.append(allCharacters.charAt(rng.nextInt(26)));
	
					String firstName = firstNameBuilder.toString();
					String lastName = lastNameBuilder.toString();

				}
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and searching.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;

			System.out.println(probSize + "\t" + String.format("%.5f", averageTime));
		}
	}
}

