package assign09;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashTableTester {
	
	private HashTable<Integer, Integer> emptyIntegerHashTable = new HashTable<>();
	private HashTable<String, String> oneHashTable = new HashTable<>();
	private HashTable<Integer, Integer> largeHashTable = new HashTable<>();

	private Random random = new Random();

	@BeforeEach
	void setUp() throws Exception {
		oneHashTable.put("a", "a");

		largeHashTable.put(1, 1);
		largeHashTable.put(2, 2);
		largeHashTable.put(3, 3);
		largeHashTable.put(4, 4);
		largeHashTable.put(5, 5);
		largeHashTable.put(101, 101);
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
	
	@Test
	void largeContainsKey() {
		assertTrue(largeHashTable.containsKey(1));
		assertTrue(largeHashTable.containsKey(101));
		assertTrue(largeHashTable.containsKey(5));
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
	
	@Test
	void largeContainsValue() {
		assertTrue(largeHashTable.containsValue(1));
		assertTrue(largeHashTable.containsValue(101));
		assertTrue(largeHashTable.containsValue(5));
	}
	
//entries()
	@Test
	void oneEntries() {
		ArrayList<MapEntry<String, String>> expected = new ArrayList<>();
		expected.add(new MapEntry<String, String>("a", "a"));
		assertEquals(expected, oneHashTable.entries());
	}
	
	@Test
	void largeEntries() {
		ArrayList<MapEntry<Integer, Integer>> list = new ArrayList<>();
		list.add(new MapEntry<Integer, Integer>(1, 1));
		list.add(new MapEntry<Integer, Integer>(101, 101));
		list.add(new MapEntry<Integer, Integer>(2, 2));
		list.add(new MapEntry<Integer, Integer>(3, 3));
		list.add(new MapEntry<Integer, Integer>(4, 4));
		list.add(new MapEntry<Integer, Integer>(5, 5));
		
		assertEquals(list, largeHashTable.entries());
	}
	
//get()
	@Test
	void oneGet() {
		assertEquals("a", oneHashTable.get("a"));
	}
	
	@Test
	void largeGet() {
		assertEquals(1, largeHashTable.get(1));
		assertEquals(2, largeHashTable.get(2));
		assertEquals(3, largeHashTable.get(3));
		assertEquals(4, largeHashTable.get(4));
		assertEquals(5, largeHashTable.get(5));
		assertEquals(101, largeHashTable.get(101));
	}
	
//isEmpty()
	@Test
	void emptyIsEmpty() {
		assertTrue(emptyIntegerHashTable.isEmpty());
	}
	
	@Test
	void oneIsEmpty() {
		assertFalse(oneHashTable.isEmpty());
	}
	
	@Test
	void largeIsEmpty() {
		assertFalse(largeHashTable.isEmpty());
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
	
	@Test
	void putReturnNull() {
		assertEquals(null, largeHashTable.put(6, 6));
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
	
	@Test
	void removeReturnNull() {
		assertEquals(null, largeHashTable.remove(6));
	}
	
//size()
	@Test
	void emptySize() {
		assertEquals(0, emptyIntegerHashTable.size());
	}

	@Test
	void oneSize() {
		assertEquals(1, oneHashTable.size());
	}

	@Test
	void largeSize() {
		assertEquals(6, largeHashTable.size());
	}
}
