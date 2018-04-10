import java.util .ArrayList;

public class BinSearchPriorityQueue {
	private ArrayList<Integer> items;
	
	public BinSearchPriorityQueue() {	//Constructor
		items = new ArrayList<Integer>();
	}
	
	public BinSearchPriorityQueue(int initCap) {	//Constructor with capacity arg
		items = new ArrayList<Integer>(initCap);
	}
	
	public boolean isEmpty() { return items.isEmpty(); }
	public Integer remove() { return items.remove(items.size() - 1 ); }
	public Integer peek() { return items.get(items.size() - 1 ); }

	public void add(Integer x) {
		int left = 0;
		int right = items.size() - 1;
		int middle;
		
		while(left <= right) {
			middle = (left + right) / 2;
			
			if(x.compareTo(items.get(middle)) < 0 )
				left = middle + 1;
			else 
				right = middle - 1;
		}
		items.add(left, x);
	}
	
	public static void main(String[] args) {
		BinSearchPriorityQueue bsq = new BinSearchPriorityQueue(10);
		
		for(int i = 0; i < 10; i++ ) {
			int num = (int) (Math.random() * 15 + 1);
			bsq.add(num); //fill nums with a random # 0-10
			System.out.print(num + " ");
		}
		
		System.out.println("\n--------------------");
		
		while(!bsq.isEmpty() ) 
			System.out.print(bsq.remove() + " ");
	}

}
