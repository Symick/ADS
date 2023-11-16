package struct;

import java.util.Arrays;


/**
 * @author Gerke de Boer
 * Heap implementatie met array, waarbij de array vanaf index 1 gevuld wordt.
 * 
 * @param <Item>
 */
public class Heap<Item extends Comparable<Item>> {

	private Item[] itemList;
	private final static int INITIAL_SIZE = 20;
	private int arraySize;
	private int heapSize;

	// start an empty Heap, but initialize array
	@SuppressWarnings("unchecked")
	public Heap() {
		super();
		itemList = (Item[]) new Comparable[INITIAL_SIZE];
		heapSize = 0;
		arraySize = INITIAL_SIZE;
	}

	public void addItem(Item newItem) { /* toevoegen op juiste plek, gebruik swim */ }

	public void addArrayOfItems(Item[] list) { /* array toevoegen aan een bestaande heap */ }

	public void makeHeap(Item[] list) { /* maakt nieuwe Heap op basis van array */ }

	public Item removeItem() { /* return bovenste item, maakt Heap in orde, gebruik sink() */  return null;}

	private void doubleSize() { /* resize van de itemList door grootte te verdubbelen */ }

	private void swim(int index) { /* een item zwemt naar boven, zorg dat je heapified */ }

	private void sink(int index) { /* een item zinkt naar beneden, zorg dat je heapified */ }

	public String toString() { /* maakt een String in de vorm van [item1, item2, ..... , ] */  return null;}

}
