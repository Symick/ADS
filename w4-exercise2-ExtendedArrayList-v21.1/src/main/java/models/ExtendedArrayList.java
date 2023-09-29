package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class ExtendedArrayList<E> extends ArrayList<E> {

    public ExtendedArrayList() {
        super();
    }

    /**
     * finds the first item in the list which matches the filter
     * @param filter
     * @return  the first item found, or null if none matches
     */
    public E getFirst(Predicate<E> filter) {
        for (E item: this) {
            if (filter.test(item)) {
                return item;
            }
        }
        return null;
    }

    /**
     * finds the last item in the list which matches the filter
     * @param filter
     * @return  the last item found, or null if none matches
     */
    public E getLast(Predicate<E> filter) {
        E foundItem = null;

        for (E item: this) {
            if(filter.test(item)) {
                foundItem = item;
            }
        }

        return foundItem;
    }

    /**
     * retains only those elements in the list that match the filter
     * @param filter
     * @return  whether one or more elements have been removed
     */
    public boolean retainIf(Predicate<E> filter) {
        boolean itemsHaveBeenRemoved = false;

        // TODO use the super class iterator to check all items
        //   and remove those who do not match the filter
        Iterator<E> iterator = super.iterator();
        while(iterator.hasNext()) {
            E item = iterator.next();
            if (!filter.test(item)) {
                iterator.remove();
                itemsHaveBeenRemoved = true;
            }
        }

        return itemsHaveBeenRemoved;
    }

    /**
     * Remove successive duplicates from the array using the equalityTester to verify
     * whether two items are equal.
     * This methods assumes than the arraylist has been sorted in a way that any duplicates are
     * stored in successive positions within the list.
     * @param equalityTester returns true if two items are equal
     * @return  whether one or more elements have been removed
     */
    public boolean removeSuccessiveDuplicates(BiPredicate<E,E> equalityTester) {
        boolean itemsHaveBeenRemoved = false;
        int index = 0;
        while (index < this.size() -1) {
            E current = this.get(index);
            E next = this.get(index + 1);

            if (equalityTester.test(current, next)) {
                //if a item is removed don't increment the index, so that potential triple duplicates are spotted
                this.remove(index + 1);
                itemsHaveBeenRemoved = true;
            } else {
                index++; //if nothing is removed go to the next item in the list
            }
        }

        return itemsHaveBeenRemoved;
    }
}
