import java.util.ArrayList;

public class ASimpleList<I> implements SimpleList<I> {

    private I[] arr;
    private int size = 0;
    private int capacity = 2;

    public ASimpleList() {
        arr = (I[]) new Object[capacity];
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(I item) {
        if (size == capacity) {
            growCapacity();
        }
        arr[size] = item;
        size++;
    }

    @Override
    public I get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        return arr[index];
    }

    @Override
    public int getIndex(I item) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public I remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        I item = arr[index];
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i+1];
        }
        // set last item in the list to null
        arr[size - 1] = null;
        size--;
        return item;
    }

    private void growCapacity() {
        I[] temp =(I[]) new Object[capacity * 2];

        //copy array
        for (int i = 0; i < capacity; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
        capacity *= 2;
    }

}
