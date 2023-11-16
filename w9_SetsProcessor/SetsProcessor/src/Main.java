import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Set<Integer> numbers = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            numbers.add(random.nextInt(1001));
        }
        System.out.printf("Created set 'numbers', added 100 numbers of max value=1000; numbers.size()= %d\n", numbers.size());

        Set<Integer> oddNumbers = new HashSet<>(numbers);
        oddNumbers.removeIf(integer -> integer % 2 == 0);
        System.out.printf("Created set 'oddNumbers' numbers.size()= %d\n", oddNumbers.size());

        Set<Integer> evenNumbers = new HashSet<>(numbers);
        evenNumbers.removeAll(oddNumbers);
        System.out.printf("Created set 'evenNumbers' numbers.size()= %d\n", evenNumbers.size());

        Set<Integer> triplicates = new HashSet<>(numbers);
        triplicates.removeIf(integer -> integer % 3 != 0);
        System.out.printf("Created set 'triplicatesNumbers' numbers.size()= %d\n", triplicates.size());

        Set<Integer> sixFold1 = new HashSet<>(triplicates);
        sixFold1.retainAll(evenNumbers);
        System.out.printf("Created set 'sixfold1 Numbers' numbers.size()= %d\n", sixFold1.size());

        Set<Integer> sixFold2 = new HashSet<>(triplicates);
        sixFold2.removeAll(oddNumbers);
        System.out.printf("Created set 'sixfold2 Numbers' numbers.size()= %d\n", sixFold2.size());

        boolean equals = sixFold1.equals(sixFold2);
        System.out.println(equals);

        System.out.println(sixFold1);

    }
}