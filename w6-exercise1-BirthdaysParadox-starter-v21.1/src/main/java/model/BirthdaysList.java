package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BirthdaysList {
	protected static final int MAX_DAY = 365;
	protected int[] birthdays;			// contains the birthdays 1 <= day <= 365 of some collection of people
	protected Random randomizer;

	// provides base 'in-place' implementation of all methods with minimum memory footprint
	// i.e. no additional auxiliary arrays of size MAX_DAY or numberOfPeople or larger are used within the methods,
	// only local variables and arraylists that are required to represent the result.

	public BirthdaysList(int numberOfPeople, long seed) {
		randomizer = new Random(seed);
		this.birthdays = new int[numberOfPeople];
		for (int i = 0; i < numberOfPeople; i++) {
			this.birthdays[i] = 1 + this.randomizer.nextInt(MAX_DAY);
		}
	}
	public BirthdaysList(int numberOfPeople) {
		this(numberOfPeople, 0);
	}

	public int size() {
		return this.birthdays.length;
	}

	public void shuffle() {
		for (int i = 0; i < this.birthdays.length; i++) {
			int swapIndex = this.randomizer.nextInt(this.birthdays.length);
			int tempDay = this.birthdays[i];
			this.birthdays[i] = this.birthdays[swapIndex];
			this.birthdays[swapIndex] = tempDay;
		}
	}

	/**
	 * counts the number of people with a given birthday
	 * @param birthday
	 * @return		the number of people found
	 */
	public int countBirthdaysOn(int birthday) {
		// TODO

		return 0;
	}

	/**
	 * counts the number of people with a birthday within a given day range
	 * @param fromBirthday	first day of the range (inclusive)
	 * @param toBirthday	last day of the range (inclusive)
	 * @return		the number of people found
	 */
	public int countBirthdaysBetween(int fromBirthday, int toBirthday) {
		// TODO

		return 0;
	}

	/** finds the last day on the calendar that somebody celebrates a birthday.
	 * @return		the day found
	 */
	public int findLastBirthdayOfYear() {
		// TODO

		return 0;
	}

	/**
	 * counts the number of birthdays between 1 and 365 on which exactly one person celebrates
	 * @return		number of birthdays found
	 */
	public int countNumUniqueBirthdays() {
		// TODO

		return 0;
	}

	/** finds the first day on the calendar that nobody celebrates a birthday.
	 * @return		the day found
	 * 				return -1 if all days are birthdays
	 */
	public int findFirstNonBirthday() {
		// TODO

		return -1;
	}

	/** counts the maximum number of people celebrating the same birthday
	 * @return		the number of people found
	 */
	public int maxPeopleWithSameBirthday() {
		// TODO

		return 0;
	}

	/** counts the maximum number of people celebrating their birthday in the same week
	 * a week is any sequence of 7 consecutive days, e.g. 1..7 or 2..8 or 3..9 or etc. 359..365
	 * @return		the number of people found
	 */
	public int maxPeopleWithBirthdayInSameWeek() {
		// TODO

		return 0;
	}

	/** finds the birthday such that half the people celebrate on or before that day
	 * and the other half celebrate on or after that day
	 * @return		the birthday found
	 * 				return -1 if such day does not exist
	 */
	public int findMedianBirthday() {
		// TODO

		return 0;
	}

	/** finds all birthdays on which the maximum number of people celebrate
	 * @return		the birthdays found
	 */
	public List<Integer> findAllBirthdaysWithMaxPeople() {
		// TODO

		return null;
	}

	/** finds the shortest list of birthdays on which in total at least half of the people celebrate
	 *  the other half of the people would celebrate on another day that is not in the list
	 * @return		the birthdays found
	 */
	public List<Integer> findBirthdaysCoveringHalfOfThePeople() {
		// TODO

		return null;
	}

	public String toString() {
		return Arrays.toString(this.birthdays);
	}

}
