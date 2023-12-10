package graph;

import java.util.*;

public class SUGraph<V extends Identifiable<ID>, ID> implements SimpleGraph<V, ID> {
    private final Map<ID, V> vertices;
    private final Map<V, Set<V>> neighbours;

    public SUGraph() {
        vertices = new HashMap<>();
        neighbours = new HashMap<>();
    }

    @Override
    public boolean addVertex(V vertex) {
        if (this.vertices.putIfAbsent(vertex.getId(), vertex) == null) {
            this.neighbours.put(vertex, new HashSet<>());
            return true;
        }
        return false;
    }

    @Override
    public V getVertex(ID id) {
        return this.vertices.get(id);
    }

    @Override
    public boolean addEdge(V vertex1, V vertex2) {
        Set<V> neighbourVertex1 = neighbours.get(vertex1);
        Set<V> neighboursVertex2 = neighbours.get(vertex2);

        if (neighbourVertex1 == null || neighboursVertex2 == null) {
            return false;
        }
        return neighbourVertex1.add(vertex2) && neighboursVertex2.add(vertex1);
    }

    @Override
    public boolean addEdge(ID vertexId1, ID vertexId2) {
        V vertex1 = vertices.get(vertexId1);
        V vertex2 = vertices.get(vertexId2);
        if (vertex1 == null || vertex2 == null) {
            return false;
        }
        return addEdge(vertex1, vertex2);
    }

    @Override
    public Collection<V> getVertices() {
        return vertices.values();
    }

    @Override
    public Collection<V> getNeighbours(V vertex) {
        return neighbours.get(vertex);
    }

    @Override
    public Collection<V> getNeighbours(ID vertexId) {
        return getNeighbours(vertices.get(vertexId));
    }

    @Override
    public int getNumEdges() {
        //since graph is undirected both vertices on one edge has each other in the set of neighbours.
        //Therefore, one edge is counted twice, meaning it should be divided by 2
        return this.neighbours.values().stream().mapToInt(Set::size).sum() / 2;
    }

    @Override
    public int getNumVertices() {
        return this.vertices.size();
    }

    @Override
    public String getAdjacencyReport() {
        StringBuilder report = new StringBuilder();
        for (V vertex : vertices.values()) {
            report.append(String.format("%s: %s%n", vertex, getNeighbours(vertex)));
        }
        return report.toString();
    }

    @Override
    public boolean isConnected() {
        if (this.vertices.isEmpty()) {
            return true;
        }
        V startingVertex = this.vertices.values().iterator().next(); //gets the first vertex of the iterator
        Set<V> visited = new HashSet<>();

        depthFirstSearch(startingVertex, visited);
        return visited.size() == vertices.size();
    }

    public void depthFirstSearch(V currentVertex, Set<V> visited) {
        visited.add(currentVertex); //add the current vertex to visited list

        //loop over neighbours
        for (V vertex : getNeighbours(currentVertex)) {
            //If vertex is not visited, do a sear
            if (!visited.contains(vertex)) {
                depthFirstSearch(vertex, visited);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("SUGraph with %d vertices and %d edges", getNumVertices(), getNumEdges());
    }

    public static class Builder<V extends Identifiable<ID>, ID> implements SimpleGraphBuilder<V, ID> {
        private SUGraph<V, ID> suGraph = new SUGraph<>();

        @Override
        public SimpleGraphBuilder<V, ID> addVertices(V... vertices) {
            for (V vertex : vertices) {
                suGraph.addVertex(vertex);
            }
            return this;
        }

        @Override
        public SimpleGraphBuilder<V, ID> addEdges(V vertex, V... neighbours) {
            for (V neighbour : neighbours) {
                suGraph.addEdge(vertex, neighbour);
            }
            return this;
        }

        @Override
        public SimpleGraphBuilder<V, ID> addEdges(ID vertexId, ID... neighbourIds) {
            for (ID neighbourId: neighbourIds) {
                suGraph.addEdge(vertexId, neighbourId);
            }
            return this;
        }

        @Override
        public SimpleGraph<V, ID> build() {
            return suGraph;
        }
    }
}
