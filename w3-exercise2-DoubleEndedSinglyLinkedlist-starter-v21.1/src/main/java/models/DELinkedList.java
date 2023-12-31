package models;

import java.util.AbstractList;
import java.util.Iterator;

/**
 * Double Ended singly linked list implementation
 *
 * This list maintains the following representation invariants:
 * tail should not have any successor if any
 * - tail.next == null || tail == null
 * No node should reference the head because this is the first element
 * - node.next != head || head == null
 * No node should be reference by more than one node
 * - Node1.next == node && anotherNode.next != node
 * Any node which is not the tail should point to another node
 * node != tail && node.next == anotherNode
 * Size should be non-negative
 * - size >= 0
 *
 *
 * @param <E> the type of the items
 */
public class DELinkedList<E> extends AbstractList<E> implements Iterable<E> {
    protected int size = 0;           // current number of items in the linked list
    private Node head = null;       // first node containing the first item in the linked list
    private Node tail = null;       // last node containing the last item in the linked list

    /**
     * retrieve the item from the given index position in the list: 0 <= index < size
     *
     * @param index
     * @return the item at the given position
     * @throws IndexOutOfBoundsException if index is not within range
     */
    @Override
    public E get(int index) {
        // TODO: check the index parameter against the range of validity
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return findNode(index).item;
    }

    @Override
    public int size() {
        return this.size;
    }

    public Node findNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        int position = 0;
        Node current = head;
        while (index != position) {
            position++;
            current = current.next;
        }
        return current;
    }

    /**
     * Insert an item at the index position within the list.
     * The first position has index zero
     * The last position has index size-1
     * Existing items after the index position will have their position incremented
     * You can also append an item at the end of the list at position size
     *
     * This method should maintain the following representation invariants
     * after insert size should be one bigger and positive
     * -size > 0 && newSize == size +1;
     * predecessor of inserted node should reference inserted node in its next, or the inserted not should be the head
     * -findNode(index-1).next == newNode || newNode == head
     * Node inserted at the end should be the tail
     * index == size && new Node(item) == tail
     * @param index
     * @param item
     * @throws IndexOutOfBoundsException if the index position is out of range
     */
    @Override
    public void add(int index, E item) {
        Node newNode = new Node(item);

        //add could add node to the end of the list aka. the index which is equal to the size.
        //for example adding a node to the end of a list of 4 should be with an index of 4, the same as the size.
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        //if the list is empty head a tail become the new node
        if (size == 0) {
            head = newNode;
            tail = head;
            size++;
            return;
        }

        //insert node at front
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            size++;
            return;
        }

        //inserting a node at the end. Node is at the end when the index is equal to the size.
        //You want to add the node after the current last index.
        if(index == size) {
            tail.attachInsert(newNode);
            tail = newNode;
            size++;
            return;
        }


        //insert node in the middle.
        Node prev = findNode(index - 1);
        prev.attachInsert(newNode);
        size++;
    }


    /**
     * remove the item at given index position in the list: 0 <= index < size
     * Existing values after the index position will have their position decremented
     *
     * @param index
     * @return the removed item at the given position
     * @throws IndexOutOfBoundsException if the index position is out of range
     */
    @Override
    public E remove(int index) {
        // TODO: check the index parameter against the range of validity
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            return removeFront();
        }
        Node prev = findNode(index - 1);
        E removedItem = prev.next.item;
        //remove tail
        if (prev.next == tail) {
            prev.removeNext();
            tail = prev;

        } else {
           prev.removeNext();
        }
        size--;
        return removedItem;
    }

    public E removeFront() {
        E toRemove = head.item;
        head = head.next;
        size--;
        return toRemove;
    }


    /**
     * replace the item at the given index position in the list: 0 <= index < size
     *
     * @param index
     * @param item
     * @return the previous item at the given position
     * @throws IndexOutOfBoundsException if the index position is out of range
     */
    @Override
    public E set(int index, E item) {
        // TODO: check the index parameter against the range of validity
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        // TODO: find the node at the index position and replace its item
        Node node = findNode(index);
        E oldItem = node.item;
        node.item = item;

        // TODO: return the old item that now has been replaced
        return oldItem;
    }

    @Override
    public Object[] toArray() {
        // TODO: populate and return an Object array of items from the list
        //      use java 1.5 extende 'for( : )' syntax, once you have implemented the iterator
        Object[] array = new Object[this.size()];
        int itemCount = 0;
        for (E item: this) {
            array[itemCount] = item;
            itemCount++;
        }
        return array;
    }

    /**
     * @return an initialised iterator that will iterate from the first
     * to the last item in the list
     */
    @Override
    public Iterator<E> iterator() {
        // TODO replace below iterator by a more memory efficient, true iterator instance
        //      that iterates along all items in the list
        //      implement a local class for that, which implements the Iterator<E> interface
        // use the default list iterator for now
        return new DELLIterator();
    }

    /**
     * An inner container class to store the items in a chain of references
     */
    private class Node {
        private E item;
        private Node next = null;

        private Node(E item) {
            this.item = item;
        }
        // TODO add more private helper methods as required, e.g.

        private void attachInsert(Node newNode) {
            //connect the current successor to the new Node
            newNode.next = next;

            //connect the new node to this node
            next = newNode;
        }

        private Node removeNext() {
            Node toRemove = next;
            next = toRemove.next;

            return toRemove;
        }


        @Override
        public String toString() {
            return item.toString();
        }
    }

    private class DELLIterator implements Iterator<E> {
        private Node current;
        private Node previousNode;
        private Node nextNode;
        private boolean removeAllowed;

        private DELLIterator() {
            current = null;
            previousNode = null;
            nextNode = head;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public E next() {
            E item = nextNode.item;
            previousNode = current;
            current = nextNode;
            nextNode = nextNode.next;
            removeAllowed = true;
            return item;
        }

        @Override
        public void remove() {
            if (!removeAllowed) {
                throw new IllegalStateException();
            }

            removeAllowed = false;

            if (current == head) {
                removeFront();
                return;
            }

            if (nextNode == null) {
                previousNode.removeNext();
                tail = previousNode;
                size--;
                return;
            }
            previousNode.removeNext();
            previousNode.next = nextNode;

            //reinstate the current, which was removed to be the previous so the iteration doesn't break
            // and reference the already deleted node.
            current = previousNode;
            size--;
        }
    }
}
