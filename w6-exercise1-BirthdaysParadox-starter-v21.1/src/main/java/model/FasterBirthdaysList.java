package model;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
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

        int max = 0;
        for (int i = 1; i < peopleWithBirthday.length; i++) {
            if(peopleWithBirthday[i] > max) {
                max = peopleWithBirthday[i];
            }
        }
        return max;
    }

    @Override
    public int maxPeopleWithBirthdayInSameWeek() {
        int[] b = new int[MAX_DAY +1];
        for (int birthday : birthdays) {
            b[birthday] ++;
        }

        int max = 0;
        int nextWeek = b[1] + b[2] + b[3] + b[4] + b[5] + b[6] + b[7]; //initialy the num of birthdays for the first week
        for (int i = 8; i < b.length ; i++) {
            if (nextWeek > max) {
                max = nextWeek;
            }
            nextWeek = nextWeek - b[i-7] + b[i];
        }
        return max;
    }

    @Override
    public List<Integer> findAllBirthdaysWithMaxPeople() {
        List<Integer> maxPeople = new ArrayList<>();
        int maxBirthday = this.maxPeopleWithSameBirthday();
        int[] b = new int[MAX_DAY +1];
        for (int birthday : birthdays) {
            b[birthday] ++;
        }

        for (int i = 1; i < b.length; i++) {
            if (b[i] == maxBirthday) {
                maxPeople.add(i);
            }
        }
        return maxPeople;
    }

    @Override
    public int findMedianBirthday() {
        int[] b = new int[MAX_DAY +1];
        for (int birthday : birthdays) {
            b[birthday]++;
        }

        int before = 0;
        int after = birthdays.length;
        for (int i = 1; i < b.length; i++) {
            before += b[i];
            if (before >= birthdays.length / 2 && after >= birthdays.length / 2) {
                return i;
            }
            after -= b[i];
        }
        return -1;
    }

    @Override
    public List<Integer> findBirthdaysCoveringHalfOfThePeople() {
        List<Integer> birthDaylist = new ArrayList<>();
        int[] b = new int[MAX_DAY +1];
        boolean[] isIncluded = new boolean[MAX_DAY + 1];
        for (int birthday : birthdays) {
            b[birthday]++;
        }
        int total = 0;
        for (int i = 1; i <= MAX_DAY; i++) {
            int max = 0;
            int nextHighest = 0;

            for (int j = 1; j <= MAX_DAY; j++) {
                if (!isIncluded[j] && b[j] > max) {
                    max = b[j];
                    nextHighest = j;
                }
            }
            birthDaylist.add(nextHighest);
            isIncluded[nextHighest] = true;
            total += max;

            if (total >= this.birthdays.length /2) break;
        }
        return birthDaylist;
    }


    // TODO override other methods that can be accelerated with help of auxiliary arrays.

}
