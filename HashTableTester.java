package assign09;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashTableTester {
	
	private HashTable<Integer, Integer> emptyIntegerHashTable = new HashTable<>();
	private HashTable<String, String> oneHashTable = new HashTable<>();

	private Random random = new Random();

	@BeforeEach
	void setUp() throws Exception {
		oneHashTable.put("a", "a");
		
	}

//clear()	
	@Test
	void testClear() {
		oneHashTable.clear();
		assertEquals(0, oneHashTable.size());
	}
	
//containsKey()
	@Test
	void oneContainsKey() {
		assertTrue(oneHashTable.containsKey("a"));
	}
	
	@Test
	void oneDoesNotContainsKey() {
		assertFalse(oneHashTable.containsKey("b"));
	}
	
//containsValue()
	@Test
	void oneContainsValue() {
		assertTrue(oneHashTable.containsValue("a"));
	}

	@Test
	void oneDoesNotContainValue() {
		assertFalse(oneHashTable.containsValue("b"));
	}
	
//entries()
	@Test
	void oneEntries() {
		ArrayList<MapEntry<String, String>> expected = new ArrayList<>();
		expected.add(new MapEntry<String, String>("a", "a"));
		assertEquals(expected, oneHashTable.entries());
	}
	
//get()
	@Test
	void oneGet() {
		assertEquals("a", oneHashTable.get("a"));
	}
	
//isEmpty()
	@Test
	void oneIsEmpty() {
		assertFalse(oneHashTable.isEmpty());
	}
	
//put()
	@Test
	void onePutReplace() {
		assertEquals("a", oneHashTable.put("a", "b"));
		assertEquals("b", oneHashTable.get("a"));
	}
	
	@Test
	void emptyLargePut() {
		for (int i = 0; i < 1000; i++) 
			emptyIntegerHashTable.put(random.nextInt(200), random.nextInt(100));
		
		emptyIntegerHashTable.put(1, 999);
		assertEquals(999, emptyIntegerHashTable.get(1));
	}
	
//remove()
	@Test
	void oneRemove() {
		assertEquals("a", oneHashTable.remove("a"));
	}
	
	@Test
	void emptyLargeRemove() {
		for (int i = 0; i < 1000; i++) 
			emptyIntegerHashTable.put(i*7, i);
		
		for (int i = 0; i < 500; i++) 
			emptyIntegerHashTable.remove(i*7);
		
		assertEquals(500, emptyIntegerHashTable.size());
	}
}
