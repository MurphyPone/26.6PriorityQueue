/**
 * Implements a priority queue based on a max-heap so that the values are sorted in descending order
 */


//ASSIGNMENT: Implement a priority queue of Integer objects as an ArrayList<Integer>, sorted in descending order.  Use Binary Search to find the place where a new value is to be inserted.  Is this more or less efficient than a heap? Explain.

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class HeapPriorityQueue {
  private ArrayList<Integer> items;	//The things we store
  private int numItems;	//The numItems currently in items[]
  private static final int CAPACITY = 101;

  // CONSTRUCTORS //
  public HeapPriorityQueue() {
	  items = new ArrayList<Integer>(CAPACITY);
  }

  public HeapPriorityQueue(int initialCapacity) {
    items = new ArrayList<Integer>(initialCapacity);
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
   * Adds obj to this priority queue; returns true.
   */
  public boolean add(Integer num) {
    numItems++;
    items.add(num);
    reheapUp();
    return true;
  }

  /**
   * Removes and returns the highest priority item.
   */
  public Integer remove() {
    if (numItems == 0) { throw new NoSuchElementException(); }

    Integer minNum = items.get(1);
    items.set(1, items.get(numItems) );
    numItems--;
    reheapDown();
    return minNum;
  }

  //**************************************************************************

  @SuppressWarnings("unchecked")
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
  			if(index > 0 && index < items.size()-2) { 	 //Make sure there are no IOOB
	  			if(greaterThan(items.get(left), items.get(right)) && greaterThan(items.get(right), items.get(left)) )
	  					break;
	  			if(greaterThan(items.get(left), items.get(right))) {
	  				swap(right, index);
	  				index = right;
	  			} else {
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
  		
  		while(index > 0 && greaterThan(items.get(index), items.get(parentIndex)) ) {
  			swap(index, parentIndex);
  			parentIndex /= 2;
  		}
  	}
  	
  	//swap helper
  	private void swap(Integer x, Integer y) {
  		Integer temp = items.get(x); 
  		items.set(x, y);
  		items.set(y,  temp);
  	}
  	
  	//toString
  	public String toString() {
  		return items.toString();
  	}
  	
  	public static void main(String[] args) {
  		HeapPriorityQueue mine = new HeapPriorityQueue();
  		
  		//Fill in the values
  		for(int i = 0; i < 10; i++) {
  			Integer num = (int) (Math.random() * 50 + 1);
  			mine.add(num);
  		}
  		
  		System.out.println(mine);
  		
  		
  		
  	}
}

