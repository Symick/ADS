package graph.vertices;

import graph.Identifiable;

public class StringVertex implements Identifiable<String> {
    private final String id;

    public StringVertex(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.getId();
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
    @Override
    public boolean equals(Object other) {
        if (other instanceof StringVertex) {
            StringVertex ov = (StringVertex) other;
            return this.id.equals(ov.id);
        }
        return false;
    }

}

