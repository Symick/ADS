package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.function.Function;
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

        // TODO find the last item in the list that matches filter

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

        // TODO remove successive duplicates

        return itemsHaveBeenRemoved;
    }
}
