package model;

import javax.print.DocFlavor;
import java.util.List;

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
        for (int days : birthdays) {
           personWithBirthday[days]++;
        }

        int count = 0;
        for (int persons: personWithBirthday) {
            if (persons == 1) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int findFirstNonBirthday() {
        boolean[] hasBirthday = new boolean[MAX_DAY +1];
        for (int birthday : birthdays) {
            hasBirthday[birthday] = true;
        }
        for (int i = 1; i < hasBirthday.length; i++) {
            if (!hasBirthday[i]) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int maxPeopleWithSameBirthday() {
        int[] peopleWithBirthday = new int[MAX_DAY +1];
        for (int birthday : birthdays) {
            peopleWithBirthday[birthday] ++;
        }

        for (int i = 1; i < peopleWithBirthday.length; i++) {

        }
        return 0;
    }

    @Override
    public int maxPeopleWithBirthdayInSameWeek() {
        int[] b = new int[MAX_DAY +1];
        for (int birthday : birthdays) {
            b[birthday] ++;
        }

        int max = b[1] + b[2] + b[3] + b[4] + b[5] + b[6] + b[7]; //initialy the num of birthdays for the first week
        for (int i = 8; i < b.length ; i++) {
            int nextWeek = max - b[i-7] + b[i];
            if (nextWeek > max) {
                max = nextWeek;
            }
        }
        return max;
    }

    @Override
    public int findMedianBirthday() {
        return super.findMedianBirthday();
    }

    @Override
    public List<Integer> findBirthdaysCoveringHalfOfThePeople() {
        return super.findBirthdaysCoveringHalfOfThePeople();
    }


    // TODO override other methods that can be accelerated with help of auxiliary arrays.

}
