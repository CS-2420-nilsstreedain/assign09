package assign09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class creates a HashTable that maps keys to values and uses various
 * methods to interact with those keys and values.
 * 
 * @author Nils Streedain & Paul Nuffer
 * @version April 6, 2021
 */
public class HashTable<K, V> implements Map<K, V> {

	private ArrayList<LinkedList<MapEntry<K, V>>> table;

	private int itemCount;
	
	private int collisons;

	private final int INITIAL_BACKING_SIZE = 100;
	private final double LOAD_FACTOR_CAP = 1.5;

	public HashTable() {
		table = new ArrayList<LinkedList<MapEntry<K, V>>>();
		for (int i = 0; i < INITIAL_BACKING_SIZE; i++)
			table.add(new LinkedList<MapEntry<K, V>>());

		itemCount = 0;
	}

	/**
	 * Removes all mappings from this HashTable.
	 * 
	 * O(table length)
	 */
	@Override
	public void clear() {
		// performs the same tasks as the constructor
		itemCount = 0;
		table = new ArrayList<LinkedList<MapEntry<K, V>>>();
		for (int i = 0; i < INITIAL_BACKING_SIZE; i++)
			table.add(new LinkedList<MapEntry<K, V>>());
	}

	/**
	 * Determines whether this map contains the specified key.
	 * 
	 * O(1)
	 * 
	 * @param key
	 * @return true if this map contains the key, false otherwise
	 */
	@Override
	public boolean containsKey(K key) {
		// gets the LinkedList that the key must reside in, if it exists
		LinkedList<MapEntry<K, V>> chain = table.get(Math.abs(key.hashCode() % table.size()));

		// checks each entry in the chain for the matching key
		for (MapEntry<K, V> currEntry : chain)
			if (currEntry.getKey().equals(key))
				// exits early if a match is found
				return true;

		return false;
	}

	/**
	 * Determines whether this map contains the specified value.
	 * 
	 * O(table length)
	 * 
	 * @param value
	 * @return true if this map contains one or more keys to the specified value,
	 *         false otherwise
	 */
	@Override
	public boolean containsValue(V value) {
		for (LinkedList<MapEntry<K, V>> currChain : table)
			for (MapEntry<K, V> currEntry : currChain)
				if (currEntry.getValue().equals(value))
					return true;

		return false;
	}

	/**
	 * Returns a List view of the mappings contained in this HashTable, where the
	 * ordering of mapping in the list is insignificant.
	 * 
	 * O(table length)
	 * 
	 * @return a List object containing all mapping (i.e., entries) in this
	 *         HashTable
	 */
	@Override
	public List<MapEntry<K, V>> entries() {
		List<MapEntry<K, V>> newList = new ArrayList<MapEntry<K, V>>();

		for (LinkedList<MapEntry<K, V>> chain : table)
			for (MapEntry<K, V> currEntry : chain)
				newList.add(currEntry);

		return newList;
	}

	/**
	 * Gets the value to which the specified key is mapped.
	 * 
	 * O(1)
	 * 
	 * @param key
	 * @return the value to which the specified key is mapped, or null if this
	 *         HashTable contains no mapping for the key
	 */
	@Override
	public V get(K key) {
		LinkedList<MapEntry<K, V>> chain = table.get(Math.abs(key.hashCode() % table.size()));

		for (MapEntry<K, V> currEntry : chain)
			if (currEntry.getKey().equals(key))
				return currEntry.getValue();

		return null;
	}

	/**
	 * Determines whether this HashTable contains any mappings.
	 * 
	 * O(1)
	 * 
	 * @return true if this HashTable contains no mappings, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return itemCount == 0;
	}

	/**
	 * Associates the specified value with the specified key in this HashTable.
	 * (I.e., if the key already exists in this HashTable, resets the value;
	 * otherwise adds the specified key-value pair.)
	 * 
	 * O(1)
	 * 
	 * @param key
	 * @param value
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	@Override
	public V put(K key, V value) {
		if (((double) itemCount) / ((double) table.size()) > LOAD_FACTOR_CAP) {
			List<MapEntry<K, V>> entries = this.entries();
			int newSize = table.size() * 2;

			table = new ArrayList<LinkedList<MapEntry<K, V>>>();
			itemCount = 0;
			for (int i = 0; i < newSize; i++)
				table.add(new LinkedList<MapEntry<K, V>>());

			for (MapEntry<K, V> currEntry : entries)
				this.put(currEntry.getKey(), currEntry.getValue());
		}

		LinkedList<MapEntry<K, V>> chain = table.get(Math.abs(key.hashCode() % table.size()));

		for (MapEntry<K, V> currEntry : chain) {
			if (currEntry.getKey().equals(key)) {
				V prevValue = currEntry.getValue();
				currEntry.setValue(value);
				return prevValue;
			}
			collisons++;
		}

		itemCount++;
		chain.add(new MapEntry<K, V>(key, value));

		return null;
	}

	/**
	 * Removes the mapping for a key from this HashTable if it is present.
	 * 
	 * O(1)
	 *
	 * @param key
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	@Override
	public V remove(K key) {
		LinkedList<MapEntry<K, V>> chain = table.get(Math.abs(key.hashCode() % table.size()));

		for (MapEntry<K, V> currEntry : chain) {
			if (currEntry.getKey().equals(key)) {
				V prevValue = currEntry.getValue();

				itemCount--;
				chain.remove(currEntry);

				return prevValue;
			}
			collisons++;
		}

		return null;
	}

	/**
	 * Determines the number of mappings in this HashTable.
	 * 
	 * O(1)
	 * 
	 * @return the number of mappings in this HashTable
	 */
	@Override
	public int size() {
		return itemCount;
	}

	public int getCollisons() {
		return collisons;
	}
}
