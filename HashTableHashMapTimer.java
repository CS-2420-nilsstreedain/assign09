package assign09;

import java.util.HashMap;
//import java.util.HashMap;
import java.util.Random;

public class HashTableHashMapTimer {
	
	private static String allCharacters = "abcdefghijklmnopqrstuvwxyz";


	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Random rng = new Random();
		System.out.println("N\tnanoTime");
		
		
		int incr = 1000;
		for (int probSize = 1000; probSize <= 100000; probSize += incr) {

			int timesToLoop = 2;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			long stopTime, midpointTime, startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {
			}

			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
				HashTable<String, Integer> hashTable = new HashTable<>();
//				HashMap<String, Integer> hashMap = new HashMap<>();

				for (int j = 0; j < probSize; j++) {
					StringBuilder stringBuilder = new StringBuilder();
	
					for (int k = 0; k < rng.nextInt(20); k++)
						stringBuilder.append(allCharacters.charAt(rng.nextInt(26)));
					
					String string = stringBuilder.toString();
					
					hashTable.put(string, 0);
//					hashMap.put(string, 0);
				}
			}

			midpointTime = System.nanoTime();

			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			for (int i = 0; i < timesToLoop; i++) {
				HashTable<String, Integer> hashTable = new HashTable<>();
//				HashMap<String, Integer> hashMap = new HashMap<>();

				for (int j = 0; j < probSize; j++) {
					StringBuilder stringBuilder = new StringBuilder();
	
					for (int k = 0; k < rng.nextInt(20); k++)
						stringBuilder.append(allCharacters.charAt(rng.nextInt(26)));
					
					String string = stringBuilder.toString();
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
