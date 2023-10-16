package model;

public class FasterBirthdaysList extends BirthdaysList {
    public FasterBirthdaysList(int numberOfPeople, long seed) {
        super(numberOfPeople, seed);
    }
    public FasterBirthdaysList(int numberOfItems) {
        super(numberOfItems);
    }

    // overrides some methods with faster implementations
    // that use auxiliary arrays to speed-up the computation

    /**
     * counts the number of birthdays between 1 and 365 on which exactly one person celebrates
     * @return		number of birthdays found
     */
    @Override
    public int countNumUniqueBirthdays() {
        // TODO provide a faster implementation with use of auxiliary array
        return super.countNumUniqueBirthdays();
    }

    // TODO override other methods that can be accelerated with help of auxiliary arrays.

}
