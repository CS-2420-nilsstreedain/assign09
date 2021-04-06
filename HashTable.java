package assign09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTable<K, V> implements Map<K, V> {
	
	private ArrayList<LinkedList<MapEntry<K, V>>> table;
	private int itemCount;
	
	public HashTable() {
		table = new ArrayList<LinkedList<MapEntry<K, V>>>();
		for(int i = 0; i < 100; i++)
		   table.add(new LinkedList<MapEntry<K, V>>());
		
		itemCount = 0;
	}


	/**
	 * Removes all mappings from this map.
	 * 
	 * O(table length)
	 */
	@Override
	public void clear() {
		itemCount = 0;
		table = new ArrayList<LinkedList<MapEntry<K, V>>>();
		for(int i = 0; i < 100; i++)
		   table.add(new LinkedList<MapEntry<K, V>>());
	}

	@Override
	public boolean containsKey(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(V value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MapEntry<K, V>> entries() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
