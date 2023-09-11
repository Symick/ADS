public class DESLSimpleList<I> implements SimpleList<I> {
    private Node head;
    private Node tail;
    private int size = 0;

    public DESLSimpleList() {
        head = null;
        tail = null;
    }

    private class Node {
        I item;
        Node next;

        public Node(I item) {
            this.item = item;
            next = null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public I get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = this.head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    @Override
    public void add(I item) {
        Node newNode = new Node(item);

        if (head == null) {
            head = newNode;
            tail = newNode;
            size ++;
        } else {
            //the current last node will get linked to the new node
            tail.next = newNode;
            //make the new node the current last node
            tail = newNode;
            size++;
        }
    }

    @Override
    public I remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node current = this.head;
        Node prev = null;
        if (index == 0) {
            I item = current.item;
            head = current.next;
            current.next = null;
            return item;
        }
        for (int i = 0; i < index; i++) {
            prev = current;
            current = current.next;
        }
        I item = current.item;
        //make the previous node link to the node after the node which is being removed
        prev.next = current.next;
        current.next = null;
        //if index was last item set tail to the previous node
        if (index == size -1) {
            tail = prev;
        }
        //decrement listsize
        size--;
        return item;
    }

    @Override
    public int getIndex(I item) {
        Node node = this.head;
        int pos = 0;
        while(node != null) {
            //check for match in the list
            if (node.item.equals(item)) {
                return pos;
            }
            pos++;
            node = node.next;
        }
        //item not in list
        return -1;
    }
}
