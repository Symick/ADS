import java.util.ArrayList;

public class ASimpleList<I> implements SimpleList<I> {

    private I[] arr = (I[]) new Object[1] ;
    private int count = 0;
    private int sizeInMemory = 1;
    @Override
    public int size() {
        return count;
    }

    @Override
    public void add(I item) {
        if (count == sizeInMemory) {
            growSize();
        }
        arr[count] = item;
        count++;
    }

    @Override
    public I get(int index) {
        return arr[index];
    }

    @Override
    public int getIndex(I item) {
        for (int i = 0; i < count; i++) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public I remove(int index) {
        if (index < 0 || index > count) {
            return null;
        }
        I item = arr[index];
        for (int i = index; i < count - 1; i++) {
            arr[i] = arr[i+1];
        }
        // set last item in the list to null
        arr[count - 1] = null;
        count--;
        return item;
    }

    private void growSize() {
        I[] temp =(I[]) new Object[sizeInMemory * 2];

        //copy array
        for (int i = 0; i < sizeInMemory; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
        sizeInMemory *= 2;
    }

}
