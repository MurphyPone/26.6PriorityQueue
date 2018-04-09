/**
 * Implements a priority queue based on a max-heap so that the values are sorted in descending order
 */


/* ASSIGNMENT: Implement a priority queue of Integer objects as an ArrayList<Integer>, sorted in descending order.  
 * Use Binary Search to find the place where a new value is to be inserted.  Is this more or less efficient than a heap? Explain.
 */

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class HeapPriorityQueue {
	private ArrayList<Integer> items;	//The things we store
	private int numItems;	//The numItems currently in items[]
	private static int CAPACITY = 101;

	// CONSTRUCTORS //
	public HeapPriorityQueue() { 
		items = new ArrayList<Integer>(CAPACITY); 
		numItems = 0;
		items.add(null); //add null to the list for correct indexing
	}

	public HeapPriorityQueue(int initialCapacity) {
		CAPACITY = initialCapacity;
		items = new ArrayList<Integer>(CAPACITY);
    		numItems = 0;
    		items.add(null); //add null to the list for correct indexing
	}

	/**
	 * Returns true if this priority queue is empty;
	 * otherwise returns false.
	 */
	public boolean isEmpty() { return numItems == 0; }
	
	/**
	 * Returns the highest priority element without removing
	 * it from this priority queue.
	 */
	public Object peek() {
		if (numItems == 0) { throw new NoSuchElementException(); }
		return items.get(1);
	}

	/**
	 * Adds an integer to this priority queue; returns true.
	 */
	public boolean add(Integer num) {
		numItems++;
		items.add(num);
		reheapUp();
		return true;
	}	

	/**
	 * Removes and returns the highest priority item. --the root
	 */
	public Integer remove() {
		if (numItems == 0) { throw new NoSuchElementException(); }
		Integer minNum = items.get(1);
		items.set(1, items.get(numItems) );
		numItems--;
		reheapDown();
		return minNum;
	}

	//Simplified comparison methods bc using easy Integer type 
	private boolean lessThan(Integer a, Integer b) {
		return (a - b) < 0;
	}
  
	private boolean greaterThan(Integer a, Integer b) {
		return (a - b) >= 0;
	}

  	//TODO BE ABLE TO REPRODUE BOTH OF THESE ALGORITHMS ON A TEST
  	/** 
  	Remove the root and place
	the last leaf at the root.
	Starting at the root, swap
	the node with its smaller
	child, as many times as
	needed to repair the heap
	
	Precondition : the last item is now the root, and the heap is complete
  	**/
	private void reheapDown() {
		int index = 1;
		while(index < numItems) {
			int left = 2 * index;
			int right = 2 * index + 1;
			if(index > 0 && index < items.size()-1) { 	 //Make sure there are no IOOB
				if(greaterThan(items.get(left), items.get(right)) && greaterThan(items.get(right), items.get(left)) ) //weird conflict
					break;
				if(greaterThan(items.get(left), items.get(right))) { //items[left] > items[right]
					swap(right, index);
					index = right;
				} else { //items[right] > items[left]
					swap(left, index);
					index = left;		
				}
			}
		} 
	}
  	
  	/** 
  	Add a leaf. Starting at the
	last leaf, swap the node with
	its parent as many times as
	needed to repair the heap.
	
	Precondition : the leaf has already been added and the heap is complete
  	**/
	private void reheapUp() {
		int index = numItems; //last item
		int parentIndex = index/2; //index of the parent	
		while(parentIndex > 0 && greaterThan(items.get(index), items.get(parentIndex)) ) {
			swap(index, parentIndex);
			parentIndex /= 2;
		}
	}
  	
  	//swap helper
	private void swap(int x, int y) {
		//Object temp = items[x];
		//items[x] = items[y];
		//items[y] = temp;
		
		Integer temp = items.get(x); 
		items.set(x, items.get(y) );
		items.set(y, temp);
	}
  	
	//toString
	public String toString() {
		return items.toString();
	}
	
	public int getSize() {
		return numItems-1;
	}
	
	public boolean insert(Integer x) {
		return true;
	}
  	
	public static void main(String[] args) {
		HeapPriorityQueue hpq = new HeapPriorityQueue();
  		
		//Fill in with random values
		for(int i = 0; i < 10; i++) {
			Integer num = (int) (Math.random() * 50 + 1);
			hpq.add(num);
		}
		System.out.println(hpq);
		
		ArrayList<Integer> sorted = new ArrayList<Integer>(hpq.getSize()); 

		while(!hpq.isEmpty())
			sorted.add(hpq.remove() );
		System.out.println(sorted);
  		
  		
	}
}

