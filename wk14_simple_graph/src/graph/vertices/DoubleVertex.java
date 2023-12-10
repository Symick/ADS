package graph.vertices;

import graph.Identifiable;

import java.util.Objects;

public class DoubleVertex implements Identifiable<Double> {
    private Double id;

    public DoubleVertex(Double id) {
        this.id = id;
    }
    @Override
    public Double getId() {
        return id;
    }

    @Override
    public String toString() {
        return getId().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoubleVertex that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
