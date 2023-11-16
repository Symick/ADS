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

	private int getLeftChild(int index) {
		return index * 2;
	}

	private int getRightChild(int index) {
		return index * 2 - 1;
	}

	private int getParent(int index) {
		return index / 2;
	}

	public void addItem(Item newItem) {
		if (heapSize + 1 == arraySize) {
			ensureCapacity();
		}
		itemList[heapSize + 1] = newItem;
		heapSize++;
		swim(heapSize);
	}

	public void addArrayOfItems(Item[] list) {
		for (Item item: list) {
			addItem(item);
		}
	}

	public void makeHeap(Item[] list) {
		itemList = (Item[]) new Comparable[INITIAL_SIZE];
		heapSize = 0;
		arraySize = INITIAL_SIZE;

		for (Item item: list) {
			addItem(item);
		}
	}

	public Item removeItem() {
		Item root = itemList[1];
		swap(1, heapSize);
		heapSize--;
		sink(1);
		return root;
	}


	private void swim(int index) {
		while(index > 1 && itemList[index].compareTo(itemList[getParent(index)]) < 0) {
			swap(index, getParent(index));
			index = getParent(index);
		}
	}

	private void sink(int index) {
		while (getLeftChild(index) <= heapSize) {
			int childIndex = getLeftChild(index);
			if (itemList[childIndex].compareTo(itemList[childIndex + 1]) > 0 && childIndex < heapSize) {
				childIndex++; // set the current child index to the right child.
			}

			//if no child is smaller than the parent, heap is already heapified
			if (itemList[childIndex].compareTo(itemList[index]) > 0) {
				break;
			}
			swap(index, childIndex);

			index = childIndex;
		}
	}

	private void ensureCapacity() {
		arraySize *= 2;
		itemList = Arrays.copyOf(itemList, arraySize);
	}

	private void swap( int index1, int index2) {
		Item temp = itemList[index1];
		itemList[index1] = itemList[index2];
		itemList[index2] = temp;
	}
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		for (int i = 1; i <= heapSize; i++) {
			str.append(itemList[i].toString());
			if (i != heapSize) {
				str.append(", ");
			}
		}
		str.append("]");
		return str.toString();
	}

}
