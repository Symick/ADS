package nl.hva.ict.ads;

import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class CsvTable implements Iterable<CsvRow> {
    private final String resourceName;

    public CsvTable(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * Provides lazy loading of the data source and feeds it into a stream
     * @return
     */
    public Stream<CsvRow> stream() { return StreamSupport.stream(spliterator(), false); }

    @Override
    public Spliterator spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }

    @Override
    public Iterator<CsvRow> iterator() {
        return new CsvRowIterator(
                new Scanner(new InputStreamReader(this.getClass().getResourceAsStream(this.resourceName))));
    }

    private static class CsvRowIterator implements Iterator<CsvRow> {
        private final Scanner inputScanner;

        public CsvRowIterator(Scanner scanner) {
            inputScanner = scanner;
            // inputScanner.nextLine(); //skip first line, does not contain data but field names.
        }
        @Override
        public boolean hasNext() {
            return this.inputScanner.hasNextLine();
        }

        @Override
        public CsvRow next() {
            return new CsvRow(this.inputScanner.nextLine());
        }
    }
}
