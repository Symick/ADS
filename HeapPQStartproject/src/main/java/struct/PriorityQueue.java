package struct;


public class PriorityQueue<I extends Comparable<I>> {

	private Heap<I> myHeap;
	private int pqSize;
	
	public PriorityQueue() {
		super();
		/* klaar zetten van de Heap en .....? */
	}
	
	public void enqueue(I newItem) { /* toevoegen op de juiste plek */ }
	
	public I dequeue() { /* geeft het Item terug dat aan de beurt is en verwijdert het */ return null;}
	
	public void addArrayToPQ(I[] nieuweLijst) { /* array met items toevoegen op basis van priority */}
	
	public String toString() { /* geeft de lijst als String in volgorde van prioriteit */ return null;	}
}
