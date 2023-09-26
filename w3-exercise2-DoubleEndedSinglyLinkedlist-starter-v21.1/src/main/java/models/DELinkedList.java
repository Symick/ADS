package models;

import java.util.AbstractList;
import java.util.Iterator;

/**
 * Double Ended singly linked list implementation
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
        // TODO: find the node at the index position and return the item from the node
        int position = 0;
        Node current = head;
        while (index != position) {
            position++;
            current = current.next;
        }
        return current.item;
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
     * @param index
     * @param item
     * @throws IndexOutOfBoundsException if the index position is out of range
     */
    @Override
    public void add(int index, E item) {
        Node newNode = new Node(item);
        // TODO: check the index parameter against the range of validity
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        //if the list is empty head an tail become the new node
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

        if(index == size) {
            tail.attachInsert(newNode);
            tail = newNode;
            size++;
            return;
        }


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
            Node removedNode = prev.removeNext();
            prev.next = removedNode.next;
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
            next = null;
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
