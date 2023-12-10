package nl.hva.ict.ads;

import java.util.Arrays;
import java.util.List;

public class CsvRow {
    private List<String> fields;
    private static final  String SEPARATOR = ",";

    public CsvRow(String rowString) {
        this.fields = Arrays.asList(rowString.split(SEPARATOR));
    }

    public int size() { return fields.size(); }

    public String getField(int index) { return fields.get(index);}

    @Override
    public String toString() {
        return "CsvRow{" +
                "fields=" + fields +
                '}';
    }
}
