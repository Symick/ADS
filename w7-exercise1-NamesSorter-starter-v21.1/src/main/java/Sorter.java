

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Sorter<E> {

    private Random randomizer;

    public Sorter() {
        this(0L);
    }
    public Sorter(long seed) {
        this.randomizer = new Random(seed);
    }

    public void shuffle(E[] items) {
        for (int i = 0; i < items.length; i++) {
            int j = this.randomizer.nextInt(items.length);
            E temp = items[i]; items[i] = items[j]; items[j] = temp;
        }
    }

    /**
     * Sorts all items by bubble sort
     * detects when all items in the list are sorted before processing the complete outer loop
     * @param items
     * @param comparator
     * @return  the items sorted in place
     */
    public List<E> bubbleSortUntilDone(List<E> items, Comparator<E> comparator) {

        int lastIndex = items.size();
        boolean somethingHasBubbled;
        do {
            lastIndex--;
            somethingHasBubbled = false;
            int index = 0;
            // TODO bubble larger elements in items[0..lastIndex] to the right and track swaps in somethingHasBubbled
            while (index < lastIndex) {
                E firstItem = items.get(index);
                E secondItem = items.get(index + 1);
                if (comparator.compare(firstItem, secondItem) > 0) {
                    items.set(index, secondItem);
                    items.set(index + 1, firstItem);
                    somethingHasBubbled = true;
                }
                index++;
            }

            // largest element has arrived in items[lastIndex]
        } while (somethingHasBubbled && lastIndex > 1);

        return items;
    }

    /**
     * Sorts all items by quick sort using the provided comparator
     * for deciding relative ordening of two items
     * Items are sorted 'in place' without use of an auxiliary list or array
     * @param items
     * @param comparator
     * @return  the items sorted in place
     */
    public List<E> mergeSortViaAuxiliary(List<E> items, Comparator<E> comparator) {
        // sort the complete items from position 0 till size-1, encluding position size
        E[] auxiliary = (E[])new Object[items.size()];
        this.mergeSortPartViaAuxiliary(items, auxiliary, 0, items.size()-1, comparator);
        return items;
    }


    /**
     * Sorts all items between index positions from and to inclusive by quick sort using the provided comparator
     * for deciding relative ordening of two items
     * Items are sorted 'in place' without use of an auxiliary list or array or other positions in items
     *
     * @param items
     * @param comparator
     * @return  the items sorted in place
     */
    private void mergeSortPartViaAuxiliary(List<E> items, E[] auxiliary, int from, int to, Comparator<E> comparator) {

        // sort the partial items from position from till to,
        if (from >= to) {
            // only one item or less to sort...
            return;
        }
        int mid = (from+to)/2;

        // TODO recursively sort the left and the right parts and merge the result
        mergeSortPartViaAuxiliary(items, auxiliary, from, mid, comparator);
        mergeSortPartViaAuxiliary(items, auxiliary, mid + 1, to, comparator);

        mergeViaAuxiliary(items, auxiliary, from, mid, to, comparator);
    }

    /**
     * merges the sorted list parts items[from..mid] and items[mid+1..to]
     * into items[from..to]
     * using the auxiliary array for intermediate storage
     * no additional local data structures should be required
     * @param items
     * @param auxiliary
     * @param from
     * @param mid
     * @param to
     * @param comparator
     */
    private void mergeViaAuxiliary(List<E> items, E[] auxiliary, int from, int mid, int to, Comparator<E> comparator) {
        for (int i = from; i <= to; i++) {
            auxiliary[i] = items.get(i);
        }
        int leftPointer = from;
        int rightPointer = mid +1;
        for (int i = from; i <= to; i++) {
            if (leftPointer > mid) {
                //left part of array is empty
                items.set(i, auxiliary[rightPointer]);
                rightPointer++;
            } else if (rightPointer > to) {
                //right part is empty
                items.set(i, auxiliary[leftPointer]);
                leftPointer++;
            } else if (comparator.compare(auxiliary[leftPointer],auxiliary[rightPointer]) <= 0) {
                items.set(i, auxiliary[leftPointer]);
                leftPointer++;
            } else {
                items.set(i, auxiliary[rightPointer]);
                rightPointer++;
            }
        }
    }

    /**
     * Sorts all items by batcher's odd-even merge sort
     * see https://en.wikipedia.org/wiki/Batcher_odd%E2%80%93even_mergesort for the pseudo code of this algorithm
     * @param items
     * @param comparator
     * @return  the items sorted in place
     */
    public List<E> batchersOddEvenMergeSort(List<E> items, Comparator<E> comparator) {
        for (int i = 1; i < items.size(); i *=2) {
            for (int j = i; j >= 1; j/=2) {
                for (int k = j % i; k <= items.size() -1 - j ; k += (j*2)) {
                    for (int l = 0; l <= Math.min(j-1, items.size() - k - j -1); l++) {
                        if (Math.floor((l+k) / (i*2.0)) == Math.floor((l + k + j) / (i*2.0))) {
                            int idx1 = k + l;
                            int idx2 = k + l + j;
                            if (idx2 >= items.size() ) continue;

                            E firstItem = items.get(idx1);
                            E secondItem = items.get(idx2);
                            if (comparator.compare(firstItem, secondItem) > 0) {
                                items.set(idx1, secondItem);
                                items.set(idx2, firstItem);
                            }
                        }
                    }
                }
            }
        }


        return items;
    }
}
