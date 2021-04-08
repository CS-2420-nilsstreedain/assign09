package assign09;

import java.util.Random;

/**
 * This class operates similar to a timing class, but instead it prints
 * the total amount of collisions that the HashTable class reports
 * instead of outputting a time. Lower collisions indicates
 * better hash quality.
 * 
 * @author Paul Nuffer and Nils Streedain
 *
 */
public class StudentHashCodeQuality {

	private static String allCharacters = "abcdefghijklmnopqrstuvwxyz";

	private static Random rng = new Random();

	public static void main(String[] args) {
		System.out.println("ProblemSize\tbadHash\t\tmediumHash\tgoodHash");
		
		int incr = 1000;
		for (int probSize = 1000; probSize <= 100000; probSize += incr) {

			HashTable<StudentBadHash, Integer> badHashCode = new HashTable<>();
			HashTable<StudentMediumHash, Integer> mediumHashCode = new HashTable<>();
			HashTable<StudentGoodHash, Integer> goodHashCode = new HashTable<>();

			for (int i = 0; i < probSize; i++) {
				int uid = rng.nextInt(99999999);

				StringBuilder firstNameBuilder = new StringBuilder();
				StringBuilder lastNameBuilder = new StringBuilder();

				for (int j = 0; j < rng.nextInt(5); j++)
					firstNameBuilder.append(allCharacters.charAt(rng.nextInt(26)));
				for (int j = 0; j < rng.nextInt(10); j++)
					lastNameBuilder.append(allCharacters.charAt(rng.nextInt(26)));

				String firstName = firstNameBuilder.toString();
				String lastName = lastNameBuilder.toString();

				badHashCode.put(new StudentBadHash(uid, firstName, lastName), 0);
				mediumHashCode.put(new StudentMediumHash(uid, firstName, lastName), 0);
				goodHashCode.put(new StudentGoodHash(uid, firstName, lastName), 0);
			}
// commented out for 'release', needs the getCollisions method to be uncommented
// inside HashTable for this to function
//			System.out.println(
//					probSize + "\t\t" +
//					badHashCode.getCollisons() + "\t" +
//					mediumHashCode.getCollisons() + "\t\t" +
//					goodHashCode.getCollisons());
		}
	}
}
