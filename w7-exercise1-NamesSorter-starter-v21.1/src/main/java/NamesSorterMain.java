import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NamesSorterMain {
    private static final int REPEATS = 5;
    private static final int MAX_N = 10_000;

    public static void main(String[] args) {
        System.out.println("Welcome to the HvA Names Sorter\n");

        Sorter<String> sorter = new Sorter<>();

        SimpleTable<Double> results = new SimpleTable<>(5,15, Double::sum, (a,b)-> a*b);

        List<String> originalNames = new ArrayList<>();
        List<String> sortedOriginalNames;
        List<String> sortedNames;

        long started;
        double duration;

        for (int iteration = 1; iteration <= REPEATS; iteration++) {
            System.out.printf("\nTest iteration-%d\n", iteration);
            for (int n = 100, row = 0; n < MAX_N; n *= 2, row++ ) {
                System.out.printf("\nSorting %d names:\n", n);

                results.add(0, row, (double)n);

                originalNames.clear();
                for (int i = 0; i < n; i++) originalNames.add(Names.nextFullNameReversed());

                sortedOriginalNames = new ArrayList<>(originalNames);
                System.gc();
                started = System.nanoTime();
                sortedOriginalNames.sort(Comparator.naturalOrder());
                duration = 1E-6*(System.nanoTime() - started);
                System.out.printf("Collection sort took %.2f msec\n", duration);
                results.add(1, row, duration);

                sortedNames = new ArrayList<>(originalNames);
                System.gc();
                started = System.nanoTime();
                sorter.bubbleSortUntilDone(sortedNames,String::compareTo);
                duration = 1E-6*(System.nanoTime() - started);
                System.out.printf("BubbleSortUntilDone took %.2f msec\n", duration);
                results.add(2, row, duration);
                if (showDifferences(sortedOriginalNames, sortedNames)) break;

                sortedNames = new ArrayList<>(originalNames);
                System.gc();
                started = System.nanoTime();
                sorter.mergeSortViaAuxiliary(sortedNames,String::compareTo);
                duration = 1E-6*(System.nanoTime() - started);
                System.out.printf("MergeSortViaAuxiliary took %.2f msec\n", duration);
                results.add(3, row, duration);
                if (showDifferences(sortedOriginalNames, sortedNames)) break;

                sortedNames = new ArrayList<>(originalNames);
                System.gc();
                started = System.nanoTime();
                sorter.batchersOddEvenMergeSort(sortedNames,String::compareTo);
                duration = 1E-6*(System.nanoTime() - started);
                System.out.printf("BatchersOddEvenMergeSort took %.2f msec\n", duration);
                results.add(4, row, duration);
                if (showDifferences(sortedOriginalNames, sortedNames)) break;
            }
        }

        results.multiplyAll(1.0/REPEATS);
        System.out.printf("\nSummary of %d repeats:\n", REPEATS);
        System.out.println("N; T_collection; T_bubbleUntilDone; T_mergeViaAux; T_batchers");
        System.out.println(results.csv("%.2f"));
    }

    private static <E> boolean showDifferences(List<E> list1, List<E> list2) {
        if (list1.size() != list2.size()) {
            System.out.printf("Lists have different sizes %d and %d\n", list1.size(), list2.size());
            return true;
        }
        int firstDifference = 0;
        while (firstDifference < list1.size() && list1.get(firstDifference).equals(list2.get(firstDifference)))
            firstDifference++;
        if (firstDifference == list1.size()) return false;
        System.out.printf("Lists differ at position %d:\n%s\n%s\n", firstDifference,
                list1.subList(Integer.max(0, firstDifference-2),Integer.min(list1.size(),firstDifference+3)),
                list2.subList(Integer.max(0, firstDifference-2),Integer.min(list2.size(),firstDifference+3))
                );
        return true;
    }
}
