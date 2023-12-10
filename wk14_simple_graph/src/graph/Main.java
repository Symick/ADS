package graph;


import graph.vertices.DoubleVertex;
import graph.vertices.StringVertex;

public class Main {
    public static void main(String[] args) {
        runStringExample();
        runDoubleExample();
    }

    public static void runStringExample() {
        System.out.println("Welcome to the Simple Undirected Graph demo program\n");
// Build a graph with vertices that are uniquely identified by a String identifier
        SimpleGraph<StringVertex, String> graph = new SUGraph.Builder<StringVertex, String>().addVertices(new StringVertex("A"), new StringVertex("B"), new StringVertex("C"), new StringVertex("D"), new StringVertex("E")).addEdges("A", "B", "C").addEdges("B", "C", "D", "E").addEdges("C", "D", "E").addEdges("D", "E").build();
        System.out.println(graph);
        System.out.println(graph.getAdjacencyReport());
        System.out.println("isConnected = " + graph.isConnected() + "\n");
        graph.addVertex(new StringVertex("E"));
// duplicate test
        graph.addVertex(new StringVertex("F"));
        graph.addVertex(new StringVertex("G"));
        graph.addVertex(new StringVertex("H"));
        graph.addEdge("F", "G");
        graph.addEdge("G", "H");
        graph.addEdge("H", "I");
// non-existing vertex test
        System.out.println(graph);
        System.out.println(graph.getAdjacencyReport());
        System.out.println("isConnected = " + graph.isConnected() + "\n");
    }

    public static void runDoubleExample() {
        System.out.println("\nTry the Double type for the vertex identifier:\n");
// Build a graph with vertices that are uniquely identified by a Double identifier
        SimpleGraph<DoubleVertex, Double> graph2 = new SUGraph.Builder<DoubleVertex, Double>()
                .addVertices(new DoubleVertex(11.1), new DoubleVertex(22.2), new DoubleVertex(33.3), new DoubleVertex(44.4), new DoubleVertex(55.5))
                .addEdges(11.1, 22.2, 33.3)
                .addEdges(22.2, 33.3, 44.4, 55.5)
                .addEdges(33.3, 44.4, 55.5)
                .addEdges(44.4, 55.5)
                .build();
        System.out.println(graph2);
        System.out.println(graph2.getAdjacencyReport());
        System.out.println("isConnected = " + graph2.isConnected() + "\n");
    }
}