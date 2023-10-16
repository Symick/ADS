package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BirthdaysListTest {
    private static final long RANDOM_SEED = 19630427;
    private static final int ONE_DAY = 123;
    private static final int FROM_DAY = 100;
    private static final int TO_DAY = 150;

    BirthdaysList small;
    BirthdaysList medium;
    BirthdaysList fasterMedium;
    BirthdaysList fasterLarge;

    @BeforeEach
    private void setup() {
        this.small = new BirthdaysList(1000, RANDOM_SEED);
        this.medium = new BirthdaysList(2000, RANDOM_SEED);
        this.fasterMedium = new FasterBirthdaysList(5000, RANDOM_SEED);
        this.fasterLarge = new FasterBirthdaysList(10000, RANDOM_SEED);
    }

    @Test
    void countBirthdaysOn() {
        assertEquals(4, this.small.countBirthdaysOn(ONE_DAY));
        assertEquals(9, this.medium.countBirthdaysOn(ONE_DAY));
        assertEquals(17, this.fasterMedium.countBirthdaysOn(ONE_DAY));
        assertEquals(30, this.fasterLarge.countBirthdaysOn(ONE_DAY));
    }

    @Test
    void countBirthdaysBetween() {
        assertEquals(143, this.small.countBirthdaysBetween(FROM_DAY, TO_DAY));
        assertEquals(303, this.medium.countBirthdaysBetween(FROM_DAY, TO_DAY));
        assertEquals(717, this.fasterMedium.countBirthdaysBetween(FROM_DAY, TO_DAY));
        assertEquals(1372, this.fasterLarge.countBirthdaysBetween(FROM_DAY, TO_DAY));
    }

    @Test
    void findLastBirthdayOfYear() {
        assertEquals(365, this.small.findLastBirthdayOfYear());
        assertEquals(365, this.medium.findLastBirthdayOfYear());
        assertEquals(365, this.fasterMedium.findLastBirthdayOfYear());
        assertEquals(365, this.fasterLarge.findLastBirthdayOfYear());
    }

    @Test
    void countNumUniqueBirthdays() {
        assertEquals(66, this.small.countNumUniqueBirthdays());
        assertEquals(11, this.medium.countNumUniqueBirthdays());
        assertEquals(0, this.fasterMedium.countNumUniqueBirthdays());
        assertEquals(0, this.fasterLarge.countNumUniqueBirthdays());
    }

    @Test
    void findFirstNonBirthday() {
        assertEquals(1, this.small.findFirstNonBirthday());
        assertEquals(266, this.medium.findFirstNonBirthday());
        assertEquals(-1, this.fasterMedium.findFirstNonBirthday());
        assertEquals(-1, this.fasterLarge.findFirstNonBirthday());
    }

    @Test
    void maxPeopleWithSameBirthday() {
        assertEquals(11, this.small.maxPeopleWithSameBirthday());
        assertEquals(13, this.medium.maxPeopleWithSameBirthday());
        assertEquals(26, this.fasterMedium.maxPeopleWithSameBirthday());
        assertEquals(42, this.fasterLarge.maxPeopleWithSameBirthday());
    }

    @Test
    void maxPeopleWithBirthdayInSameWeek() {
        assertEquals(33, this.small.maxPeopleWithBirthdayInSameWeek());
        assertEquals(57, this.medium.maxPeopleWithBirthdayInSameWeek());
        assertEquals(123, this.fasterMedium.maxPeopleWithBirthdayInSameWeek());
        assertEquals(227, this.fasterLarge.maxPeopleWithBirthdayInSameWeek());
    }

    @Test
    void findMedianBirthday() {
        assertEquals(178, this.small.findMedianBirthday());
        assertEquals(180, this.medium.findMedianBirthday());
        assertEquals(183, this.fasterMedium.findMedianBirthday());
        assertEquals(185, this.fasterLarge.findMedianBirthday());
    }

    @Test
    void findBirthdaysWithMaxPeople() {
        assertEquals(List.of(39), this.small.findAllBirthdaysWithMaxPeople());
        assertEquals(List.of(39), this.medium.findAllBirthdaysWithMaxPeople());
        assertEquals(List.of(107), this.fasterMedium.findAllBirthdaysWithMaxPeople());
        assertEquals(List.of(38,85), this.fasterLarge.findAllBirthdaysWithMaxPeople());
    }

    @Test
    void findBirthdaysCoveringHalfOfThePeople() {
        assertEquals(100, this.small.findBirthdaysCoveringHalfOfThePeople().size());
        assertEquals(122, this.medium.findBirthdaysCoveringHalfOfThePeople().size());
        assertEquals(144, this.fasterMedium.findBirthdaysCoveringHalfOfThePeople().size());
        assertEquals(155, this.fasterLarge.findBirthdaysCoveringHalfOfThePeople().size());
    }
}