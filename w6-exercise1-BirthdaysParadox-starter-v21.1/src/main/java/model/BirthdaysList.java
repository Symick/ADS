package model;

import java.util.*;

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
		int count = 0;
		for (int currentBirthday: birthdays) {
			if (currentBirthday == birthday) {
				count++;
			}
		}
		return count;
	}

	/**
	 * counts the number of people with a birthday within a given day range
	 * @param fromBirthday	first day of the range (inclusive)
	 * @param toBirthday	last day of the range (inclusive)
	 * @return		the number of people found
	 */
	public int countBirthdaysBetween(int fromBirthday, int toBirthday) {
		int count = 0;

		for (int birthday: birthdays) {
			if (birthday >= fromBirthday && birthday <= toBirthday) {
				count++;
			}
		}
		return count;
	}

	/** finds the last day on the calendar that somebody celebrates a birthday.
	 * @return		the day found
	 */
	public int findLastBirthdayOfYear() {
		int last = 0;

		for (int birthday: birthdays) {
			if (birthday == MAX_DAY) return birthday;
			if (birthday > last) {
				last = birthday;
			}
		}
		return last;
	}

	/**
	 * counts the number of birthdays between 1 and 365 on which exactly one person celebrates
	 * @return		number of birthdays found
	 */
	public int countNumUniqueBirthdays() {
		int count = 0;
		for (int birthday : birthdays) {
			if (countBirthdaysOn(birthday) == 1) {
				count++;
			}
		}
		return count;
	}

	/** finds the first day on the calendar that nobody celebrates a birthday.
	 * @return		the day found
	 * 				return -1 if all days are birthdays
	 */
	public int findFirstNonBirthday() {
		for (int i = 1; i <= MAX_DAY; i++) {
			if (countBirthdaysOn(i) == 0) {
				return i;
			}
		}

		return -1;
	}

	/** counts the maximum number of people celebrating the same birthday
	 * @return		the number of people found
	 */
	public int maxPeopleWithSameBirthday() {
		int max = 0;

		for (int birthday : birthdays) {
			int count = countBirthdaysOn(birthday);
			if (count > max) {
				max = count;
			}
		}

		return max;
	}

	/** counts the maximum number of people celebrating their birthday in the same week
	 * a week is any sequence of 7 consecutive days, e.g. 1..7 or 2..8 or 3..9 or etc. 359..365
	 * @return		the number of people found
	 */
	public int maxPeopleWithBirthdayInSameWeek() {
		int max = 0;

		for (int birthday: birthdays) {
			int countInWeek = countBirthdaysBetween(birthday, birthday + 6);
			if (countInWeek > max) {
				max = countInWeek;
			}
		}
		return max;
	}

	/** finds the birthday such that half the people celebrate on or before that day
	 * and the other half celebrate on or after that day
	 * @return		the birthday found
	 * 				return -1 if such day does not exist
	 */
	public int findMedianBirthday() {
		for (int i = 1; i <= MAX_DAY; i++) {
			int before = countBirthdaysBetween(1, i);
			int after = countBirthdaysBetween(i, MAX_DAY);
				if (before >= birthdays.length /2 && after >= birthdays.length / 2) {
					return i;
				}
		}
		return 0;
	}

	/** finds all birthdays on which the maximum number of people celebrate
	 * @return		the birthdays found
	 */
	public List<Integer> findAllBirthdaysWithMaxPeople() {
		ArrayList<Integer> birthdays = new ArrayList<>();
		int maxBirthdays = maxPeopleWithSameBirthday();

		for (int i = 1; i <= 365; i++) {
			if (maxBirthdays == countBirthdaysOn(i)) {
				birthdays.add(i);
			}
		}

		return birthdays;
	}

	/** finds the shortest list of birthdays on which in total at least half of the people celebrate
	 *  the other half of the people would celebrate on another day that is not in the list
	 * @return		the birthdays found
	 */
	public List<Integer> findBirthdaysCoveringHalfOfThePeople() {
		ArrayList<Integer> half = new ArrayList<>();
		int total = 0;
		for (int i = 0; i < birthdays.length; i++) {
			int max = 0;
			int nextHighestBirthday = 0;
			for (int j = 0; j < birthdays.length; j++) {
				if (birthdays[i] != birthdays[j]) {
					int count = countBirthdaysOn(birthdays[j]);
					if (half.contains(birthdays[j])) continue;
					if (count > max) {
						max = count;
						nextHighestBirthday = birthdays[j];
					}
				}
			}
			half.add(nextHighestBirthday);
			total += max;
			if (total >= birthdays.length / 2) {
				return half;
			}
		}
		return half;
	}

	public String toString() {
		return Arrays.toString(this.birthdays);
	}

}
