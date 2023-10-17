import model.BirthdaysList;
import model.FasterBirthdaysList;

import java.util.List;

public class BirthdaysParadoxMain {
	private static final long RANDOM_SEED = 19670427;

	public static void main(String[] args) {
		BirthdaysList birthdays2500 = new BirthdaysList(2500, RANDOM_SEED);
		FasterBirthdaysList fasterBirthdaysList = new FasterBirthdaysList(2500, RANDOM_SEED);
		birthdaysTester(fasterBirthdaysList);
		birthdaysTester(birthdays2500);


//		for (int n = 40000; n >= 5000; n /= 2) {
//			BirthdaysList birthdays = new BirthdaysList(n);
//			birthdaysTester(birthdays);
//			BirthdaysList fasterBirthdays = new FasterBirthdaysList(n);
//			birthdaysTester(fasterBirthdays);
//		}
	}

	private static void birthdaysTester(BirthdaysList birthdays) {
		final int ONE_DAY = 123;
		final int FROM_DAY = 100;
		final int TO_DAY = 150;
		long started, finished;
		int result;
		List<Integer> resultsList;

		System.out.printf("\nTesting class %s with a list of %d birthdays:\n", birthdays.getClass().getSimpleName(), birthdays.size());
		birthdays.shuffle();
		System.gc();

		started = System.nanoTime();
		result = birthdays.countBirthdaysOn(ONE_DAY);
		finished = System.nanoTime();
		System.out.printf("Found %d people on day-%d after %.3f msec\n", result, ONE_DAY,(finished-started)/1E6);


		started = System.nanoTime();
		result = birthdays.countBirthdaysBetween(FROM_DAY, TO_DAY);
		finished = System.nanoTime();
		System.out.printf("Found %d people between day-%d and day-%d after %.3f msec\n", result, FROM_DAY, TO_DAY, (finished-started)/1E6);


		started = System.nanoTime();
		result = birthdays.findLastBirthdayOfYear();
		finished = System.nanoTime();
		System.out.printf("Found last birthday of year at day-%d after %.3f msec\n", result, (finished-started)/1E6);


		started = System.nanoTime();
		result = birthdays.countNumUniqueBirthdays();
		finished = System.nanoTime();
		System.out.printf("Found %d unique birthdays after %.3f msec\n", result, (finished-started)/1E6);

		// TODO measure performance of other methods


		started = System.nanoTime();
		result = birthdays.findFirstNonBirthday();
		finished = System.nanoTime();
		System.out.printf("Found %d as first non birthday after %.3f msec\n", result, (finished-started)/1E6);


		started = System.nanoTime();
		result = birthdays.maxPeopleWithSameBirthday();
		finished = System.nanoTime();
		System.out.printf("Found %d people with the same birthday after %.3f msec\n", result, (finished-started)/1E6);


		started = System.nanoTime();
		result = birthdays.maxPeopleWithBirthdayInSameWeek();
		finished = System.nanoTime();
		System.out.printf("Found %d with the same birthdays in a week after %.3f msec\n", result, (finished-started)/1E6);


		started = System.nanoTime();
		result = birthdays.findMedianBirthday();
		finished = System.nanoTime();
		System.out.printf("Found median birthday %d after %.3f msec\n", result, (finished-started)/1E6);


		started = System.nanoTime();
		List<Integer> results = birthdays.findAllBirthdaysWithMaxPeople();
		finished = System.nanoTime();
		System.out.printf("Found  birthday with max people %s after %.3f msec\n", results, (finished-started)/1E6);

		started = System.nanoTime();
		results = birthdays.findBirthdaysCoveringHalfOfThePeople();
		finished = System.nanoTime();
		System.out.printf("Found  birthday with max people %s after %.3f msec\n", results, (finished-started)/1E6);

	}
}
