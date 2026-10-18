package graph;

import java.util.Random;

public class GraphGenerator {
    private static final long SEED = 42;
    private static final Random random = new Random(SEED);

    public static Graph generateConnectedUndirectedGraph(int vertices, int edges) {
        Graph graph = new Graph(vertices);

        for (int i = 1; i < vertices; i++) {
            int u = random.nextInt(i);
            double weight = Math.round(random.nextDouble() * 100.0 * 100.0) / 100.0;
            graph.addEdge(u, i, weight, false);
        }

        int remainingEdges = edges - (vertices - 1);
        while (remainingEdges > 0) {
            int u = random.nextInt(vertices);
            int v = random.nextInt(vertices);
            if (u != v) {
                double weight = Math.round(random.nextDouble() * 100.0 * 100.0) / 100.0;
                graph.addEdge(u, v, weight, false);
                remainingEdges--;
            }
        }
        return graph;
    }

    public static Graph generateDirectedGraph(int vertices, int edges) {
        Graph graph = new Graph(vertices);
        for (int i = 0; i < edges; i++) {
            int u = random.nextInt(vertices);
            int v = random.nextInt(vertices);
            double weight = Math.round(random.nextDouble() * 100.0 * 100.0) / 100.0;
            graph.addEdge(u, v, weight, true);
        }
        return graph;
    }
}