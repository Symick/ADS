package struct;


import java.util.Arrays;

public class PriorityQueue<I extends Comparable<I>> {

	private Heap<I> myHeap;
	private int pqSize;
	
	public PriorityQueue() {
		super();
		myHeap = new Heap<>();
		pqSize = 0;
	}
	
	public void enqueue(I newItem) {
		this.myHeap.addItem(newItem);
		pqSize++;
	}
	
	public I dequeue() {
		if (pqSize <=0) {
			throw new IllegalStateException("Queue is empty");
		}

		I item = this.myHeap.removeItem();
		pqSize--;
		return item;
	}
	
	public void addArrayToPQ(I[] nieuweLijst) {
		for (I item: nieuweLijst) {
			enqueue(item);
		}
	}
	
	public String toString() {
		return myHeap.toString();
	}
}
