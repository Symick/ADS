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
        int[] personWithBirthday = new int[MAX_DAY +1];
        for (int i = 1; i <= MAX_DAY; i++) {
            personWithBirthday[i] = countBirthdaysOn(i);
        }

        int count = 0;
        for (int persons: personWithBirthday) {
            if (persons == 1) {
                count++;
            }
        }
        return count;
    }

    // TODO override other methods that can be accelerated with help of auxiliary arrays.

}
